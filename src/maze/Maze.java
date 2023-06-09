package maze;

import java.io.Serializable;
import java.util.List;

public class Maze implements Serializable{
    public enum Direction{
        NORTH, SOUTH, EAST, WEST
    }

    public class Coordinate{
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String toString(){
            return "(" + this.x +", " + this.y + ")";
        }
    }

    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles;


}
