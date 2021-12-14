/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class CavesTest {

  @DataProvider(name = "findPathsFixture")
  Object[][] findPathsFixture() {
    return new Object[][] {
      {
        new ArrayList<>(
            Arrays.asList(
                "start-A",
                "A-a",
                "A-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,A,end",
                "start,A,a,A,end"
            )
        ),
        false,
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(
            Arrays.asList(
                "start-a",
                "start-b",
                "a-end",
                "b-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,a,end",
                "start,b,end"
            )
        ),
        false,
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(
            Arrays.asList(
                "start-a",
                "start-b",
                "start-c",
                "a-A",
                "b-B",
                "c-C",
                "A-end",
                "B-end",
                "C-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,a,A,end",
                "start,b,B,end",
                "start,c,C,end"
            )
        ),
        false,
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(
            Arrays.asList(
                "start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,A,end",
                "start,b,end",
                "start,A,b,end",
                "start,b,A,end",
                "start,A,b,A,end",
                "start,A,c,A,end",
                "start,A,c,A,b,end",
                "start,b,A,c,A,end",
                "start,A,b,A,c,A,end",
                "start,A,c,A,b,A,end"
            )
        ),
        false,
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(
            Arrays.asList(
                "start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,A,end",
                "start,b,end",
                "start,A,b,end",
                "start,b,A,end",
                "start,A,b,A,end",
                "start,A,c,A,end",
                "start,b,A,b,end",
                "start,b,d,b,end",
                "start,A,b,A,b,end",
                "start,A,b,d,b,end",
                "start,A,c,A,b,end",
                "start,b,A,b,A,end",
                "start,b,A,c,A,end",
                "start,b,d,b,A,end",
                "start,A,b,A,b,A,end",
                "start,A,b,A,c,A,end",
                "start,A,b,d,b,A,end",
                "start,A,c,A,b,A,end",
                "start,A,c,A,c,A,end",
                "start,b,A,c,A,b,end",
                "start,A,b,A,c,A,b,end",
                "start,A,c,A,b,A,b,end",
                "start,A,c,A,b,d,b,end",
                "start,A,c,A,c,A,b,end",
                "start,b,A,b,A,c,A,end",
                "start,b,A,c,A,b,A,end",
                "start,b,A,c,A,c,A,end",
                "start,b,d,b,A,c,A,end",
                "start,A,b,A,b,A,c,A,end",
                "start,A,b,A,c,A,b,A,end",
                "start,A,b,A,c,A,c,A,end",
                "start,A,b,d,b,A,c,A,end",
                "start,A,c,A,b,A,b,A,end",
                "start,A,c,A,b,A,c,A,end",
                "start,A,c,A,b,d,b,A,end",
                "start,A,c,A,c,A,b,A,end"
            )
        ),
        true,
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "findPathsFixture")
  public void findPathsTest(List<String> data, List<String> expected, boolean part2Rules, TEST_TYPE testType) {
    var inst = new Caves(data);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(
          inst.findPaths(part2Rules).stream().map(path -> path.encode()).collect(Collectors.toList()),
          expected
      );
    } else {
      assertNotEquals(
          inst.findPaths(part2Rules).stream().map(path -> path.encode()).collect(Collectors.toList()),
          expected
      );
    }
  }
}
