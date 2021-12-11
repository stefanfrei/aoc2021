/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day10;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class ScannerTest {

  @DataProvider(name = "makeChunksFixture")
  Object[][] makeChunksFixture() {
    return new Object[][] {
      {
        "()()",
        new ArrayList<String>(Arrays.asList("()", "()")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "(<>[])([{<()>}])",
        new ArrayList<String>(Arrays.asList("(<>[])", "([{<()>}])")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "(<>[]){([{<()>}])}",
        new ArrayList<String>(Arrays.asList("(<>[])", "{([{<()>}])}")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "(<>[]){([{<()>}])",
        new ArrayList<String>(Arrays.asList("(<>[])", "{([{<()>}])")),
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "makeChunksFixture")
  public void makeChunksTest(String code, List<String> expected, TEST_TYPE testType) {
    var inst = new Scanner(code);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.makeChunks(code), expected);
    } else {
      assertNotEquals(inst.makeChunks(code), expected);
    }
  }
}
