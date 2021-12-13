/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Octopi {

  public final List<Octopus> octopi;
  public final Map<String, Integer> adjacentRelatives;
  final int dataWidth;
  final int dataHeight;
  final int maxSteps;
  public int step;
  public int flashes;


  public Octopi(List<String> data, int maxSteps) {
    octopi = init(data);
    dataWidth = data.get(0).length();
    dataHeight = data.size();
    adjacentRelatives = initAdjacentRelatives();
    initAdjacents();
    this.maxSteps = maxSteps;
    step = 0;
    flashes = 0;
  }

  List<Octopus> init(List<String> data) {
    List<Octopus> list = new ArrayList<>();
    int index = 0;
    for (var line : data) {
      for (var c : line.toCharArray()) {
        list.add(new Octopus(index++, Integer.parseInt(String.valueOf(c))));
      }
    }
    return list;
  }

  Map<String, Integer> initAdjacentRelatives() {
    return Stream.of(new Object[][] {
      { "TOP",       -1 * dataWidth     },
      { "TOP_RIGHT", -1 * dataWidth + 1 },
      { "RIGHT",      1                 },
      { "BOT_RIGHT",  dataWidth + 1     },
      { "BOT",        dataWidth         },
      { "BOT_LEFT",   dataWidth - 1     },
      { "LEFT",      -1                 },
      { "TOP_LEFT",  -1 * dataWidth - 1 },
    }).collect(Collectors.toUnmodifiableMap(data -> (String) data[0], data -> (Integer) data[1]));
  }

  boolean isTopBorder(int index) {
    return index < dataWidth;
  }

  boolean isRightBorder(int index) {
    return index % dataWidth == dataWidth -1;
  }

  boolean isBottomBorder(int index) {
    return index >= dataWidth * dataHeight - dataWidth;
  }

  boolean isLeftBorder(int index) {
    return index % dataWidth == 0;
  }

  void initAdjacents() {
    for (var octopus : octopi) {
      var index = octopus.index;
      var adjacents = octopus.adjacents;
      boolean isTopBorder = isTopBorder(index);
      boolean isRightBorder = isRightBorder(index);
      boolean isBottomBorder = isBottomBorder(index);
      boolean isLeftBorder = isLeftBorder(index);
      if (!isTopBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("TOP")));
      }
      if (!isTopBorder && !isRightBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("TOP_RIGHT")));
      }
      if (!isRightBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("RIGHT")));
      }
      if (!isRightBorder && !isBottomBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("BOT_RIGHT")));
      }
      if (!isBottomBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("BOT")));
      }
      if (!isLeftBorder && !isBottomBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("BOT_LEFT")));
      }
      if (!isLeftBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("LEFT")));
      }
      if (!isLeftBorder && !isTopBorder) {
        adjacents.add(octopi.get(index + adjacentRelatives.get("TOP_LEFT")));
      }
    }
  }

  public void processSteps() {
    IntStream.range(0, maxSteps).forEach(i -> step());
  }

  public int findFirstSimultanFlash() {
    int step = 0;
    while(step() != octopi.size()) {
      step++;
    }
    return ++step;
  }

  List<Octopus> getFlashers(List<Octopus> octopi) {
    return octopi.stream().filter(
        octopus -> octopus.energy > 9 && !octopus.hasFlashed
    ).collect(Collectors.toList());
  }

  public int step() {
    int numFlashers = 0;
    octopi.forEach(octo -> {
      octo.energy++;
    });
    List<Octopus> flashers;
    while (!(flashers = getFlashers(octopi)).isEmpty()) {
      numFlashers += flashers.size();
      flashers.forEach(octopus -> octopus.flash());
    }
    flashes += numFlashers;
    octopi.stream().filter(octopus -> octopus.energy > 9).forEach(octopus -> {
      octopus.energy = 0;
      octopus.hasFlashed = false;
    });
    return numFlashers;
  }

  public List<Octopus> getAdjacents(Octopus current) {
    List<Octopus> list = new ArrayList<>();
    return list;
  }

  public String getEnergyLevels() {
    StringBuilder sb = new StringBuilder((dataWidth + 1) * dataHeight);
    for (var octopus : octopi) {
      sb.append(octopus.energy);
      if (isRightBorder(octopus.index)) sb.append('\n');
    }
    return sb.toString();
  }
}
