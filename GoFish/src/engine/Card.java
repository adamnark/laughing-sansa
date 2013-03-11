package engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author adamnark
 */
public class Card {
    private  LinkedList<String> faces;

    public Card(){
        this.faces = new LinkedList<>();
    }

    @Override
    public String toString() {
        String s = "";
        for (String face: this.faces) {
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
        if (!Objects.equals(this.faces, other.faces)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.faces);
        return hash;
    }

      

    public boolean HasFace(String other_face) throws NullPointerException {
        if (other_face == null){
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
}
