/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Navi {

  static final int BASE_10 = 10;
  final int dataHeight;
  final int dataWidth;
  final CavePoint start;
  public final Map<Point, CavePoint> map;
  public final Map<Point, Map<String, CavePoint>> adjacents;
  public final Set<CavePoint> path;


  public Navi(List<String> data) {
    dataHeight = data.size();
    dataWidth = data.get(0).length();
    map = initMap(data);
    adjacents = initAdjacents();
    start = map.get(new Point(0, 0));
    path = new HashSet<>();
  }

  final Map<Point, CavePoint> initMap(List<String> data) {
    Map<Point, CavePoint> initial = new HashMap<>();
    int y = 0;
    for (var line : data) {
      int x = 0;
      for ( var c : line.toCharArray()) {
        var key = new Point(x, y);
        initial.put(key, new CavePoint(x++, y, Integer.parseInt(String.valueOf(c), BASE_10)));
      }
      y++;
    }
    var endPoint = new Point(dataWidth - 1, dataHeight - 1);
    initial.put(endPoint, new CavePoint(endPoint.x, endPoint.y, initial.get(endPoint).risk, true));
    return initial;
  }

  Map<Point, Map<String, CavePoint>> initAdjacents() {
    Map<Point, Map<String, CavePoint>> initial = new HashMap<>();
    for (var point : map.keySet()) {
      Map<String, CavePoint> entry = new HashMap<>();
      if (point.y > 0) entry.put("top", map.get(new Point(point.x, point.y - 1)));
      if (point.x < dataWidth - 1) entry.put("right", map.get(new Point(point.x + 1, point.y)));
      if (point.y < dataHeight - 1) entry.put("bott", map.get(new Point(point.x, point.y + 1)));
      if (point.x > 0) entry.put("left", map.get(new Point(point.x - 1, point.y)));
      initial.put(point, entry);
    }
    return initial;
  }

  void scan() {

  }

  public int makePath() {
    path.add(new CavePoint(0, 1, 1));
    path.add(new CavePoint(0, 2, 2));
    path.add(new CavePoint(1, 2, 1));
    path.add(new CavePoint(2, 2, 3));
    return path.stream().reduce(0, (sum, point) -> sum + point.risk, Integer::sum);
  }
}
