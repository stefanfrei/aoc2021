/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Day3 extends Day {

  Day3() {
    this(RUN_TYPE.PROD);
  }

  Day3(RUN_TYPE runType) {
    super(runType);
  }

  int toDec(String[] bin) {
    var sb = new StringBuilder();
    for (String s : bin) {
      sb.append(s);
    }
    return Integer.parseInt(sb.toString(), 2);
  }

  @Override
  public long part1() {

    final int DATA_WIDTH = data.get(0).length();
    String[] gamma = new String[DATA_WIDTH], epsi = new String[DATA_WIDTH];
    for (int i = 0; i < DATA_WIDTH; i++) {
      int ones = 0;
      int zeroes = 0;
      for (String line : data) {
        if (line.charAt(i) == '0') {
          zeroes++;
        } else {
          ones++;
        }
      }
      if (ones > zeroes) {
        gamma[i] = "1";
        epsi[i] = "0";
      } else {
        gamma[i] = "0";
        epsi[i] = "1";
      }
    }

    return toDec(gamma) * toDec(epsi);
  }

  int getGen(List<String> data) {
    List<String> result = new ArrayList<>(data);
    var index = 0;
    while (result.size() > 1) {
      List<String> ones = new ArrayList<>(), zeroes = new ArrayList<>();
      for (String val : result) {
        if (val.charAt(index) == '1') {
          ones.add(val);
        } else {
          zeroes.add(val);
        }
      }
      index++;
      //compare sizes
      result = (ones.size() >= zeroes.size()) ? ones : zeroes;
    }
    return Integer.parseInt(result.get(0), 2);
  }

  int getScrubber(List<String> data) {
    List<String> result = new ArrayList<>(data);
    var index = 0;
    while (result.size() > 1) {
      List<String> ones = new ArrayList<>(), zeroes = new ArrayList<>();
      for (String val : result) {
        if (val.charAt(index) == '1') {
          ones.add(val);
        } else {
          zeroes.add(val);
        }
      }
      index++;
      //compare sizes
      result = (ones.size() < zeroes.size()) ? ones : zeroes;
    }
    return Integer.parseInt(result.get(0), 2);
  }

  @Override
  public long part2() {
    return getGen(data) * getScrubber(data);
  }
}
