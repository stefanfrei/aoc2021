/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.schlibbuz.aoc2021.day5.Point;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

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
