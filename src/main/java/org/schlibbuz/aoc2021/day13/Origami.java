/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Origami {

  static final char DOT = '#';
  static final char NUL = '.';
  static final char LF = '\n';
  static final Pattern COORD_PATTERN = Pattern.compile("[\\d]+\\,[\\d]+");

  int dataWidth;
  int dataHeight;
  public Set<Dot> dots;
  public final List<Fold> folds;


  public Origami(List<String> data) {
    dataWidth = 0;
    dataHeight = 0;
    dots = initDots(data);
    folds = initFolds(data);
  }

  Set<Dot> initDots(List<String> data) {
    Set<Dot> set = new HashSet<>();

    List<String> dotsData = data.stream()
        .filter(line -> COORD_PATTERN.matcher(line).find())
        .collect(Collectors.toList());

    for (var dotLine : dotsData) {
      var coords = Arrays.asList(dotLine.split(",")).stream()
          .map(coord -> Integer.parseInt(coord, 10)).collect(Collectors.toList());
      var x = coords.get(0);
      if (x + 1 > dataWidth) dataWidth = x + 1;
      var y = coords.get(1);
      if (y + 1 > dataHeight) dataHeight = y + 1;

      set.add(new Dot(x, y));
    }
    return set;
  }

  List<Fold> initFolds(List<String> data) {
    List<Fold> list = new ArrayList<>();
    var foldsData = data.stream().filter(line -> line.startsWith("fold"))
        .collect(Collectors.toList());
    for (var foldData : foldsData) {
      String[] parts = foldData.replace("fold along ", "").split("=");
      list.add(new Fold(
          parts[0].equals("y") ? FOLD_TYPE.VERT : FOLD_TYPE.HORIZ,
          Integer.parseInt(parts[1], 10)
      ));
    }
    return list;
  }

  StringBuilder initPaperBuilder() {
    StringBuilder sb = new StringBuilder((dataWidth + 1) * dataHeight);
    for (int line = 0; line < dataHeight; line++) {
      for (int cell = 0; cell < dataWidth; cell++) sb.append(NUL);
      sb.append(LF);
    }
    return sb;
  }

  public String getPaper() {
    StringBuilder sb = initPaperBuilder();
    for (var dot : dots) {
      var pos = dot.y * (dataWidth + 1) + dot.x;
      sb.setCharAt(pos, DOT);
    }
    return sb.toString();
  }

  Set<Dot> foldHoriz(int index) {
    Set<Dot> newDots = new HashSet<>();
    for (var dot : dots) {
      newDots.add(dot.x < index ? dot : new Dot(index - (dot.x - index), dot.y));
    }
    dataWidth = dataWidth / 2;
    return newDots;
  }

  Set<Dot> foldVert(int index) {
    Set<Dot> newDots = new HashSet<>();
    for (var dot : dots) {
      newDots.add(dot.y < index ? dot : new Dot(dot.x, index - (dot.y - index)));
    }
    dataHeight = dataHeight / 2;
    return newDots;
  }
  public Set<Dot> fold(Fold fold) {
    return fold.foldType == FOLD_TYPE.HORIZ ? foldHoriz(fold.index) : foldVert(fold.index);
  }
}
