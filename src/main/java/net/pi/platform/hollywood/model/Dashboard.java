package net.pi.platform.hollywood.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Document(collection = "dashboards")
public class Dashboard {

  @Id
  private String id;

  private String name;

  List<Widget> widgets;

  public List<Widget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }


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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dashboard that = (Dashboard) o;

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.name, that.name) &&
      Objects.equals(this.widgets, that.widgets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, widgets);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
      .add("id = " + id)
      .add("name = " + name)
      .add("widgets = " + widgets)
      .toString();
  }
}
