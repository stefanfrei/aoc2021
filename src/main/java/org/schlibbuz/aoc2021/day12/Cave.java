/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Cave {

  public final String id;
  public final CAVE_TYPE caveType;
  public int visited;
  public Set<String> adjacents;
  public Set<List<String>> pathsToEnd;


  public Cave(String id) {
    this.id = id;
    caveType = getCaveType();
    visited = 0;
    adjacents = new HashSet<>();
    pathsToEnd = new HashSet<>();
  }

  CAVE_TYPE getCaveType() {
    return id.toUpperCase().equals(id) ? CAVE_TYPE.BIG : CAVE_TYPE.SMALL;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 61 * hash + Objects.hashCode(this.id);
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
    final Cave other = (Cave) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

}
