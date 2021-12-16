/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Polymer {

  final String template;
  int iterations;
  final Map<String, Character> rules;
  final Map<String, List<String>> expansions;
  final Map<String, Long> frags;
  final Map<Character, Long> stats;


  public Polymer(List<String> data) {
    template = data.get(0);
    iterations = 1;
    rules = initRules(data.subList(2, data.size()));
    expansions = initExpansions();
    frags = initFrags();
    stats = initStats();
  }

  Map<String, Character> initRules(List<String> data) {
    Map<String, Character> map = new HashMap<>();
    for (String line : data) {
      var parts = line.split(" -> ");
      map.put(parts[0], (char)parts[1].charAt(0));
    }
    return map;
  }

  Map<String, List<String>> initExpansions() {
    Map<String, List<String>> map = new HashMap<>();
    for (var rule : rules.keySet()) {
      var c = rules.get(rule);
      map.put(rule, List.of(
          String.valueOf(rule.charAt(0)).concat(String.valueOf(c)),
          String.valueOf(c).concat(String.valueOf(rule.charAt(1)))
      ));
    }
    return map;
  }

  Map<String, Long> initFrags() {
    Map<String, Long> map = new HashMap<>();
    for (int i = 0; i < template.length() - 1; i++) {
      var key = template.substring(i, i + 2);
      map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
    }
    return map;
  }

  Map<Character, Long> initStats() {
    Map<Character, Long> map = new HashMap<>();
    for (var key : template.toCharArray()) {
      map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
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
    List<Addition> additions = new ArrayList<>();
    for (int i = 0; i < polymer.length() - 1; i++) {
      var ruleKey = polymer.substring(i, i + 2);
      var rule = rules.get(ruleKey);
      if (rule != null) {
        additions.add(new Addition(rule, i + 1 + additions.size()));
      }
    }
    additions.forEach(addition ->
        polymer.insert(addition.index, addition.val)
    );
    return polymer;
  }

  public long getScore() {
    List<Long> vals = new ArrayList<>(stats.values());
    Collections.sort(vals);
    return vals.get(vals.size() - 1) - vals.get(0);
  }

  public long getScore(String polymer) {
    Map<Character, Long> map = new HashMap<>();
    for (char c : polymer.toCharArray()) {
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1L);
      }
    }
    List<Long> vals = new ArrayList<>(map.values());
    Collections.sort(vals);
    return vals.get(vals.size() - 1) - vals.get(0);
  }

  void calcFrags() {

  }

  public void expand() {
    for (int i = 0; i < iterations; i++) {
      Set<String> removes = new HashSet<>();
      for (var rule : rules.keySet()) {
        if (frags.containsKey(rule)) {
          removes.add(rule);
        }
      }
      Map<String, Long> updates = new HashMap<>();
      for (var toRemove : removes) {
        var numFrags = frags.remove(toRemove);
        for (var expKey : expansions.get(toRemove)) {
          updates.put(
              expKey,
              updates.containsKey(expKey) ? updates.get(expKey) + numFrags : numFrags
          );
        }
        var statsKey = rules.get(toRemove);
        stats.put(
            statsKey, stats.containsKey(statsKey) ? stats.get(statsKey) + numFrags : numFrags
        );
      }
      for (var update : updates.keySet()) {
        frags.put(
            update,
            frags.containsKey(update) ?
                frags.get(update) + updates.get(update) : updates.get(update)
        );
      }
    }
  }
}
