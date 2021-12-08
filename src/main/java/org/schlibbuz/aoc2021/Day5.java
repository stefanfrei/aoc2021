/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day5 extends Day implements FileLoader {

  final Map<Integer, Map<Integer, Integer>> map;

  Day5() {
    this(RUN_TYPE.PROD);
  }

  Day5(RUN_TYPE runType) {
    super(runType);
    map = new HashMap<>();
  }

  Map<Integer, Map<Integer,  Integer>> loadMap(List<String> data) {
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    data.forEach(System.out::println);
    return map;
  }

  @Override
  public int part1() {
    var map = loadMap(data);
    return 5;
  }

  @Override
  public int part2() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


}
