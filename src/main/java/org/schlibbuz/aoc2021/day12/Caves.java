/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Caves {

  public Map<String, Cave> caves;
  List<Path> paths;


  public Caves(List<String> data) {
    caves = initCaves(data);
    paths = new ArrayList<>();
  }

  Map<String, Cave> initCaves(List<String> data) {
    Map<String, Set<String>> frags = new HashMap<>();

    for (var line : data) {
      var caveIds = Arrays.asList(line.split("-"));
      for (var caveId : caveIds) {
        if (!frags.containsKey(caveId)) {
          frags.put(caveId, new HashSet<>());
        }
        frags.get(caveId).addAll(
            caveIds.stream().filter(adjId -> !adjId.equals(caveId)).collect(Collectors.toSet())
        );
      }
    }
    Map<String, Cave> caves = new HashMap<>();
    for (var caveId : frags.keySet()) {
      caves.put(caveId, new Cave(caveId));
    }
    for (var caveId : caves.keySet()) {
      caves.get(caveId).adjacents.addAll(
          frags.get(caveId).stream().collect(Collectors.toSet())
      );
    }
    return caves;
  }

  

  Set<String> filterUndiscovered() {
    return caves.keySet()
        .stream()
        .filter(caveId -> !caveId.equals("end") && caves.get(caveId).pathsToEnd.isEmpty())
        .collect(Collectors.toSet());
  }

  List<Path> getIncompletePaths(List<Path> paths) {
    return paths.stream().filter(path -> !path.nodes.contains("end")).collect(Collectors.toList());
  }

  List<Path> divePath(Path path) {
    var lastCave = caves.get(path.nodes.get(path.nodes.size() -1));
    List<Path> newPaths = new ArrayList<>();
    for (var nextOption : lastCave.adjacents) {
      var newPath = new Path(path);
      System.out.println(nextOption);
      newPath.addNode(caves.get(nextOption));
      newPaths.add(newPath);
    }
    return newPaths;
  }

  public List<Path> findPaths() {
    paths.add(new Path(Arrays.asList(caves.get("start"))));
    var iter = 0;
    while (!(paths = getIncompletePaths(paths)).isEmpty()) {
      var backup = List.copyOf(paths);
      List<Path> changes = new ArrayList<>();
      for (var path : paths) changes.addAll(divePath(path));
      paths.clear();
      paths.addAll(changes);
      paths.forEach(System.out::println);
      if (paths.equals(backup) || ++iter == 3) {
        System.out.println("no progress, will terminate");
        break;
      }
    }
    return paths;
  }
}
