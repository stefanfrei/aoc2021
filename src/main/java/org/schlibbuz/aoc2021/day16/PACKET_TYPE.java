/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.schlibbuz.aoc2021.day16;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stefanfrei
 */
public enum PACKET_TYPE {
  OPERATOR(3), LITERAL(4);

  private static final Map<Integer,PACKET_TYPE> lookup = new HashMap<>();

  static {
    for(PACKET_TYPE w : EnumSet.allOf(PACKET_TYPE.class)) lookup.put(w.id(), w);
  }

  final int id;

  private PACKET_TYPE(int id) {
    this.id = id;
  }

  public int id() { return id; }

  public static PACKET_TYPE get(int id) {
    return lookup.get(id);
  }
}
