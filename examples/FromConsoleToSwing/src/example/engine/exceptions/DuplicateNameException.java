package example.engine.exceptions;

public class DuplicateNameException extends RuntimeException{

    public DuplicateNameException() {
	super ("Name already exists");
    }
}
