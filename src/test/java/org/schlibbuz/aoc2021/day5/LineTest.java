/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day5;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class LineTest {
  @DataProvider(name = "lineData")
  Object[][] getLinesData() {
    return new Object[][] {
      {0, 0, 0, 3, "Line{xS=0, yS=0, xE=0, yE=3, lineType=VERT}", TEST_TYPE.SHOULD_PASS},
      {0, 3, 0, 0, "Line{xS=0, yS=0, xE=0, yE=3, lineType=VERT}", TEST_TYPE.SHOULD_PASS},
      {3, 0, 0, 0, "Line{xS=0, yS=0, xE=3, yE=0, lineType=HORIZ}", TEST_TYPE.SHOULD_PASS},
      {0, 0, 3, 0, "Line{xS=0, yS=0, xE=3, yE=0, lineType=HORIZ}", TEST_TYPE.SHOULD_PASS},
      {1, 1, 3, 3, "Line{xS=1, yS=1, xE=3, yE=3, lineType=DIAG_LTR_DESC}", TEST_TYPE.SHOULD_PASS},
      {3, 3, 1, 1, "Line{xS=1, yS=1, xE=3, yE=3, lineType=DIAG_LTR_DESC}", TEST_TYPE.SHOULD_PASS},
      {0, 3, 3, 0, "Line{xS=0, yS=3, xE=3, yE=0, lineType=DIAG_LTR_ASC}", TEST_TYPE.SHOULD_PASS},
      {3, 0, 0, 3, "Line{xS=0, yS=3, xE=3, yE=0, lineType=DIAG_LTR_ASC}", TEST_TYPE.SHOULD_PASS},

      {0, 0, 0, 7, "Line{xS=0, yS=0, xE=0, yE=3, lineType=VERT}", TEST_TYPE.SHOULD_FAIL},
    };
  }
  @Test(dataProvider = "lineData")
  public void constructTest(int xS, int yS, int xE, int yE, String expected, TEST_TYPE testType) {
    var inst = new Line(xS, yS, xE, yE);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.toString(), expected);
    } else {
      assertNotEquals(inst.toString(), expected);
    }
  }


}
