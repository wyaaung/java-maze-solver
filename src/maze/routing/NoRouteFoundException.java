package maze.routing;

public class NoRouteFoundException extends RuntimeException {
    public NoRouteFoundException(){}

    public NoRouteFoundException(String exceptionString){
        super(exceptionString);
    }
}
