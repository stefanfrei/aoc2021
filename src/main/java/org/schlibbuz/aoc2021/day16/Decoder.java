/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Decoder {

  static final byte BINARY = 2;
  static final byte DECIMAL = 10;
  static final byte VERSION_LENGTH = 3;
  static final byte TYPE_LENGTH = 3;
  static final byte DIGIT_LENGTH = 5;
  static final byte LENGTH_TYPE_11 = 11;
  static final byte LENGTH_TYPE_15 = 15;

  final String src;


  public Decoder(List<String> data) {
    this(data.get(0));
  }

  public Decoder(String data) {
    src = hexToBin(data);
  }

  final String hexToBin(String hex) {
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

  int toInt(String s) {
    System.out.println(s);
    System.out.println(Integer.parseInt(s, BINARY));
    return Integer.parseInt(s, BINARY);
  }

  LiteralPacket addLiteralPacket(List<IPacket> packets) {
    return null;
  }

  public List<IPacket> binToPackets(String bin) {
    List<IPacket> packets = new ArrayList<>();
    int i = 0;
    while (i < bin.length()) {
      var version = VERSION.get(toInt(bin.substring(i, i + VERSION_LENGTH)));
      i += VERSION_LENGTH;
      PACKET_TYPE type = PACKET_TYPE.get(toInt(bin.substring(i, i + TYPE_LENGTH)));
      i += TYPE_LENGTH;
      if (type == PACKET_TYPE.LITERAL) {
        var isLastDigit = false;
        StringBuilder b = new StringBuilder();
        for (;;) {
          isLastDigit = bin.charAt(i) == '0';
          b.append(bin.substring(i + 1, i + DIGIT_LENGTH));
          System.out.println(b.toString());
          i += DIGIT_LENGTH;
          if (isLastDigit) break;
        }
        var val = toInt(b.toString());
        packets.add(new LiteralPacket(version, val));
        continue;
      }
      var lengthType = bin.charAt(i++) == '0' ? LENGTH_TYPE.BIT_15 : LENGTH_TYPE.BIT_11;
      var subPacketsAmount = toInt(bin.substring(i, i + lengthType.val()));
      i += lengthType.val;
      packets.add(new OperatorPacket(version, lengthType, subPacketsAmount));
    }
    return packets;
  }

}
