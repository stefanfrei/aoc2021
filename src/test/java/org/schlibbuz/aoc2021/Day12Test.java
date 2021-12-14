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
public class Day12Test {

  @Test
  public void testPart1() {
    var inst = new Day12(RUN_TYPE.TEST);
    assertEquals(inst.part1(), 226);
  }

  @Test
  public void testPart2() {
    var inst = new Day12(RUN_TYPE.TEST);
    assertEquals(inst.part2(), 3509);
  }
}
