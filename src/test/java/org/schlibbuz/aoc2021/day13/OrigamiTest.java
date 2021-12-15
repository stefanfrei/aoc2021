/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day13;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class OrigamiTest {

  @DataProvider(name = "initPaperBuilderFixture")
  Object[][] initPaperBuilderFixture() {
    return new Object[][] {
      {
        new ArrayList<>(Arrays.asList(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,1",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0"
        )),
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n",
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "initPaperBuilderFixture")
  public void initPaperBuilderTest(List<String> data, String expected, TEST_TYPE testType) {
    var inst = new Origami(data);
    assertEquals(inst.initPaperBuilder().toString(), expected);
  }

  @DataProvider(name = "getPaperFixture")
  Object[][] getPaperFixture() {
    return new Object[][] {
      {
        new ArrayList<>(Arrays.asList(
            "6,0",
            "3,0",
            "9,0"
        )),
        "...#..#..#\n",
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(Arrays.asList(
            "6,0",
            "4,1",
            "3,0",
            "9,0"
        )),
        "...#..#..#\n" +
        "....#.....\n",
        TEST_TYPE.SHOULD_PASS
      },
      {
        new ArrayList<>(Arrays.asList(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0"
        )),
        "...#..#..#.\n" +
        "....#......\n" +
        "...........\n" +
        "#..........\n" +
        "...#....#.#\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        "...........\n" +
        ".#....#.##.\n" +
        "....#......\n" +
        "......#...#\n" +
        "#..........\n" +
        "#.#........\n",
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "getPaperFixture")
  public void getPaperTest(List<String> data, String expected, TEST_TYPE testType) {
    var inst = new Origami(data);
    assertEquals(inst.getPaper(), expected);
  }

  @DataProvider(name = "foldFixture")
  Object[][] foldFixture() {
    return new Object[][] {
      {
        new ArrayList<>(Arrays.asList(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0"
        )),
        new Fold(FOLD_TYPE.VERT, 7),
        Set.of(
           new Dot(0, 0),
           new Dot(2, 0),
           new Dot(3, 0),
           new Dot(6, 0),
           new Dot(9, 0),
           new Dot(0, 1),
           new Dot(4, 1),
           new Dot(6, 2),
           new Dot(10, 2),
           new Dot(0, 3),
           new Dot(4, 3),
           new Dot(1, 4),
           new Dot(3, 4),
           new Dot(6, 4),
           new Dot(8, 4),
           new Dot(9, 4),
           new Dot(10, 4)
        ),
        "#.##..#..#.\n" +
        "#...#......\n" +
        "......#...#\n" +
        "#...#......\n" +
        ".#.#..#.###\n" +
        "...........\n" +
        "...........\n",
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "foldFixture")
  public void foldTest(
      List<String> data,
      Fold fold,
      Set<Dot> expectedDots,
      String expectedPaper,
      TEST_TYPE testType
  ) {
    var inst = new Origami(data);
    inst.dots = inst.fold(fold);
    assertEqualsNoOrder(inst.dots.toArray(), expectedDots.toArray());
    assertEquals(inst.getPaper(), expectedPaper);
  }
}
