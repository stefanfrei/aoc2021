/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day16;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Decoder {

  final String src;

  public Decoder(List<String> data) {
    src = data.get(0);
  }

  String hexToBin(String hex) {
    var isOdd = hex.length() % 2 == 1;
    StringBuilder b = new StringBuilder(isOdd ? 4 * (hex.length() + 1) : 4 * hex.length());
    if (isOdd) b.append("0000");
    var firstDigit = Byte.parseByte(String.valueOf(hex.charAt(0)), 16);
    if (firstDigit < 8) b.append("0");
    if (firstDigit < 4) b.append("0");
    if (firstDigit < 2) b.append("0");
    b.append(new BigInteger(hex, 16).toString(2));
    return b.toString();
  }
}
