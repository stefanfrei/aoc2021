/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public interface FileLoader {
  List<String> loadFile(RUN_TYPE runType) throws IOException ;
}
