/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Polymer {

  final String template;
  final Map<String, Character> rules;
  final Map<Character, Integer> elementCounts;
  int iterations;


  public Polymer(List<String> data) {
    template = data.get(0);
    rules = initRules(data.subList(2, data.size()));
    elementCounts = new HashMap<>();
    iterations = 1;
  }

  Map<String, Character> initRules(List<String> data) {
    Map<String, Character> map = new HashMap<>();
    for (String line : data) {
      var parts = line.split(" -> ");
      map.put(parts[0], (char)parts[1].charAt(0));
    }
    return map;
  }

  public Polymer times(int times) {
    iterations = times;
    return this;
  }

  public StringBuilder grow() {
    StringBuilder polymer = new StringBuilder(template);
    System.out.println(String.format("Template   : %s", template));
    for (int i = 1 ; i <= iterations; i++) {
      polymer = grow(polymer);
      System.out.println(String.format("After step %2d: %s", i, polymer.toString()));
    }
    return polymer;
  }

  StringBuilder grow(StringBuilder polymer) {
    return polymer;
  }
}
