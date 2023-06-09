package maze;

import java.io.BufferedReader;
import java.io.FileReader;
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

    private Maze(){}

    public static Maze fromTxt(String sIn){
        return null;
    }

    private void setEntrance(Tile tile){
        if (this.entrance == null){
            if (this.getTileLocation(tile).getX() != -1){
                this.entrance = tile;
            }
            throw new IllegalArgumentException();
        } else {
            throw new MultipleEntranceException();
        }
    }

    private void setExit(Tile tile){
        if (this.exit == null){
            if (this.getTileLocation(tile).getX() != -1){
                this.exit = tile;
            }
            throw new IllegalArgumentException();
        } else {
            throw new MultipleExitException();
        }
    }

    public Coordinate getTileLocation(Tile tile){
        for (int i=0; i < this.tiles.size(); i++){
            int index = this.tiles.get(i).indexOf(tile);

            if (index != -1){
                return new Coordinate(index, tiles.size() - 1 - i);
            }
        }
        return new Coordinate(-1, -1);
    }

    public Tile getEntrance(){
        return this.entrance;
    }

    public Tile getExit(){
        return this.exit;
    }

    public List<List<Tile>> getTiles() {
        return this.tiles;
    }

    public Tile getTileAtLocation(Coordinate coordinate){
        int x = coordinate.getX();
        int y = coordinate.getY();
        return tiles.get(tiles.size() - y - 1).get(x);
    }

    public Tile getAdjacentTile(Tile tile, Direction direction){
        Coordinate origin = this.getTileLocation(tile);
        switch(direction.toString()){
            case "NORTH":
                origin = new Coordinate(origin.getX(), origin.getY() + 1);
                return this.getTileAtLocation(origin);
            case "SOUTH":
                origin = new Coordinate(origin.getX(), origin.getY() - 1);
                return this.getTileAtLocation(origin);
            case "WEST":
                origin = new Coordinate(origin.getX() - 1, origin.getY());
                return this.getTileAtLocation(origin);
            case "EAST":
                origin = new Coordinate(origin.getX() + 1, origin.getY());
                return this.getTileAtLocation(origin);
            default:
                origin = null;
                return Tile.fromChar('#');
        }
    }

    public String toString(){
        String s = "";
        for (int k = 0; k < tiles.size(); k++){
            s = s + "\n" + (tiles.size() - k - 1) + "\t";
            for (int l = 0; l < tiles.get(k).size(); l++){
                s = s + tiles.get(k).get(l).toString() + "   ";
            }
        }

        s = s + "\n\n" + "" + "\t";

        for (int m = 0; m < tiles.get(0).size(); m++){
            s = s + m + "   ";
        }
        return s;
    }
}
