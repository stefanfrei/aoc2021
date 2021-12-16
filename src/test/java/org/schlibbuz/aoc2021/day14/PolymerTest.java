/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.schlibbuz.aoc2021.day14;

import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author stefanfrei
 */
public class PolymerTest {
  @DataProvider(name = "growFix")
  Object[][] growFix() {
    return new Object[][] {
      {
        List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        ),
        1,
        "NCNBCHB"
      },
      {
        List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        ),
        2,
        "NBCCNBBBCBHCB"
      },
      {
        List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        ),
        3,
        "NBBBCNCCNBBNBNBBCHBHHBCHB"
      },
      {
        List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        ),
        4,
        "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"
      },
    };
  }
  @Test(dataProvider = "growFix")
  public void growTest(List<String> data, int iterations, String expected) {
    var inst  = new Polymer(data);
    assertEquals(inst.times(iterations).grow().toString(), expected);
  }

  @DataProvider(name = "expandFix")
  Object[][] expandFix() {
    return new Object[][] {
      {
        List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
        ),
        Map.of(
            "NC", 1L,
            "CN", 1L,
            "NB", 1L,
            "BC", 1L,
            "CH", 1L,
            "HB", 1L
        ),
        Map.of(
            'N', 2L,
            'C', 2L,
            'B', 2L,
            'H', 1L
        )
      },
    };
  }
  @Test(dataProvider = "expandFix")
  public void expandTest(
      List<String> data,
      Map<String, Long> expFrags,
      Map<Character, Long> expStats
  ) {
    var inst = new Polymer(data);
    inst.expand();
    assertEquals(inst.frags, expFrags);
    assertEquals(inst.stats, expStats);
  }
}
