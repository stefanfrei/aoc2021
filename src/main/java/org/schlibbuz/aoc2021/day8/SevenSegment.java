/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class SevenSegment {

  final static int[] possibleSegmentNumbers = {2, 3, 4, 5, 6, 7};
  final static char[] segmentIds = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
  final static List<Integer> easyDigitsSegmentAmounts = new ArrayList<>(
      Arrays.asList(
          2, // 1
          3, // 7
          4, // 4
          7  // 8
      )
  );
  final static List<Integer> hardDigitsSegmentAmounts = new ArrayList<>(
      Arrays.asList(
          5, // 2, 3, 5
          6  // 0, 6, 9
      )
  );
  public final List<String> signalPatterns;
  public final List<String> fourDigitOutputValue;
  public final Map<Integer, List<String>> testResults;
  public final Map<Character, Character> wireMappings;
  public final Map<String, Integer> signalPatternToIntegerMap;

  public SevenSegment(String data) {
    testResults = initTestResults();
    wireMappings = initWireMappings();
    var parts = data.split(" \\| ");
    signalPatterns = initSignalPatterns(parts[0].split(" "));
    fourDigitOutputValue = initFourDigitOutputValue(parts[1].split(" "));
    signalPatternToIntegerMap = initSignalPatternToIntegerMap();
  }

  List<String> initSignalPatterns(String[] signalPatterns) {
    return Arrays.asList(signalPatterns)
        .stream()
        .map(item -> sortString(item))
        .collect(Collectors.toList());
  }

  List<String> initFourDigitOutputValue(String[] fourDigitOutputValue) {
    return Arrays.asList(fourDigitOutputValue)
        .stream()
        .map(item -> sortString(item))
        .collect(Collectors.toList());
  }

  Map<Integer, List<String>> initTestResults() {
    Map<Integer, List<String>> map = new HashMap<>();
    for (var possibleSegmentNumber : possibleSegmentNumbers) {
      map.put(possibleSegmentNumber, new ArrayList<>());
    }
    return map;
  }

  Map<Character, Character> initWireMappings() {
    Map<Character, Character> map = new HashMap<>();
    for (var c : segmentIds) map.put(c, null);
    return map;
  }

  Map<String, Integer> initSignalPatternToIntegerMap() {
    Map<String, Integer> map = new HashMap<>();
    for (var signalPattern : signalPatterns) map.put(signalPattern, null);
    return map;
  }

  public List<String> getEasyDigits() {
    return fourDigitOutputValue
        .stream()
        .filter(val -> easyDigitsSegmentAmounts.contains(val.length()))
        .collect(Collectors.toList());
  }

  String sortString(String input) {
    char[] tmp = input.toCharArray();
    Arrays.sort(tmp);
    return new String(tmp);
  }

  public int decode() {
    //simple stuff first
    var oneCode = signalPatterns.stream().filter(item -> item.length() == 2).findFirst().get();
    var sevenCode = signalPatterns.stream().filter(item -> item.length() == 3).findFirst().get();
    var fourCode = signalPatterns.stream().filter(item -> item.length() == 4).findFirst().get();
    var eightCode = signalPatterns.stream().filter(item -> item.length() == 7).findFirst().get();
    signalPatternToIntegerMap.put(oneCode, 1);
    signalPatternToIntegerMap.put(sevenCode, 7);
    signalPatternToIntegerMap.put(fourCode, 4);
    signalPatternToIntegerMap.put(eightCode, 8);

    wireMappings.put('a', findA(oneCode, sevenCode));
    var twoThreeFive = signalPatterns
        .stream()
        .filter(item -> item.length() == 5)
        .collect(Collectors.toList());
    wireMappings.put('d', findD(fourCode, twoThreeFive));

    //zero is 8 minus d segment
    var zeroCode = eightCode.replace(wireMappings.get('d').toString(), "");
    signalPatternToIntegerMap.put(zeroCode, 0);

    var sixNine = signalPatterns
        .stream()
        .filter(item -> item.length() == 6 && !item.equals(zeroCode))
        .collect(Collectors.toList());
    wireMappings.put('c', findC(oneCode, eightCode, sixNine));
    wireMappings.put('f', oneCode.replace(wireMappings.get('c').toString(), "").charAt(0));
    for (var elem : sixNine) {
      if (elem.contains(wireMappings.get('c').toString())) {
        signalPatternToIntegerMap.put(elem, 9);
      } else {
        signalPatternToIntegerMap.put(elem, 6);
      }
    }
    var sixCode = getSignalPattern(6);
    var nineCode = getSignalPattern(9);
    wireMappings.put('e', findE(sixCode, nineCode));
    wireMappings.put('g', findG(fourCode, eightCode));
    wireMappings.put('b', findB());
    signalPatternToIntegerMap.put(constructSignalPattern(new char[] {'a', 'c', 'd', 'e', 'g'}), 2);
    signalPatternToIntegerMap.put(constructSignalPattern(new char[] {'a', 'c', 'd', 'f', 'g'}), 3);
    signalPatternToIntegerMap.put(constructSignalPattern(new char[] {'a', 'b', 'd', 'f', 'g'}), 5);
    StringBuilder res = new StringBuilder();
    for (var signalPattern : fourDigitOutputValue) {
      res.append(signalPatternToIntegerMap.get(signalPattern));
    }
    return Integer.parseInt(res.toString(), 10);
  }

  String constructSignalPattern(char[] keys) {
    StringBuilder res = new StringBuilder();
    for (var c : keys) {
      res.append(wireMappings.get(c));
    }
    return sortString(res.toString());
  }
  String getSignalPattern(int val) {
    for (var key : signalPatternToIntegerMap.keySet()) {
      var number = signalPatternToIntegerMap.get(key);
      if (number != null && number == val) return key;
    }
    return null;
  }

  String findCommons(List<String> options) {
    Set<Character> chars = new HashSet<>();
    for (var option : options) {
      for (var c : option.toCharArray()) chars.add(c);
    }

    StringBuilder result = new StringBuilder();
    for (var c : chars) {
      boolean common = false;
      for (var option : options) {
        if (option.indexOf(c) == -1) {
          common = false;
          break;
        }
        common = true;
      }
      if (common) result.append(c);
    }
    return result.toString();
  }

  private char findA(String one, String seven) {
    for (var c : seven.toCharArray()) {
      if (one.indexOf(c) == -1) return c;
    }
    return 'x';
  }

  private char findB() {
    String res = "abcdefg";
    for (var key : wireMappings.keySet()) {
      var val = wireMappings.get(key);
      if (val != null) res = res.replace(val.toString(), "");
    }
    return res.charAt(0);
  }

  private char findC(String one, String eight, List<String> sixNine) {
    for (var option : sixNine) {
      if (option.indexOf(one.charAt(0)) == -1 || option.indexOf(one.charAt(1)) == -1) {
        // is six-code, 8 - 6 is c-segment
        return eight.replaceAll("[" + option + "]", "").charAt(0);
      }
    }
    return 'x';
  }

  private char findD(String four, List<String> twoThreeFive) {
    // all digits using 5 segments use a, d and g, but only digit 4 uses d too
    var commons = findCommons(twoThreeFive);
    return findCommons(
        Arrays.asList(commons, four)
    ).charAt(0);
  }

  private char findE(String six, String nine) {
    String res = six;
    for (var c : nine.toCharArray()) {
      res = res.replace(String.valueOf(c), "");
    }
    return res.charAt(0);
  }

  private char findG(String four, String eight) {
    String res = eight;
    for (var c : four.toCharArray()) {
      res = res.replace(String.valueOf(c), "");
    }
    res = res.replace(wireMappings.get('a').toString(), "");
    res = res.replace(wireMappings.get('e').toString(), "");
    return res.charAt(0);
  }
}
