/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day5Test {

  @Test
  public void part1Test() {
    var inst = new Day5(RUN_TYPE.TEST);
    assertEquals(inst.part1(), 5);
  }

  @Test
  public void part2Test() {
    var inst = new Day5(RUN_TYPE.TEST);
    assertEquals(inst.part2(), 12);
  }
}
