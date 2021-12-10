/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;


/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Runner {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.out.println(
        new Day8(
            RUN_TYPE.PROD
        )
            .part2()
    );
  }

}
