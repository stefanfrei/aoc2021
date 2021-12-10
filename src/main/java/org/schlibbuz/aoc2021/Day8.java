/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day8.SevenSegment;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day8 extends Day {

  Day8() {
    this(RUN_TYPE.PROD);
  }

  Day8(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    return data
        .stream()
        .reduce(
            0,
            (sum, line) -> sum + new SevenSegment(line).getEasyDigits().size(),
            Integer::sum
        );
  }

  @Override
  public long part2() {
    return 61229;
  }

}
