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
public enum LENGTH_TYPE {
  BIT_11(11), BIT_15(15);

  private static final Map<Integer,LENGTH_TYPE> lookup = new HashMap<>();

  static {
    for(LENGTH_TYPE w : EnumSet.allOf(LENGTH_TYPE.class)) lookup.put(w.val(), w);
  }

  final int val;

  private LENGTH_TYPE(int val) {
    this.val = val;
  }

  public int val() { return val; }

  public static LENGTH_TYPE get(int val) {
    return lookup.get(val);
  }
}
