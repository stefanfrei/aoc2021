/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author stefanfrei
 */
public final class Day4 extends Day {

  Day4() {
    this(RUN_TYPE.PROD);
  }

  Day4(RUN_TYPE runType) {
    super(runType);
  }

    private class Number {
        int val;
        boolean wasDrawn;

        Number(int val) {
            this.val = val;
            wasDrawn = false;
        }
    }

    private class BingoCard {


        List<List<Number>> numbers;

        BingoCard(List<String> numbers) {
            this.numbers = new ArrayList<>();
            for (String line : numbers) {
                List<Number> row = new ArrayList<>();
                for(String number : line.trim().split("\\s+")) {
                    row.add(new Number(
                            Integer.parseInt(number, 10)
                    ));
                }
                this.numbers.add(row);
            }
        }

        int processDraw(int draw) {
          boolean drawProcessed = false;
          for (var row : numbers) {
            for (var cell : row) {
              if (cell.val == draw) {
                cell.wasDrawn = true;
                drawProcessed = true;
                break;
              }
            }
            if (drawProcessed) break;
          }
          if (hasBingo()) return getUndrawnsSum();
          return 0;
        }

        int getUndrawnsSum() {
            final List<Integer> undrawns = new ArrayList<>();
            numbers.forEach(row -> {
                row.stream().filter(cell -> (!cell.wasDrawn)).forEachOrdered(cell -> {
                    undrawns.add(cell.val);
                });
            });
            return undrawns
                    .stream().reduce(0, Integer::sum);
        }

        boolean hasBingo() {
            for (var row : numbers) {
                for (int i = 0; i < row.size(); i++) {
                    if (!row.get(i).wasDrawn) break;
                    if (i == row.size() -1) return true;
                }
            }

            for (int rowIndex = 0; rowIndex < numbers.size(); rowIndex++) {
                for (int cellIndex = 0; cellIndex < numbers.get(rowIndex).size(); cellIndex++) {
                    if (!numbers.get(cellIndex).get(rowIndex).wasDrawn) break;
                    if (cellIndex == numbers.size() - 1) return true;
                }
            }
            return false;
        }

    @Override
    public String toString() {
      var sb = new StringBuilder(256);
      sb.append("BingoCard{numbers=\n");
      for (var row : numbers) {
        for (var cell : row) {
          sb.append(String.format("%3d[%1s]", cell.val, (cell.wasDrawn ? "x" : " ")));
        }
        sb.append("\n");
      }
      sb.append('}');
      return sb.toString();
    }


    }

    public long part1() {
        var draws = Arrays.asList(data.get(0).split(","))
                .stream()
                .map(val -> Integer.parseInt(val, 10))
                .collect(Collectors.toUnmodifiableList());

        List<BingoCard> cards = new ArrayList<>();
        for (var i = 2; i < data.size(); i++) {
            List<String> numbers = new ArrayList<>();
            String line;
            while(i < data.size() && !(line = data.get(i)).isEmpty()) {
                i++;
                numbers.add(line);
            }
            cards.add(new BingoCard(numbers));
        }

        cards.forEach(System.out::println);

        for (var draw : draws) {
          System.out.println(draw);
          for (var card : cards) {
            var drawResult = card.processDraw(draw);
            if (drawResult > 0) {
              System.out.println(card);
              System.out.println("bingo");
              return draw * drawResult;
            }
          }
        }
        return 0;
    }

    public long part2() {
        var draws = Arrays.asList(data.get(0).split(","))
                .stream()
                .map(val -> Integer.parseInt(val, 10))
                .collect(Collectors.toUnmodifiableList());

        List<BingoCard> cards = new ArrayList<>();
        for (var i = 2; i < data.size(); i++) {
            List<String> numbers = new ArrayList<>();
            String line;
            while(i < data.size() && !(line = data.get(i)).isEmpty()) {
                i++;
                numbers.add(line);
            }
            cards.add(new BingoCard(numbers));
        }

        cards.forEach(System.out::println);

        for (var draw : draws) {
          Iterator<BingoCard> it = cards.iterator();
          while(it.hasNext()) {
            var card = it.next();
            var drawResult = card.processDraw(draw);
            if (drawResult > 0) {
              if (cards.size() == 1) return draw * drawResult;
              it.remove();
            }
          }
        }
        return 0;
    }
}
