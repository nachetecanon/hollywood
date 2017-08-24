package net.pi.platform.hollywood.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.StringJoiner;

@Document
public class WidgetPosition {

  Integer x;
  Integer y;
  Integer rows;
  Integer cols;

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getRows() {
    return rows;
  }

  public void setRows(Integer rows) {
    this.rows = rows;
  }

  public Integer getCols() {
    return cols;
  }

  public void setCols(Integer cols) {
    this.cols = cols;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WidgetPosition that = (WidgetPosition) o;

    return Objects.equals(this.cols, that.cols) &&
      Objects.equals(this.rows, that.rows) &&
      Objects.equals(this.x, that.x) &&
      Objects.equals(this.y, that.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cols, rows, x, y);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("cols = " + cols)
      .add("rows = " + rows)
      .add("x = " + x)
      .add("y = " + y)
      .toString();
  }
}
