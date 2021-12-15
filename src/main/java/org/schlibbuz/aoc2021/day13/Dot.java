/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day13;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Dot {

  final int x;
  final int y;


  public Dot(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + this.x;
    hash = 97 * hash + this.y;
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
    final Dot other = (Dot) obj;
    if (this.x != other.x) {
      return false;
    }
    return this.y == other.y;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Dot{x=").append(x);
    sb.append(", y=").append(y);
    sb.append('}');
    return sb.toString();
  }
  
}
