/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.Collections;
import java.util.Comparator;
import org.schlibbuz.aoc2021.day9.HeightMap;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day9 extends Day {

  Day9() {
    this(RUN_TYPE.PROD);
  }

  Day9(RUN_TYPE runType) {
    super(runType);
  }

  @Override
  public long part1() {
    var map = new HeightMap(data);
    return map.sumLowPoints(map.getLowPointsIndices());
  }

  @Override
  public long part2() {
    var map = new HeightMap(data);
    var basins = map.getBasins(map.getLowPointsIndices());
    Collections.sort(basins, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o2.length - o1.length;
      }
    });

    long total = 1;
    for (int i = 0; i < 3; i++) {
      total *= basins.get(i).length;
    }
    return total;
  }

}
