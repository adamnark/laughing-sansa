package engine;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author adamnark
 */
public class Card {

    private LinkedList<String> faces;
    private String name;

    public Card() {
        this.faces = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card(LinkedList<String> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        String s = "";
        for (String face : this.faces) {
            s += face + ", ";
        }

        return '{' + s + '}';
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
        for (String face : faces) {
            if (!other.hasFace(face)){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.faces);
        return hash;
    }

    public boolean hasFace(String other_face) {
        if (other_face == null) {
            throw new NullPointerException();
        }

        for (String face : faces) {
            if (other_face.equals(face)) {
                return true;
            }
        }

        return false;
    }

    public LinkedList<String> getFaces() {
        return this.faces;
    }

    public void addFace(String face) {
        if (face == null) {
            throw new NullPointerException("face");
        }

        this.faces.add(face);
    }
}
