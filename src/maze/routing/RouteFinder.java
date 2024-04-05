package maze.routing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import maze.Maze;
import maze.Tile;

public class RouteFinder implements Serializable {
    private Maze maze;
    private Stack<Tile> route;
    private Stack<Integer> directions;
    private boolean finished;
    private boolean started;

    public RouteFinder(Maze maze) {
      this.maze = maze;
      this.route = new Stack<>();
      this.directions = new Stack<>();
      this.finished = false;
      this.started = false;
    }

    public Maze getMaze() {
      return this.maze;
    }

    public List<Tile> getRoute() {
      return new ArrayList<>(route);
    }
    public boolean isFinished() {
      return this.finished;
    }

    public static RouteFinder load(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
      return null;
    }

    public void save(String path) throws FileNotFoundException, IOException {
    }

    public boolean step() throws NoRouteFoundException {
      return false;
    }

    public String toString() {
      return "";
    }
}
