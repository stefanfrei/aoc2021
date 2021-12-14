/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day12.Caves;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day12 extends Day {

  Day12() {
    this(RUN_TYPE.PROD);
  }

  Day12(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    var caves = new Caves(data);
    return caves.findPaths().size();
  }

  @Override
  public long part2() {
    var caves = new Caves(data);
    return caves.findPaths(true).size();
  }

}
