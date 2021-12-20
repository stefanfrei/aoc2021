/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day16;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class LiteralPacket extends APacket implements IPacket {

  final int value;


  public LiteralPacket(VERSION version, int value) {
    super(version, PACKET_TYPE.LITERAL);
    this.value = value;
  }

}
