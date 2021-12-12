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
      {
        "(()(()))(()()())",
        new ArrayList<String>(Arrays.asList("(()(()))", "(()()())")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "(()(()))(()()())(()(()))(()()())",
        new ArrayList<String>(Arrays.asList("(()(()))", "(()()())", "(()(()))", "(()()())")),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "{([(<{}[<>[]}>{[]{[(<()>",
        new ArrayList<String>(Arrays.asList("{([(<{}[<>[]}", ">{[]{[(<()>")),
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

  @DataProvider(name = "isCodeCorruptedFixture")
  Object[][] isCodeCorruptedFixture() {
    return new Object[][] {
      { "{([(<{}[<>[]}>{[]{[(<()>", true, TEST_TYPE.SHOULD_PASS },
      { "[[<[([]))<([[{}[[()]]]", true, TEST_TYPE.SHOULD_PASS },
      { "[{[{({}]{}}([{[{{{}}([]", true, TEST_TYPE.SHOULD_PASS },
      { "[<(<(<(<{}))><([]([]()", true, TEST_TYPE.SHOULD_PASS },
      { "<{([([[(<>()){}]>(<<{{", true, TEST_TYPE.SHOULD_PASS },
    };
  }
  @Test(dataProvider = "isCodeCorruptedFixture")
  public void isCodeCorruptedTest(String code, boolean expected, TEST_TYPE testType) {
    var inst = new Scanner(code);
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.isCodeCorrupted(), expected);
    } else {
      assertNotEquals(inst.isCodeCorrupted(), expected);
    }
  }

  @DataProvider(name = "validateChunkFixture")
  Object[][] validateChunkFixture() {
    return new Object[][] {
      {
        "(>",
        new ArrayList<>(
            Arrays.asList(
                new ScannerError(ERROR_TYPE.CORRUPTED, 1, '>', ')')
            )
        ),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "(>{)",
        new ArrayList<>(
            Arrays.asList(
                new ScannerError(ERROR_TYPE.CORRUPTED, 1, '>', ')'),
                new ScannerError(ERROR_TYPE.CORRUPTED, 3, ')', '}')
            )
        ),
        TEST_TYPE.SHOULD_PASS
      },
      {
        "([]>{)",
        new ArrayList<>(
            Arrays.asList(
                new ScannerError(ERROR_TYPE.CORRUPTED, 3, '>', ')'),
                new ScannerError(ERROR_TYPE.CORRUPTED, 5, ')', '}')
            )
        ),
        TEST_TYPE.SHOULD_PASS
      },
    };
  }
  @Test(dataProvider = "validateChunkFixture")
  public void validateChunkTest(String chunk, List<ScannerError> expected, TEST_TYPE testType) {
    var inst = new Scanner("()");
    if (testType == TEST_TYPE.SHOULD_PASS) {
      assertEquals(inst.validateChunk(chunk), expected);
    } else {
      assertNotEquals(inst.validateChunk(chunk), expected);
    }
  }
}
