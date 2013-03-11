package engine;

/**
 *
 * @author adamnark
 */
public class Card {
    private String[] faces;
    
    @Override public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        
        if (!(other instanceof Card)){
            return false;
        }
        
        Card otherCard = (Card)other;
        for (String face : faces) {
            if (!otherCard.HasFace(face)) {
                return false;
            }
        }
                
        return true;
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
    
    public String[] getFaces() {
        return faces;
    }
}
