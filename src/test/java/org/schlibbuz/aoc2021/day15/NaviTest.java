/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day15;

import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.Set;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class NaviTest {


  @DataProvider(name = "makePathFix")
  Object[][] makePathFix() {
    return new Object[][] {
      {
        List.of(
            "126",
            "138",
            "213"
        ),
        Set.of(
            new CavePoint(0, 1, 1),
            new CavePoint(0, 2, 2),
            new CavePoint(1, 2, 1),
            new CavePoint(2, 2, 3)
        ),
        7
      },
      {
        List.of(
            "11111",
            "99991",
            "11111",
            "19999",
            "11111"
        ),
        Set.of(
            new CavePoint(1, 0, 1),//first horiz line exclusive startpoint(0, 0)
            new CavePoint(2, 0, 1),
            new CavePoint(3, 0, 1),
            new CavePoint(4, 0, 1),

            new CavePoint(4, 1, 1),//vert connector

            new CavePoint(4, 2, 1),//second line
            new CavePoint(3, 2, 1),
            new CavePoint(2, 2, 1),
            new CavePoint(1, 2, 1),
            new CavePoint(0, 2, 1),

            new CavePoint(0, 3, 1),//vert connector

            new CavePoint(0, 4, 1),//third line
            new CavePoint(1, 4, 1),
            new CavePoint(2, 4, 1),
            new CavePoint(3, 4, 1),
            new CavePoint(4, 4, 1)//end
        ),
        7
      },
//      {
//        List.of(
//            "1163751742",
//            "1381373672",
//            "2136511328",
//            "3694931569",
//            "7463417111",
//            "1319128137",
//            "1359912421",
//            "3125421639",
//            "1293138521",
//            "2311944581"
//        ),
//        Set.of(
//            new CavePoint(0, 1, 1),
//            new CavePoint(0, 2, 2),
//            new CavePoint(1, 2, 1),
//            new CavePoint(2, 2, 3),
//            new CavePoint(3, 2, 6),
//            new CavePoint(4, 2, 5),
//            new CavePoint(5, 2, 1),
//            new CavePoint(6, 2, 1),
//            new CavePoint(6, 3, 1),
//            new CavePoint(7, 3, 5),
//            new CavePoint(7, 4, 1),
//            new CavePoint(7, 5, 1),
//            new CavePoint(8, 5, 3),
//            new CavePoint(8, 6, 2),
//            new CavePoint(8, 7, 3),
//            new CavePoint(8, 8, 2),
//            new CavePoint(9, 8, 1),
//            new CavePoint(9, 9, 1)
//        ),
//        40
//      },
    };
  }
  @Test(dataProvider = "makePathFix")
  public void makePathTest(List<String> data, Set<CavePoint> expectedPath, int expectedRiskLevel) {
    var inst = new Navi(data);
    var riskLevel = inst.makePath();
    assertEquals(inst.path, expectedPath);
    assertEquals(riskLevel, expectedRiskLevel);
  }
}
