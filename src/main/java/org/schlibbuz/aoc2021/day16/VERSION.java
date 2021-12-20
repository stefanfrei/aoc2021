/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day16;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public enum VERSION {
  V1(1), V2(2), V6(6), V7(7);

  private static final Map<Integer, VERSION> lookup = new HashMap<>();

  static {
    for(VERSION w : EnumSet.allOf(VERSION.class)) lookup.put(w.val(), w);
  }

  final int val;

  private VERSION(int val) {
    this.val = val;
  }

  public int val() { return val; }

  public static VERSION get(int val) {
    return lookup.get(val);
  }

}
