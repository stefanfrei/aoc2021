/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day7 extends Day {

  Day7() {
    this(RUN_TYPE.PROD);
  }

  Day7(RUN_TYPE runType) {
    super(runType);
  }

  int consumeLinear(int from, int to) {
    return to - from;
  }

  int consumeGrowing(int from, int to) {
    int consumption = 1;
    int sum = 0;
    for (var i = from; i < to; i++) {
      sum += consumption++;
    }
    return sum;
  }

  @Override
  public long part1() {
    var data = Arrays.asList(super.data.get(0).split(",")).stream().map(mapper -> Integer.parseInt(mapper)).collect(Collectors.toList());
    Collections.sort(data);
    data.forEach(System.out::println);
    int minCons = 0;
    for (int elem = data.get(0); elem <= data.get(data.size() - 1); elem++) {
      int acc = 0;
      for (var i : data) {
        acc += consumeLinear(Math.min(i, elem), Math.max(i, elem));
      }
      if (minCons == 0 || acc < minCons) minCons = acc;
    }
    return minCons;
  }

  @Override
  public long part2() {
    var data = Arrays.asList(super.data.get(0).split(",")).stream().map(mapper -> Integer.parseInt(mapper)).collect(Collectors.toList());
    Collections.sort(data);
    data.forEach(System.out::println);
    int minCons = 0;
    for (int elem = data.get(0); elem <= data.get(data.size() - 1); elem++) {
      int acc = 0;
      for (var i : data) {
        acc += consumeGrowing(Math.min(i, elem), Math.max(i, elem));
      }
      if (minCons == 0 || acc < minCons) minCons = acc;
    }
    return minCons;
  }

}
