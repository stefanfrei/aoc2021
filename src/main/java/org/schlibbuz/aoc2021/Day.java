/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public abstract class Day implements FileLoader, DayStructure {
  final File file;
  public final List<String> data;
  final static String DATA_PREFIX = "src/main/resources/";
  static final Charset UTF_8 = Charset.forName("UTF-8");


  Day(RUN_TYPE runType) {
    var dayNum = this.getClass().getSimpleName().replace("Day", "");
    file = new File(
        new StringBuilder(DATA_PREFIX.length() + 2)
        .append(DATA_PREFIX)
        .append(dayNum)
        .append(runType == RUN_TYPE.PROD ? "p" : "t")
        .toString()
    );
    data = loadFile(runType);
  }

  @Override
  public List<String> loadFile(RUN_TYPE runType) {
    try {
      return FileUtils.readLines(file, UTF_8);
    } catch(IOException e) {
      throw new RuntimeException("cannot proceed without data");
    }
  }
}
