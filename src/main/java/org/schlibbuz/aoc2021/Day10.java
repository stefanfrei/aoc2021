/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.schlibbuz.aoc2021.day10.ERROR_TYPE;
import org.schlibbuz.aoc2021.day10.Scanner;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day10 extends Day {

  Day10() {
    this(RUN_TYPE.PROD);
  }

  Day10(RUN_TYPE runType) {
    super(runType);
  }


  @Override
  public long part1() {
    int score = 0;
    for (var code : data) {
      var scanner = new Scanner(code);
      var errs = scanner.validateChunk(code);
      if (errs.size() > 0 && errs.get(0).errType == ERROR_TYPE.CORRUPTED) {
        score += Scanner.PART1_SCORE_MAP.get(errs.get(0).actual);
      }
    }
    return score;
  }

  @Override
  public long part2() {
    List<Long> scores = new ArrayList<>();
    for (var code : data) {
      var scanner = new Scanner(code);
      var errs = scanner.validateChunk(code);
      if (errs.size() > 0 && errs.get(0).errType == ERROR_TYPE.INCOMPLETE) {
        long score = 0;
        for ( var err : errs) {
          score *= 5;
          score += Scanner.PART2_SCORE_MAP.get(err.expected);
        }
        scores.add(score);
      }
    }
    Collections.sort(scores);
    return scores.get(scores.size() / 2);
  }

}
