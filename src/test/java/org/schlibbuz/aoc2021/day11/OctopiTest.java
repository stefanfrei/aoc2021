/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day11;

import org.schlibbuz.aoc2021.Day11;
import org.schlibbuz.aoc2021.RUN_TYPE;
import org.schlibbuz.aoc2021.TEST_TYPE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class OctopiTest {

  @DataProvider(name = "processStepsFixture")
  Object[][] processStepsFixture() {
    return new Object[][] {
      {
        1,
        "6594254334\n" +
        "3856965822\n" +
        "6375667284\n" +
        "7252447257\n" +
        "7468496589\n" +
        "5278635756\n" +
        "3287952832\n" +
        "7993992245\n" +
        "5957959665\n" +
        "6394862637\n",
        TEST_TYPE.SHOULD_PASS
      },
      {
        2,
        "8807476555\n" +
        "5089087054\n" +
        "8597889608\n" +
        "8485769600\n" +
        "8700908800\n" +
        "6600088989\n" +
        "6800005943\n" +
        "0000007456\n" +
        "9000000876\n" +
        "8700006848\n",
        TEST_TYPE.SHOULD_PASS
      },
      {
        40,
        "6211111981\n" +
        "0421111119\n" +
        "0042111115\n" +
        "0003111115\n" +
        "0003111116\n" +
        "0065611111\n" +
        "0532351111\n" +
        "3322234597\n" +
        "2222222976\n" +
        "2222222762\n",
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "processStepsFixture")
  public void processStepsTest(int maxSteps, String expected, TEST_TYPE testType) {
    var inst = new Octopi(new Day11(RUN_TYPE.TEST).data, maxSteps);
    inst.processSteps();
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.getEnergyLevels(), expected);
    } else {
      assertNotEquals(true, true);
    }
  }
}
