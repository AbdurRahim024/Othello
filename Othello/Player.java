package Othello;

public class Player {
    private String name;
    private char colour;
    public Player(String name,char colour){
        this.name = name;
        this.colour = colour;
    }

    public char getColour() {
        return colour;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
