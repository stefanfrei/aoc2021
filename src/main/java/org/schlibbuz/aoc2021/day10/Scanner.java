/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Scanner {

  static final String OPENERS = "([{<";
  static final String CLOSERS = ")]}>";
  final String code;
  public final List<String> chunks;


  public Scanner(String codeString) {
    code = codeString;
    chunks = makeChunks(code);
  }

  List<String> makeChunks(String code) {
    List<String> list = new ArrayList<>();
    var chunkOpener = code.charAt(0);
    var chunkCloser = getCorrespondingCloser(chunkOpener);
    var depth = 1;
    var chunkStart = 0;
    for (int i  = 1; i < code.length(); i++) {
      var c = code.charAt(i);
      if (c == chunkOpener) depth++;
      if (c == chunkCloser && --depth == 0 || i == code.length() - 1) {
        list.add(code.substring(chunkStart, i + 1));
        if (i == code.length() - 1) break; //last char
        chunkStart = i + 1;
        chunkOpener = code.charAt(i + 1);
        chunkCloser = getCorrespondingCloser(chunkOpener);
      }
    }
    return list;
  }

  char getCorrespondingCloser(char opener) {
    return CLOSERS.charAt(OPENERS.indexOf(opener));
  }

  public int validateChunk(String chunk) {
    Arrays.asList(chunk).forEach(System.out::print);
    System.out.println("");
    return 0;
  }
}
