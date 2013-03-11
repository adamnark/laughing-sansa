package example.validation;

/**
 *
 * @author Liron Blecher
 */
public interface Validatable {
    
    public String getPlayerData();
    
    public void setValid(boolean valid, String errorMessage);
    
    public void setValidtor(Validator validator);
}