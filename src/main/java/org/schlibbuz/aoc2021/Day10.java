/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day10.Scanner;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day10 extends Day {

  Day10() {
    this(RUN_TYPE.PROD);
  }

  Day10(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    int score = 0;
    for (var code : data) {
      var scanner = new Scanner(code);
    }
    return 0;
  }

  @Override
  public long part2() {
    return 0;
  }

}
