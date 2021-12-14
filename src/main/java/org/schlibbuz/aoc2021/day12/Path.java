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

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Path {

  public final List<String> nodes;
  public final Set<String> smallCavesVisited;


  public Path() {
    this(new ArrayList<>());
  }

  public Path(List<Cave> nodes) {
    this.nodes = new ArrayList<>();
    smallCavesVisited = new HashSet<>();
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
  }

  boolean isLegalMove(Cave newNode) {
    //dont visit small caves more than once
    if (newNode.caveType == CAVE_TYPE.SMALL) return !nodes.contains(newNode.id);

    //prevent pingponging between big caves (A,B,A,B), cant occur when list size < 4
    var size = nodes.size();
    if (size < 4) return true;
    return !(nodes.get(size - 2).equals(newNode.id) && nodes.get(size - 1).equals(size - 3));

  }

  public void addNode(Cave newNode) {
    var id = newNode.id;
    if (isLegalMove(newNode)) {
      nodes.add(id);
      if (newNode.caveType == CAVE_TYPE.SMALL) smallCavesVisited.add(id);
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
