/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.aoc2021.day5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Line {

  public final Point pS;
  public final Point pE;
  public final LINE_TYPE lineType;

  public Line(List<Integer> coords) {
    this(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
  }

  public Line(int xS, int yS, int xE, int yE) {
    if (xS == xE) {
      //vertical, normalize to descending (desc)
      pS = new Point(xS, Math.min(yS, yE));
      pE = new Point(xS, Math.max(yS, yE));
      lineType = LINE_TYPE.VERT;
    } else if (yS == yE) {
      //horizontal, normalize to left-to-right (ltr)
      pS = new Point(Math.min(xS, xE), yS);
      pE = new Point(Math.max(xS, xE), yS);
      lineType = LINE_TYPE.HORIZ;
    } else if (xS < xE && yS < yE) {
      //diag_ltr_desc
      pS = new Point(xS, yS);
      pE = new Point(xE, yE);
      lineType = LINE_TYPE.DIAG_LTR_DESC;
    } else if (xS < xE && yS > yE) {
      //diag_ltr_asc
      pS = new Point(xS, yS);
      pE = new Point(xE, yE);
      lineType = LINE_TYPE.DIAG_LTR_ASC;
    } else if (xS > xE && yS > yE) {
      //diag_rtl_asc, normalize to diag_ltr_desc
      pS = new Point(xE, yE);
      pE = new Point(xS, yS);
      lineType = LINE_TYPE.DIAG_LTR_DESC;
    } else if (xS > xE && yS < yE) {
      //diag_rtl_desc, normalize to diag_ltr_asc
      pS = new Point(xE, yE);
      pE = new Point(xS, yS);
      lineType = LINE_TYPE.DIAG_LTR_ASC;
    } else {
      //should not happen
      pS = new Point(xS, yS);
      pE = new Point(xE, yE);
      lineType = null;
    }
  }

  public List<Point> getPoints() {
    List<Point> points = new ArrayList<>();
    if (lineType == LINE_TYPE.HORIZ) {
      for (var i = pS.x; i <= pE.x; i++) {
        points.add(
            new Point(i, pS.y)
        );
      }
      return points;
    }
    if (lineType == LINE_TYPE.VERT) {
      for (var i = pS.y; i <= pE.y; i++) {
        points.add(
            new Point(pS.x, i)
        );
      }
      return points;
    }
    if (lineType == LINE_TYPE.DIAG_LTR_DESC) {
      for (var i = 0; i <= pE.x - pS.x; i++) {
        points.add(
            new Point(pS.x + i, pS.y + i)
        );
      }
      return points;
    }
    if (lineType == LINE_TYPE.DIAG_LTR_ASC) {
      for (var i = 0; i <= pE.x - pS.x; i++) {
        points.add(
            new Point(pS.x + i, pS.y - i)
        );
      }
      return points;
    }
    return points;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(64);
    sb.append("Line{xS=").append(pS.x);
    sb.append(", yS=").append(pS.y);
    sb.append(", xE=").append(pE.x);
    sb.append(", yE=").append(pE.y);
    sb.append(", lineType=").append(lineType);
    sb.append('}');
    return sb.toString();
  }
}
