/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class DataTools {

  static List<Integer> toIntList(List<String> data) {
    return data
        .stream()
        .map(val -> Integer.parseInt(val)).collect(Collectors.toUnmodifiableList());
  }
}
