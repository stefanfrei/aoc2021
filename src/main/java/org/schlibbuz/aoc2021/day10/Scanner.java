/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day10;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class Scanner {

  static final String OPENERS = "([{<";
  static final String CLOSERS = ")]}>";
  static final Pattern CLOSER_PATTERN = Pattern.compile("[\\)|\\]|\\}|>]");
  public static final Map<Character, Integer> SCORE_MAP = Stream.of(new Object[][] {
     { ')',     3 },
     { ']',    57 },
     { '}',  1197 },
     { '>', 25137 },
 }).collect(Collectors.toUnmodifiableMap(data -> (char) data[0], data -> (Integer) data[1]));
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
        if (!OPENERS.contains(String.valueOf(chunkOpener))) {
          list.add(code.substring(chunkStart));
          break;
        }
        chunkCloser = getCorrespondingCloser(chunkOpener);
      }
    }
    return list;
  }

  char getCorrespondingCloser(char opener) {
    return CLOSERS.charAt(OPENERS.indexOf(opener));
  }

  public boolean isCodeCorrupted() {
    return true;
  }

  public List<ScannerError> validateChunk(final String chunk) {
    List<ScannerError> errs = new ArrayList<>();
    var src = String.copyValueOf(chunk.toCharArray());
    int index = 0;
    while (src.length() > errs.size()) {
      Matcher m = CLOSER_PATTERN.matcher(src);
      if (!m.find()) {
        errs.add(new ScannerError(ERROR_TYPE.INCOMPLETE, -1, '\u0000', '\u0000'));
        return errs;
      }
      var findIndex = m.start();
      var currentCloser = m.group().charAt(0);
      var expectedCloser = getCorrespondingCloser(src.charAt(findIndex - 1 ));
      if (currentCloser != expectedCloser) {
        errs.add(
            new ScannerError(ERROR_TYPE.CORRUPTED, index + findIndex, currentCloser, expectedCloser)
        );
      }
      src = src.substring(0, findIndex - 1).concat(src.substring(findIndex + 1));
      index += 2;
    }
    return errs;
  }
}
