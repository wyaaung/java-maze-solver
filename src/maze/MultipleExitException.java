package maze;

public class MultipleExitException extends InvalidMazeException {
    public MultipleExitException(){}

    public MultipleExitException(String exceptionString){
        super(exceptionString);
    }
}
