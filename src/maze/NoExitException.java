package maze;

public class NoExitException extends InvalidMazeException {
    public NoExitException(){}

    public NoExitException(String exceptionString){
        super(exceptionString);
    }
}
