/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day5 extends Day implements FileLoader {

  final Map<String, Integer> map;

  Day5() {
    this(RUN_TYPE.PROD);
  }

  Day5(RUN_TYPE runType) {
    super(runType);
    map = new HashMap<>();
  }

  Map<String, Integer> loadMap(List<Line> lines) {
    Map<String, Integer> map = new HashMap<>();
    for (var line : lines) {
      System.out.println(line);
    }
    return map;
  }
  
  @Override
  public int part1() {
    var lines = data
        .stream()
        .map(line -> new Line(line))
        .filter(line -> line.xS == line.xE || line.yS == line.yE)
        .collect(Collectors.toList());
    loadMap(lines);
    return 5;
  }

  @Override
  public int part2() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private enum LINE_TYPE {
    HORIZ, VERT, DIAG_LEFT_DOWN, DIAG_LEFT_UP, DIAG_RIGHT_DOWN, DIAG_RIGHT_UP;
  }
  private final class Line {

    final int xS;
    final int yS;
    final int xE;
    final int yE;
    final LINE_TYPE lineType;
    
    Line(String dataLine) {
      String[] coords = loadCoords(dataLine);
      xS = Integer.parseInt(coords[0]);
      yS = Integer.parseInt(coords[1]);
      xE = Integer.parseInt(coords[2]);
      yE = Integer.parseInt(coords[3]);
      lineType = findLineType();
    }

    Line(int xS, int yS, int xE, int yE) {
      this.xS = xS;
      this.yS = yS;
      this.xE = xE;
      this.yE = yE;
      lineType = findLineType();
    }
    
    LINE_TYPE findLineType() {
      if (xS == xE) return LINE_TYPE.HORIZ;
      if (yS == yE) return LINE_TYPE.VERT;
      return null; //should not happen
    }
    
    String[] loadCoords(String dataLine) {
      return dataLine.split(" -> |\\,");
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder(64);
      sb.append("Line{xS=").append(xS);
      sb.append(", yS=").append(yS);
      sb.append(", xE=").append(xE);
      sb.append(", yE=").append(yE);
      sb.append(", lineType=").append(lineType);
      sb.append('}');
      return sb.toString();
    }

  }

}
