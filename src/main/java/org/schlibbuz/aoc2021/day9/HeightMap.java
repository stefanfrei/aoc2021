/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public final class HeightMap {

  public final byte[] data;
  public final int dataHeight;
  public final int dataWidth;

  public HeightMap(List<String> data) {
    dataHeight = data.size();
    dataWidth = data.get(0).length();
    this.data = initData(data);
  }

  public static <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
    AtomicInteger counter = new AtomicInteger(0);
    return item -> consumer.accept(counter.getAndIncrement(), item);
  }

  byte[] initData(List<String> data) {
    final byte[] arr = new byte[dataHeight * dataWidth];
    data.stream().forEach(withCounter((i, line) -> {
      int j = 0;
      for (var c : line.toCharArray()) {
        arr[i * dataWidth + j++] = Byte.parseByte(String.valueOf(c));
      }
    }));
    return arr;
  }

  public List<int[]> getBasins(List<Integer> lowPointsIndices) {
    List<int[]> basins = new ArrayList<>();
    for (var lowPointIndex : lowPointsIndices) basins.add(getBasin(lowPointIndex));
    return basins;
  }

  Set<Integer> addHigherAdjacents(int centerIndex, Set<Integer> basin) {
    Set<Integer> adjacentIndices = new HashSet<Integer>();
    adjacentIndices.add(centerIndex - dataWidth);
    if (centerIndex % dataWidth < dataWidth - 1) adjacentIndices.add(centerIndex + 1);
    adjacentIndices.add(centerIndex + dataWidth);
    if (centerIndex % dataWidth > 0) adjacentIndices.add(centerIndex - 1);

    for (var adjacentIndex : adjacentIndices) {
      var adjacent = getValue(adjacentIndex);
      if ( adjacent < 9 && adjacent > getValue(centerIndex)) {
        basin.addAll(addHigherAdjacents(adjacentIndex, basin));
      }
    }
    basin.add(centerIndex);
    return basin;
  }

  int[] getBasin(int lowPointIndex) {
    Set<Integer> basin = new HashSet<>();
    basin = addHigherAdjacents(lowPointIndex, basin);
    return basin.stream().mapToInt(Integer::valueOf).toArray();
  }

  public long sumLowPoints(List<Integer> lowPointsIndices) {
    long sum = 0;
    for (var i : lowPointsIndices) {
      sum += data[i] + 1;
    }
    return sum;
  }

  public List<Integer> getLowPointsIndices() {
    List<Integer> lowPoints = new ArrayList<>();
    for (int i = data.length - 1; i != 0; i--) {
      var currentValue = data[i];
      if (
          currentValue < getValue(i - dataWidth)
          &&
          currentValue < getValue(i + 1)
          &&
          currentValue < getValue(i + dataWidth)
          &&
          currentValue < getValue(i - 1)
          ) lowPoints.add(i);
    }
    return lowPoints;
  }

  byte getValue(int index) {
    try {
        return data[index];
      } catch(IndexOutOfBoundsException e) {
        return 9;
      }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder( data.length + dataHeight + 64);
    sb.append("HeightMap{dataHeight=").append(dataHeight);
    sb.append(", dataWidth=").append(dataWidth);
    sb.append("\ndata=[\n");
    for (int i = 0; i < dataHeight; i++) {
      for (int j = 0; j < dataWidth; j++) {
        sb.append(data[i * dataWidth + j]);
      }
      sb.append("\n");
    }
    sb.append("]\n}");
    return sb.toString();
  }

}
