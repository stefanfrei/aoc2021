/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.schlibbuz.aoc2021.TEST_TYPE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class HeightMapTest {

  @DataProvider(name = "basinFixture")
  Object[][] basinFixture() {
    return new Object[][] {
      {
        new ArrayList<String>(
            Arrays.asList(
                "989",
                "212",
                "989"
            )
        ),
        5,
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "basinFixture")
  public void getBasinTest(List<String> data, int expected, TEST_TYPE testType) {
    var inst = new HeightMap(data);
    var lowPointIndex = inst.getLowPointsIndices().get(0);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.getBasin(lowPointIndex).length, expected);
    } else {
      assertNotEquals(inst.getBasin(lowPointIndex).length, expected);
    }
  }
}
