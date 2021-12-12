/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day10;

import java.util.Objects;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class ScannerError {

  public final ERROR_TYPE errType;
  public final int index;
  public final char actual;
  public final char expected;


  public ScannerError(ERROR_TYPE errType, int index, char actual, char expected) {
    this.errType = errType;
    this.index = index;
    this.actual = actual;
    this.expected = expected;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + Objects.hashCode(this.errType);
    hash = 23 * hash + this.index;
    hash = 23 * hash + this.actual;
    hash = 23 * hash + this.expected;
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
    final ScannerError other = (ScannerError) obj;
    if (this.index != other.index) {
      return false;
    }
    if (this.actual != other.actual) {
      return false;
    }
    if (this.expected != other.expected) {
      return false;
    }
    if (this.errType != other.errType) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ScannerError{errType=").append(errType);
    sb.append(", index=").append(index);
    sb.append(", actual=").append(actual);
    sb.append(", expected=").append(expected);
    sb.append('}');
    return sb.toString();
  }

}
