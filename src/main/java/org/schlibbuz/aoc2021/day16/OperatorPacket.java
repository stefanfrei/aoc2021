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
public class OperatorPacket extends APacket implements IPacket {

  final LENGTH_TYPE lengthType;
  final int totalSubPacketsLength;


  public OperatorPacket(VERSION version, LENGTH_TYPE lengthType, int totalSubPacketsLength) {
    super(version, PACKET_TYPE.OPERATOR);
    this.lengthType = lengthType;
    this.totalSubPacketsLength = totalSubPacketsLength;
  }

}
