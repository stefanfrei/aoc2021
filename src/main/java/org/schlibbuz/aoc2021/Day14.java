/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day14.Polymer;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day14 extends Day {

  Day14() {
    this(RUN_TYPE.PROD);
  }

  Day14(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    var inst = new Polymer(data);
    inst.times(10).expand();
    return inst.getScore();
//    return inst.getScore(
//        inst.times(10).grow().toString()
//    );
  }

  @Override
  public long part2() {
    var inst = new Polymer(data);
    inst.times(40).expand();
    return inst.getScore();
  }

}
