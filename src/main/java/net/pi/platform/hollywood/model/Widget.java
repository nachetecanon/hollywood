package net.pi.platform.hollywood.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Widget {

  private String id;

  private String name;

  private Boolean bookmarked;

  private WidgetPosition widgetPosition;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getBookmarked() {
    return bookmarked;
  }

  public void setBookmarked(Boolean bookmarked) {
    this.bookmarked = bookmarked;
  }

  public WidgetPosition getWidgetPosition() {
    return widgetPosition;
  }

  public void setWidgetPosition(WidgetPosition widgetPosition) {
    this.widgetPosition = widgetPosition;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Widget that = (Widget) o;

    return Objects.equals(this.bookmarked, that.bookmarked) &&
      Objects.equals(this.id, that.id) &&
      Objects.equals(this.name, that.name) &&
      Objects.equals(this.widgetPosition, that.widgetPosition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookmarked, id, name, widgetPosition);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("bookmarked = " + bookmarked)
      .add("id = " + id)
      .add("name = " + name)
      .add("widgetPosition = " + widgetPosition)
      .toString();
  }
}
