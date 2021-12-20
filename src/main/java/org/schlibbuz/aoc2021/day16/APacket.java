/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.schlibbuz.aoc2021.day16;

import java.util.Objects;

/**
 *
 * @author stefanfrei
 */
abstract class APacket implements IPacket {

  final VERSION version;
  final PACKET_TYPE type;


  APacket(VERSION version, PACKET_TYPE type) {
    this.version = version;
    this.type = type;
  }


  @Override
  public VERSION version() {
    return version;
  }

  @Override
  public PACKET_TYPE type() {
    return type;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + Objects.hashCode(this.version);
    hash = 29 * hash + Objects.hashCode(this.type);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final APacket other = (APacket) obj;
    if (this.version != other.version) {
      return false;
    }
    return this.type == other.type;
  }

}
