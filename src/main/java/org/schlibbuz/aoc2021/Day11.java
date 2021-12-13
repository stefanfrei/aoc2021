/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day11.Octopi;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day11 extends Day {

  static final int MAX_STEPS  = 100;

  Day11() {
    this(RUN_TYPE.PROD);
  }

  public Day11(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    var octopi = new Octopi(data, MAX_STEPS);
    octopi.processSteps();
    return octopi.flashes;
  }

  @Override
  public long part2() {
    var octopi = new Octopi(data, MAX_STEPS);
    return octopi.findFirstSimultanFlash();
  }

}
