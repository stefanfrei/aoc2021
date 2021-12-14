/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Path {

  public final List<String> nodes;
  public final Set<String> smallCavesVisited;
  final boolean part2Rules;
  boolean jokerDrawn;


  public Path() {
    this(new ArrayList<>(), false);
  }

  public Path(List<Cave> nodes, boolean part2Rules) {
    this.nodes = new ArrayList<>();
    smallCavesVisited = new HashSet<>();
    this.part2Rules = part2Rules;
    jokerDrawn = false;
    for (var node : nodes) {
      var id = node.id;
      if (!isLegalMove(node)) {
        System.out.println("illegal action for node -> " + id);
        continue;
      }
      this.nodes.add(id);
      if (node.caveType == CAVE_TYPE.SMALL) smallCavesVisited.add(id);
    }
  }

  public Path(Path path) {
    nodes = new ArrayList<>(List.copyOf(path.nodes));
    smallCavesVisited = new HashSet<>(Set.copyOf(path.smallCavesVisited));
    part2Rules = path.part2Rules;
    jokerDrawn = path.jokerDrawn;
  }

  boolean part2Rule(Cave newNode) {
    //PART 2 RULESET
    if (jokerDrawn || newNode.id.equals("start") || newNode.id.equals("end")) {
      return !nodes.contains(newNode.id);
    } else {
      return true;
    }
  }

  boolean isLegalMove(Cave newNode) {
    if (newNode.caveType == CAVE_TYPE.BIG) return true;
    return part2Rules ? part2Rule(newNode) : !nodes.contains(newNode.id);
  }

  public void addNode(Cave newNode) {
    var id = newNode.id;
    if (isLegalMove(newNode)) {
      nodes.add(id);
      if (newNode.caveType == CAVE_TYPE.SMALL) {
        var res = smallCavesVisited.add(id);
        if (!res && !id.equals("start") && !id.equals("end")) jokerDrawn = true;
      }
    } else {
      System.out.println("illegal move for cave -> " + id);
    }
  }

  String encode() {
    return String.join(",", nodes);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.nodes);
    hash = 47 * hash + Objects.hashCode(this.smallCavesVisited);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Path other = (Path) obj;
    if (!Objects.equals(this.nodes, other.nodes)) {
      return false;
    }
    return Objects.equals(this.smallCavesVisited, other.smallCavesVisited);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Path{nodes=").append(nodes);
    sb.append(", smallCavesVisited=").append(smallCavesVisited);
    sb.append('}');
    return sb.toString();
  }

}
