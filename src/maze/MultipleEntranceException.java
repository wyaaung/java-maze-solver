package maze;

public class MultipleEntranceException extends InvalidMazeException {
    public MultipleEntranceException(){}

    public MultipleEntranceException(String exceptionString){
        super(exceptionString);
    }
}
