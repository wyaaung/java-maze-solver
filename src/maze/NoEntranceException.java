package maze;

public class NoEntranceException extends InvalidMazeException {
    public NoEntranceException(){}

    public NoEntranceException(String exceptionString){
        super(exceptionString);
    }
}
