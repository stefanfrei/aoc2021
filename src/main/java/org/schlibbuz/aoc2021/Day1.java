/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day1 extends Day {

  private static final int WINDOW_SIZE = 3;
  private final List<Integer> data;

  Day1() {
    this(RUN_TYPE.PROD);
  }

  Day1(RUN_TYPE runType) {
    super(runType);
    data = DataTools.toIntList(super.data);
  }

  @Override
  public long part1() {
    int prev = 0;
    int counter = 0;
    for (int number : data) {
      if (prev != 0 && number > prev) {
        counter++;
      }
      prev = number;
    }
    return counter;
  }

  @Override
  public long part2() {
    int prev = 0;
    int counter = 0;

    for (int i = 0; i <= data.size() - WINDOW_SIZE; i++) {
      int sum = data.get(i) + data.get(i + 1) + data.get(i + 2);
      if (prev != 0 && sum > prev) {
        counter++;
      }
      prev = sum;
    }
    return counter;
  }

}
