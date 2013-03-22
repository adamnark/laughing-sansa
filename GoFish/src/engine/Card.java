package engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author adamnark
 */
public class Card {

    private LinkedList<String> serieses;
    private String name;

    public Card() {
        this.serieses = new LinkedList<>();
        this.name = "bad name";
    }

    public Card(List<String> serieses) {
        this();
        for (String series : serieses) {
            this.serieses.add(series);
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card: " + this.name.toString() + ' ' + this.serieses.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Card)) {
            return false;
        }

        Card other = (Card) obj;
        if (!this.name.equals(other.name)){
            return false;
        }
        
        for (String series : serieses) {
            if (!other.isInSeries(series)){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.serieses);
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }


    public boolean isInSeries(String otherSeries) {
        for (String series : serieses) {
            if (otherSeries.equals(series)) {
                return true;
            }
        }

        return false;
    }

    public LinkedList<String> getSerieses() {
        return this.serieses;
    }

    public void addSeries(String series) {
        if (series == null) {
            throw new NullPointerException("addSeries(null)");
        }

        this.serieses.add(series);
    }
}
