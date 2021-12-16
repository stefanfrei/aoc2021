/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day15;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class CavePoint {

  final int x;
  final int y;
  final int risk;
  int riskToEnd;
  boolean isEnd;


  public CavePoint(int x, int y, int risk) {
    this.x = x;
    this.y = y;
    this.risk = risk;
    riskToEnd = 0;
    isEnd = false;
  }

  public CavePoint(int x, int y, int risk, boolean isEnd) {
    this(x, y, risk);
    this.isEnd = isEnd;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + this.x;
    hash = 59 * hash + this.y;
    hash = 59 * hash + this.risk;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CavePoint other = (CavePoint) obj;
    if (this.x != other.x) {
      return false;
    }
    if (this.y != other.y) {
      return false;
    }
    return this.risk == other.risk;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CavePoint{x=").append(x);
    sb.append(", y=").append(y);
    sb.append(", risk=").append(risk);
    sb.append('}');
    return sb.toString();
  }

}
