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


  public Caves(List<String> data) {
    caves = initCaves(data);
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

  String encodePath(List<String> path) {
    return String.join(",", path);
  }

  Set<String> filterUndiscovered() {
    return caves.keySet()
        .stream()
        .filter(caveId -> !caveId.equals("end") && caves.get(caveId).pathsToEnd.isEmpty())
        .collect(Collectors.toSet());
  }


  boolean isLegalMove(Cave caveToAdd, List<String> path) {
    //dont visit small caves more than once
    if (caveToAdd.caveType == CAVE_TYPE.SMALL) return !path.contains(caveToAdd.id);

    //prevent pingponging between big caves (A,B,A,B), cant occur when list size < 4
    var size = path.size();
    if (size < 4) return true;
    return !(path.get(size - 2).equals(caveToAdd.id) && path.get(size - 1).equals(size - 3));

  }

  public List<Path> findPaths() {
    List<Path> paths = new ArrayList<>();
    paths.add(new Path(Arrays.asList(caves.get("start"))));
    return paths;
  }
}
