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
public class Fold {

  FOLD_TYPE foldType;
  int index;


  public Fold(FOLD_TYPE foldType, int index) {
    this.foldType = foldType;
    this.index = index;
  }
}
