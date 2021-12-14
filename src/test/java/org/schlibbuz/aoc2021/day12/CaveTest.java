/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

import org.schlibbuz.aoc2021.TEST_TYPE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class CaveTest {

  @DataProvider(name = "getCaveTypeFixture")
  Object[][] getCaveTypeFixture() {
    return new Object[][] {
      {
        "ad",
        CAVE_TYPE.SMALL,
        TEST_TYPE.SHOULD_PASS
      },
      {
        "BC",
        CAVE_TYPE.BIG,
        TEST_TYPE.SHOULD_PASS
      },
      {
        "ad",
        CAVE_TYPE.BIG,
        TEST_TYPE.SHOULD_FAIL
      },
      {
        "BC",
        CAVE_TYPE.SMALL,
        TEST_TYPE.SHOULD_FAIL
      },
    };
  }
  @Test(dataProvider = "getCaveTypeFixture")
  public void getCaveTypeTest(String id, CAVE_TYPE expected, TEST_TYPE testType) {
    var inst = new Cave(id);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.caveType, expected);
    } else {
      assertNotEquals(inst.caveType, expected);
    }
  }
}
