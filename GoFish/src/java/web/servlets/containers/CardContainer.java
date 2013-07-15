package web.servlets.containers;

import engine.Engine;
import engine.cards.Card;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adamnark
 */
public class CardContainer {

    private static Map<String, String> seriesMap; //"Lannister" -> "ser1"
    List<SeriesContainer> series;
    String name;

    public CardContainer() {
        if (seriesMap == null) {
            throw new RuntimeException("Must call CardContainer.initClass before instantiation!");
        }

        this.series = new LinkedList<>();
    }

    public CardContainer(Card c) {
        this();
        this.name = c.getName();
        for (engine.cards.Series series1 : c.getSeries()) {
            String serName = series1.getName();
            String serId = seriesMap.get(serName);
            this.series.add(new SeriesContainer(serId, serName));
        }
    }

    public static void initClass(Engine e) {
        List<engine.cards.Series> seriesList = e.getAvailableSeries();
        seriesMap = new HashMap<>();
        int i = 0;
        for (engine.cards.Series series : seriesList) {
            seriesMap.put(series.getName(), "ser" + Integer.toString(i));
            i++;
        }
    }

    public class SeriesContainer {

        String id; //"ser0"
        String name; //"Lannister"

        public SeriesContainer(String id, String name) {
            this.id = id;
            this.name = name;
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
    }
}
