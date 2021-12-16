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
public class Point {

  final int x;
  final int y;


  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + this.x;
    hash = 83 * hash + this.y;
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
    final Point other = (Point) obj;
    if (this.x != other.x) {
      return false;
    }
    return this.y == other.y;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Point{x=").append(x);
    sb.append(", y=").append(y);
    sb.append('}');
    return sb.toString();
  }
  
}
