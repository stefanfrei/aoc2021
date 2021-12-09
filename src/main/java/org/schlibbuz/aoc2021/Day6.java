/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day6 extends Day {
  Day6() {
    this(RUN_TYPE.PROD);
  }

  Day6(RUN_TYPE runType) {
    super(runType);
  }

  Map<Integer, Long> mapFish(List<Integer> initialPop) {
    final Map<Integer, Long> map = new HashMap<>();
    IntStream.range(0, 9).forEach(num -> {
      map.put(num, 0L);
    });
    initialPop.forEach(fish -> {
      map.put(fish, map.get(fish) + 1);
    });
    return map;
  }
  void printMap(Map<Integer, Long> map) {
    map.keySet().forEach(key -> {
      System.out.println(String.format("[%d]  -> %14d, ", key, map.get(key)));
    });
    System.out.println(String.format("size -> %14d", countFish(map)));
    System.out.println();
  }

  long countFish(Map<Integer, Long> map) {
    return map.values().stream().reduce(0L, Long::sum);
  }

  Map<Integer, Long> growFish(int endDay) {
    var map = mapFish(
        Arrays.asList(data.get(0).split(","))
        .stream()
        .map(timer -> Integer.parseInt(timer))
        .collect(Collectors.toList())
    );
    System.out.println("Initial state:");
    printMap(map);
    IntStream.range(1, endDay + 1).forEach(day -> {
      var dayString = day > 1 ? "days" : "day";
      System.out.println(String.format("After %3d %s:", day, dayString));
      long oldNul = 0;
      for (var key : map.keySet()) {
        if (key == 0) {
          map.put(7, map.get(0) + map.get(7));
          oldNul = map.get(0);
          map.put(0, 0L);
        } else {
          map.put(key - 1, map.get(key));
        }
      }
      map.put(8, oldNul);
      printMap(map);
    });
    return map;
  }

  @Override
  public long part1() {
    int endDay = 80;
    return countFish(growFish(endDay));
  }

  @Override
  public long part2() {
    int endDay = 256;
    return countFish(growFish(endDay));
  }
}
