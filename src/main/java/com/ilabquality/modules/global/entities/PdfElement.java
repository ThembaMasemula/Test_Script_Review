package com.ilabquality.modules.global.entities;

import be.quodlibet.boxable.BaseTable;

import com.ilabquality.modules.global.reference.SystemConstant;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
public class PdfElement {

  private final List<Point2D.Float> coordinates = new ArrayList<>();
  private final Point2D.Float leftTop;
  private final Point2D.Float rightTop;
  private final Point2D.Float rightBottom;
  private final Point2D.Float leftBottom;

  public PdfElement(BaseTable table, float xStart, float yStart) {
    this.leftTop = new Point2D.Float(xStart, yStart);
    this.rightTop = new Point2D.Float(this.leftTop.x + table.getWidth(), yStart);

    this.rightBottom = new Point2D.Float(
      this.rightTop.x,
      yStart - table.getHeaderAndDataHeight()
    );

    this.leftBottom = new Point2D.Float(this.leftTop.x, this.rightBottom.y);
    this.coordinates.add(this.leftTop);
    this.coordinates.add(this.rightTop);
    this.coordinates.add(this.rightBottom);
    this.coordinates.add(this.leftBottom);
  }

  public PdfElement(float xStart, float yStart, float width, float height) {
    this.leftTop = new Point2D.Float(xStart, yStart + height);
    this.rightTop = new Point2D.Float(this.leftTop.x + width, this.leftTop.y);
    this.rightBottom = new Point2D.Float(this.rightTop.x, yStart);
    this.leftBottom = new Point2D.Float(this.leftTop.x, this.rightBottom.y);

    this.coordinates.add(this.leftTop);
    this.coordinates.add(this.rightTop);
    this.coordinates.add(this.rightBottom);
    this.coordinates.add(this.leftBottom);
  }

  public Float getWidth() {
    return this.leftTop.x - this.rightTop.x;
  }

  public Float getHeight() {
    return this.leftTop.y - this.leftBottom.y;
  }

  public Point2D.Float getRightTopWithXMargin() {
    return new Point2D.Float(
      this.rightTop.x + SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
      this.rightTop.y
    );
  }

  public Point2D.Float getLeftBottomWithYMargin() {
    return new Point2D.Float(
      this.leftBottom.x,
      this.leftBottom.y - SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN
    );
  }

  public Point2D.Float getRightBottomWithMargin() {
    return new Point2D.Float(
      this.rightBottom.x + SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
      this.rightBottom.y - SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN
    );
  }
}
