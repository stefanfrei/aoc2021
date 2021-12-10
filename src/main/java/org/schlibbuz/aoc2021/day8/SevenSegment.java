/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public final List<String> signalPatterns;
  public final List<String> fourDigitOutputValue;
  public final Map<Integer, List<String>> testResults;
  public final Map<Character, Character> wireMappings;

  public SevenSegment(String data) {
    testResults = initTestResults();
    wireMappings = initWireMappings();
    var parts = data.split(" \\| ");
    signalPatterns = initSignalPatterns(parts[0].split(" "));
    fourDigitOutputValue = initFourDigitOutputValue(parts[1].split(" "));
  }

  List<String> initSignalPatterns(String[] signalPatterns) {
    return Arrays.asList(signalPatterns);
  }

  List<String> initFourDigitOutputValue(String[] fourDigitOutputValue) {
    return Arrays.asList(fourDigitOutputValue);
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

  public List<String> getEasyDigits() {
    return fourDigitOutputValue
        .stream()
        .filter(val -> easyDigitsSegmentAmounts.contains(val.length()))
        .collect(Collectors.toList());
  }

  void doUniqueMappings(String[] results) {
    for (var result : results) {

    }
  }

  String findInput(String result) {
    return null;
  }

  char findA(String one, String seven) {
    for (var c : seven.toCharArray()) {
      if (one.indexOf(c) == -1) return c;
    }
    return 'x';
  }
}
