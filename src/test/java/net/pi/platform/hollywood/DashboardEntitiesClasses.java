package net.pi.platform.hollywood;

import net.pi.platform.hollywood.model.Dashboard;
import net.pi.platform.hollywood.model.Widget;
import net.pi.platform.hollywood.model.WidgetPosition;

import java.util.Arrays;

public class DashboardEntitiesClasses {

  public static Dashboard getDashboard() {
    Dashboard dashboard = new Dashboard();
    dashboard.setName("name");
    dashboard.setWidgets(Arrays.asList(getWidget()));
    return dashboard;
  }

  public static Widget getWidget() {
    Widget widget = new Widget();
    widget.setName("name");
    widget.setBookmarked(true);
    widget.setWidgetPosition(getWidgetPosition());
    return widget;
  }

  public static WidgetPosition getWidgetPosition() {
    WidgetPosition widgetPosition = new WidgetPosition();
    widgetPosition.setX(1);
    widgetPosition.setY(2);
    widgetPosition.setCols(1);
    widgetPosition.setRows(2);
    return widgetPosition;
  }
}
