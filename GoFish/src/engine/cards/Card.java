package engine.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author adamnark
 */
public final class Card {

    private String name;
    private ArrayList<Series> series;
    private boolean isValid;

    public Card() {
        this.name = "#default card name#";
        this.series = new ArrayList<>();
        this.isValid = true;
    }

    public Card(List<String> series) {
        this();
        for (String string : series) {
            addSeries(new Series(string));
        }
    }
    
    public Card(Card other){
        this();
        this.name = other.name;
        for (Series otherSeries : other.series) {
            this.series.add(otherSeries);
        }
    }

    public void makeInvalid() {
        this.isValid = false;
    }

    @Override
    public String toString() {
        return "{\'" + name + "\': " + series + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.series);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Card other = (Card) obj;
        for (Series my_series : this.series) {
            if (!other.series.contains(my_series)) {
                return false;
            }
        }

        return true;
    }

    public String getName() {
        //String suffix = this.isValid ? "" : "<dead>";
        return name;// + suffix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    public void addSeries(Collection<Series> series) {
        this.series.addAll(series);
    }

    public void addSeries(Series series) {
        this.series.add(series);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public boolean isInSeries(Series otherSeries) {
        for (Series localSeries : this.series) {
            if (localSeries.equals(otherSeries)) {
                return true;
            }
        }

        return false;
    }

    public void makeValid() {
        this.isValid = true;
    }
}
