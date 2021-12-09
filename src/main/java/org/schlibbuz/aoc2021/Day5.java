/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.schlibbuz.aoc2021.day5.Line;
import org.schlibbuz.aoc2021.day5.Point;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Day5 extends Day {

  final Map<String, Integer> map;

  Day5() {
    this(RUN_TYPE.PROD);
  }

  Day5(RUN_TYPE runType) {
    super(runType);
    map = new HashMap<>();
  }

  Map<Point, Integer> loadMap(List<Line> lines) {
    Map<Point, Integer> map = new HashMap<>();
    for (var line : lines) {
      System.out.println(line);
      line.getPoints().forEach(System.out::println);
      line.getPoints().forEach(point -> {
        if (map.containsKey(point)) {
          map.put(point, map.get(point) + 1);
        } else {
          map.put(point, 1);
        }
      });
    }
    return map;
  }

  int countDangerous(Map<Point, Integer> map) {
    return (int)map.values().stream().filter(val -> val > 1).count();
  }


  Point getDiagramBounds(Map<Point, Integer> map) {
    int boundsX = 0;
    int boundsY = 0;

    for (var point : map.keySet()) {
      if (point.x > boundsX) boundsX = point.x;
      if (point.y > boundsY) boundsY = point.y;
    }
    return new Point(boundsX, boundsY);
  }

  List<char[]> initDiagram(Point bounds) {
    List<char[]> diagramTemplate = new ArrayList<>();
    char[] lineTemplate = new char[bounds.x + 1];
    for (int i = 0; i < lineTemplate.length; i++) lineTemplate[i] = '.';
    for (int i = 0; i <= bounds.y; i++) {
      diagramTemplate.add(lineTemplate.clone());
    }
    return diagramTemplate;
  }

  String mapToDiagram(Map<Point, Integer> map) {
    //find dimensions
    Point bounds = getDiagramBounds(map);
    var diagram = initDiagram(bounds);
    var sb = new StringBuilder( (bounds.x + 2) * (bounds.y + 1));
    for (var point : map.keySet()) diagram.get(point.y)[point.x] = String.valueOf(map.get(point)).charAt(0);
    for (var line : diagram) {
      sb.append(line).append('\n');
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  List<Integer> loadCoords(String dataLine) {
    return Arrays.asList(
        dataLine.split(" -> |\\,")
    ).stream().map(item -> Integer.parseInt(item)).collect(Collectors.toUnmodifiableList());
  }

  @Override
  public long part1() {
    var lines = data
        .stream()
        .map(line -> new Line(loadCoords(line)))
        .filter(line -> line.pS.x == line.pE.x || line.pS.y == line.pE.y)
        .collect(Collectors.toList());

    return countDangerous(loadMap(lines));
  }

  @Override
  public long part2() {
    var lines = data
        .stream()
        .map(line -> new Line(loadCoords(line)))
        .collect(Collectors.toList());
    var map = loadMap(lines);
    var diagram = mapToDiagram(map);
    System.out.println(diagram);
    return countDangerous(map);
  }

}
