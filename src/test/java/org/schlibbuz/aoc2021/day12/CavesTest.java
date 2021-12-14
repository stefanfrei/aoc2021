/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day12;

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
public class CavesTest {

  @DataProvider(name = "findPathsFixture")
  Object[][] findPathsFixture() {
    return new Object[][] {
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                new ArrayList<>(Arrays.asList(
//                    "start",
//                    "end"
//                ))
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "end-start"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                new ArrayList<>(Arrays.asList(
//                    "start",
//                    "end"
//                ))
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
      {
        new ArrayList<>(
            Arrays.asList(
                "start-a",
                "a-end"
            )
        ),
        new ArrayList<>(
            Arrays.asList(
                "start,a,end"
            )
        ),
        TEST_TYPE.SHOULD_PASS
      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-a",
//                "a-A",
//                "a-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                new ArrayList<>(Arrays.asList(
//                    "start",
//                    "a",
//                    "end"
//                ))
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-A",
//                "A-a",
//                "A-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                new ArrayList<>(Arrays.asList(
//                    "start",
//                    "a",
//                    "end"
//                )),
//                new ArrayList<>(Arrays.asList(
//                    "start",
//                    "A",
//                    "a",
//                    "A",
//                    "end"
//                ))
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-a",
//                "start-b",
//                "a-end",
//                "b-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                "start,a,end",
//                "start,b,end"
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-a",
//                "start-b",
//                "start-c",
//                "a-A",
//                "b-B",
//                "c-C",
//                "A-end",
//                "B-end",
//                "C-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                "start,a,A,end",
//                "start,b,B,end",
//                "start,c,C,end"
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
//      {
//        new ArrayList<>(
//            Arrays.asList(
//                "start-A",
//                "start-b",
//                "A-c",
//                "A-b",
//                "b-d",
//                "A-end",
//                "b-end"
//            )
//        ),
//        new ArrayList<>(
//            Arrays.asList(
//                "start,A,b,A,c,A,end",
//                "start,A,b,A,end",
//                "start,A,b,end",
//                "start,A,c,A,b,A,end",
//                "start,A,c,A,b,end",
//                "start,A,c,A,end",
//                "start,A,end",
//                "start,b,A,c,A,end",
//                "start,b,A,end",
//                "start,b,end"
//            )
//        ),
//        TEST_TYPE.SHOULD_PASS
//      },
    };
  }
  @Test(dataProvider = "findPathsFixture")
  public void findPathsTest(List<String> data, List<String> expected, TEST_TYPE testType) {
    var inst = new Caves(data);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(
          inst.findPaths().stream().map(path -> path.encode()),
          expected
      );
    } else {
      assertNotEquals(
          inst.findPaths().stream().map(path -> path.encode()),
          expected
      );
    }
  }
}
