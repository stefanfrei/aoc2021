/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import org.schlibbuz.aoc2021.day13.Origami;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day13 extends Day {

  Day13() {
    this(RUN_TYPE.PROD);
  }

  Day13(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    var inst = new Origami(data);
    inst.dots = inst.fold(inst.folds.get(0));
    return inst.dots.size();
  }

  @Override
  public long part2() {
    var inst = new Origami(data);
    for (var fold : inst.folds) {
      inst.dots = inst.fold(fold);
      System.out.println(inst.getPaper());
    }
    System.out.println(inst.getPaper().replace(".", " "));
    return 0;
  }

}
