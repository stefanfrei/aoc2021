/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day8;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.schlibbuz.aoc2021.TEST_TYPE;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class SevenSegmentTest {
  private static final String DUMMY_DATA =
      "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf";

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
        new ArrayList<String>(Arrays.asList("aefg", "fg")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf",
        new ArrayList<String>(Arrays.asList("aefg", "fg")),
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

  @DataProvider(name = "decodeData")
  Object[][] getDecodeData() {
    return new Object[][] {
      {
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf",
        Stream.of(new char[][] {
          { 'a', 'd' },
          { 'b', 'e' },
          { 'c', 'a' },
          { 'd', 'f' },
          { 'e', 'g' },
          { 'f', 'b' },
          { 'g', 'c' },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1])),
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "decodeData")
  public void decodeTest(String data, Map<Character, Character> expected, TEST_TYPE testType) {
    var inst = new SevenSegment(data);
    inst.decode();
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.wireMappings, expected);
    } else {
      assertNotEquals(inst.wireMappings, expected);
    }
  }

  @DataProvider(name = "commonsData")
  Object[][] getCommonsData() {
    return new Object[][] {
      {
        Stream.of(new String[] { "abc", "adg", "afh"}).collect(Collectors.toUnmodifiableList()),
        "a",
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "commonsData")
  public void findCommonsTest(List<String> data, String expected, TEST_TYPE testType) {
    var inst = new SevenSegment(DUMMY_DATA);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.findCommons(data), expected);
    } else {
      assertNotEquals(inst.findCommons(data), expected);
    }
  }


}
