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
public enum DOOR {

  TOP(0), RIGHT(1), BOTT(2), LEFT(3);

  final int val;

  
  DOOR(int val) {
    this.val = val;
  }

}
