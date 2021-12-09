/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Day2 extends Day {

  Day2() {
    this(RUN_TYPE.PROD);
  }

  Day2(RUN_TYPE runType) {
    super(runType);
  }

  public long part1() {

    int depth = 0;
    int pos = 0;
    data.forEach(System.out::println);
    for (String line : data) {
      String parts[] = line.split(" ");
      switch(parts[0]) {
        case "up":
          depth -= Integer.parseInt(parts[1]);
          break;
        case "down":
          depth += Integer.parseInt(parts[1]);
          break;
        case "forward":
          pos += Integer.parseInt(parts[1]);
          break;
      }
    }
    return depth * pos;
  }

  public long part2() {

    int angle = 0;
    int depth = 0;
    int pos = 0;
    data.forEach(System.out::println);
    for (String line : data) {
      String parts[] = line.split(" ");
      switch(parts[0]) {
        case "up":
          angle -= Integer.parseInt(parts[1]);
          break;
        case "down":
          angle += Integer.parseInt(parts[1]);
          break;
        case "forward":
          pos += Integer.parseInt(parts[1]);
          if (angle < 0) {
            depth -= Integer.parseInt(parts[1]) * Math.abs(angle);
          } else {
            depth += Integer.parseInt(parts[1]) * angle;
          }

          if (depth < 0) depth = 0;
          break;
      }
    }
    return depth * pos;
  }
}
