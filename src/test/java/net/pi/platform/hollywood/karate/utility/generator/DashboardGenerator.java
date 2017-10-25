package net.pi.platform.hollywood.karate.utility.generator;

import com.github.javafaker.Faker;
import java.util.*;

public class DashboardGenerator {

    Faker faker;

    int xGrid;
    int yGrid;

    public DashboardGenerator(){
        xGrid = 0;
        yGrid = 0;

        faker = new Faker(new Random());
    }

    public Map<String, Object> getWidgetPosition() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("x", xGrid++);
        map.put("y", yGrid++);
        map.put("cols", faker.number().numberBetween(1, 4));
        map.put("rows", faker.number().numberBetween(1, 2));

        return map;

    }

    public Map<String, Object> getWidget() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", faker.number().digits(5));
        map.put("bookmarked", faker.bool().bool());
        map.put("name", faker.app().name());

        Map<String, Object> mapVisualization = new LinkedHashMap<>();
        mapVisualization.put("someParameter","1");
        mapVisualization.put("otherParameter","2");
        map.put("visualization",mapVisualization);

        Map<String, Object> mapApp = new LinkedHashMap<>();
        mapApp.put("app1","Parameter1");
        mapApp.put("app2","Parameter2");
        map.put("app",mapApp);
        map.put("position", getWidgetPosition());

        return map;

    }

    public List<Object> getWidgets(){

        int numFields = faker.number().numberBetween(0, 4);
        List<Object> list = new LinkedList<>();

        for (int i = 0; i < numFields; i++){
            list.add(getWidget());
        }
        return list;
    }

    public Map<String, Object> generateValidDashboard() {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("bookmarked", "false");
        map.put("name", "qa_" + faker.number().digits(10));

        Map<String, Object> position = new LinkedHashMap<>();
        position.put("cols","1");
        position.put("rows","1");
        position.put("x","0");
        position.put("y","0");
        map.put("position",position);
        map.put("widgets", getWidgets());
        return map;

    }

}
