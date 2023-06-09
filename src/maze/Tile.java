package maze;

import java.io.Serializable;

public class Tile implements Serializable{
    public enum Type {
        CORRIDOR, ENTRANCE, EXIT, WALL
    }

    private Type type;

    private Tile(Type type){
        this.type = type;
    }

    protected static Tile fromChar(char c){
        switch(c){
            case 'e':
                return new Tile(Type.ENTRANCE);
            case '#':
                return new Tile(Type.WALL);
            case '.':
                return new Tile(Type.CORRIDOR);
            case 'x':
                return new Tile(Type.EXIT);
            default:
                return null;
        }
    }

    public Type getType(){
        return this.type;
    }

    public boolean isNavigable(){
        return (this.type != Type.WALL);
    }

    public String toString(){
        switch(this.type)
        {
            case ENTRANCE:
                return "e";
            case CORRIDOR:
                return ".";
            case WALL:
                return "#";
            case EXIT:
                return "x";
            default:
                return null;
        }
    }
}
