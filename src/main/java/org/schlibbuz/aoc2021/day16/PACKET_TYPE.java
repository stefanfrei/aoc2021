/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.schlibbuz.aoc2021.day16;

/**
 *
 * @author stefanfrei
 */
public enum PACKET_TYPE {
  LITERAL(4);

  final int id;
  
  private PACKET_TYPE(int id) {
    this.id = id;
  }
  
  
}
