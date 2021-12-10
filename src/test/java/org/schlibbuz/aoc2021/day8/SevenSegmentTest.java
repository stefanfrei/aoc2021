/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class SevenSegmentTest {

  @DataProvider(name = "easyDigitsData")
  Object[][] getEasyDigitsData() {
    return new Object[][] {
      {
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf",
        new ArrayList<String>(),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
        new ArrayList<String>(Arrays.asList("fgae", "fg")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf",
        new ArrayList<String>(Arrays.asList("fgae", "fg")),
        TEST_TYPE.SHOULD_FAIL
      },
    };
  }
  @Test(dataProvider = "easyDigitsData")
  public void getEasyDigitsTest(String data, List<String> expected, TEST_TYPE testType) {
    var inst = new SevenSegment(data);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.getEasyDigits(), expected);
    } else {
      assertNotEquals(inst.getEasyDigits(), expected);
    }

  }
}
