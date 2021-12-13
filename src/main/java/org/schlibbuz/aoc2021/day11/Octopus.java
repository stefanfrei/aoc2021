/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day11;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Octopus {

  public final int index;
  public int energy;
  boolean hasFlashed;
  public List<Octopus> adjacents;


  public Octopus(int index, int energy) {
    this.index = index;
    this.energy = energy;
    adjacents = new ArrayList<>();
    hasFlashed = false;
  }

  void incrementAdjacents() {
    adjacents
        .stream()
        .filter(octopus -> !octopus.hasFlashed)
        .forEach(octopus -> octopus.energy++);
  }

  void flash() {
    hasFlashed = true;
    incrementAdjacents();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Octopus{index=").append(index);
    sb.append(", energy=").append(energy);
    sb.append(", hasFlashed=").append(hasFlashed);
    sb.append('}');
    return sb.toString();
  }

}
