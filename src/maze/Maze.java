package maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public String toString(){
            return "(" + this.x +", " + this.y + ")";
        }
    }

    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles;

    private Maze(){}

    public static Maze fromTxt(String path) throws InvalidMazeException {
        Maze maze = new Maze();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            int len = line.length();

            while (line != null) {
                ArrayList<Tile> row = new ArrayList<>();
                maze.tiles.add(0, row);

                int line_len = line.length();
                if (line_len != len) {
                    throw new RaggedMazeException();
                }

                for (int i = 0; i < line_len; i++) {
                    Tile tile = Tile.fromChar(line.charAt(i));
                    row.add(tile);

                    if (tile.getType() == Tile.Type.ENTRANCE) {
                        maze.setEntrance(tile);
                    } else if (tile.getType() == Tile.Type.EXIT) {
                        maze.setExit(tile);
                    }
                }

                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException exception) {
            throw new InvalidMazeException();
        } catch (IOException exception) {
            throw new InvalidMazeException();
        }

        return null;
    }

    private void setEntrance(Tile tile) throws IllegalArgumentException, MultipleEntranceException {
        if (this.entrance == null){
            if (this.getTileLocation(tile).getX() != -1){
                this.entrance = tile;
            }
            throw new IllegalArgumentException();
        } else {
            throw new MultipleEntranceException();
        }
    }

    private void setExit(Tile tile) throws IllegalArgumentException, MultipleExitException {
        if (this.exit == null){
            if (this.getTileLocation(tile).getX() != -1){
                this.exit = tile;
            }
            throw new IllegalArgumentException();
        } else {
            throw new MultipleExitException();
        }
    }

    public Coordinate getTileLocation(Tile tile) {
        for (int i=0; i < this.tiles.size(); i++){
            int index = this.tiles.get(i).indexOf(tile);

            if (index != -1){
                return new Coordinate(index, tiles.size() - 1 - i);
            }
        }
        return new Coordinate(-1, -1);
    }

    public Tile getEntrance() {
        return this.entrance;
    }

    public Tile getExit() {
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
