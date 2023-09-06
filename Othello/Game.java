package Othello;

import java.io.*;
import java.util.Scanner;



public class Game {
    private Board board;
    private Player first, second,current;
    int userBoard;
    Game game1;

    //The game constructor which is basically the same as the uml diagrams
    public Game(Player p1, Player p2) {
        this.first = p1;
        this.second = p2;
        this.current = new Player(first.getName(),'B');
    }


    //The start method just asks the user what board they want
    public void start() {
        int inp;
        System.out.println("Starting a New Game: ");
        do {
            System.out.println("What starting position would you like\n" +
                    "1. Four By Four Starting Position \n" +
                    "2. Standard Two by Two Starting Position");

        Scanner scan = new Scanner(System.in);
         inp = scan.nextInt();
        if (inp == 1) {
            userBoard = 1;
            System.out.println("You selected the Four by Four Starting Position");
            board = new Board();
            board.drawBoard(inp);
        } else if (inp == 2) {
            System.out.println("You selected the Standard Two by Two Starting Position");
            userBoard = 2;
            board = new Board();
            board.drawBoard(inp);
        }


        }while (inp <=0 || inp >2);
    }


    //The save method just saves the game
    private void save() {
        Scanner sc = new Scanner(System.in);
        String boardString = "";
        System.out.println("Please enter the name of the text file");
        String file = sc.nextLine();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".txt"));
            writer.write(first.getName());
            writer.write("\n" + second.getName());
            writer.write("\n" + current.getName());
            Position[][] save = board.getBoard();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char a = save[i][j].getPiece();
                    boardString += a;
                }
            }
            writer.write("\n" + boardString);
            writer.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //The load method loads the game and returns a game
    public static Game load() {
        Scanner key = new Scanner(System.in);
        System.out.println("Input the file you want to load the game from (no need to put .txt at the end)");
        String filename = key.next();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename+".txt"));
            String player1 = reader.readLine();
            String player2 = reader.readLine();
            String current = reader.readLine();
            Player p1 = new Player(player1, Position.BLACK);
            Player p2 = new Player(player2, Position.WHITE);
            Game loaded = new Game(p1, p2);
            loaded.current.setName(current);

            if(current.equals(loaded.second.getName())){
                loaded.current.setColour(Position.WHITE);
            }
            String text = reader.readLine();
            loaded.board = new Board(text);
            return loaded;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //This method displays everything needed after the game has been ended
    public void endGame() {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPosition(i, j).getPiece() == 'B') {
                    black++;
                }
                if (board.getPosition(i, j).getPiece() == 'W') {
                    white++;
                }
            }
        }
        System.out.println(first.getName() + " (Player 1) has " + black + " number of black pieces");
        System.out.println(second.getName() + " (Player 2) has " + white + " number of white pieces");
        if (black > white) {
            System.out.println(first.getName() + " (Player 1) has won the game");
        } else if (white > black) {
            System.out.println(second.getName() + " (Player 2) has won the game");

        } else if (white == black) {
            System.out.println("The game ended in a draw");
        }
    }


    //Checking if all the pieces left are black or no
    public boolean allBlack() {
        boolean aB;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getBoard()[i][j].getPiece() == 'B') {
                    aB = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    //Checking if all the pieces are white or no
    public boolean allWhite() {
        boolean aW;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getBoard()[i][j].getPiece() == 'W') {
                    aW = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    //Method used just for printing the board
    public void printBoard() {
        System.out.println("  A B C D E F G H ");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board.getBoard()[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }


    //Make move method which actually flips the pieces
    public void makeMove(int x, int y) {
        Position pos = board.getPosition(x, y);
        pos.setPiece(current.getColour());
        if (pos.top) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(--row, col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.bottom) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(++row, col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }

        }
        if (pos.left) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(row, --col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.right) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(row, ++col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.topleft) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(--row, --col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.topright) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(--row, ++col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.bottomleft) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(++row, --col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }
        if (pos.bottomright) {
            int row = x;
            int col = y;
            boolean end = true;
            while (end) {
                if (board.getPosition(++row, ++col).piece != current.getColour()) {
                    board.getPosition(row, col).piece = current.getColour();
                } else {
                    end = false;
                }
            }
        }

    }

    // Play method which actually loops everytime the user enters something
    public void play() {
        Scanner sc1 = new Scanner(System.in);
        do {
            System.out.println("What would you like to do\n" +
                    "1. Save\n" +
                    "2. Concede\n" +
                    "3. Make a Move");
            int inp = sc1.nextInt();
            if (inp == 1) {
                this.save();
            } else if (inp == 2) {
                if (current.getName() == first.getName()) {
                    System.out.println("The current Board state is: ");
                    printBoard();
                    System.out.println(current.getName() + " chose to concede the game");
                    System.out.println(second.getName() + " wins the game");
                } else {
                    System.out.println("The current Board state is: ");
                    printBoard();
                    System.out.println(current.getName() + " chose to concede the game");
                    System.out.println(first.getName() + " wins the game");
                }
                System.exit(0);

            } else if (inp == 3) {
                if (board.validMove(current.getColour())) {
                    if (allBlack() || allWhite()) {
                        endGame();
                    }
                    System.out.println("Please enter the co-ordinates you wanna play at in the following format: row col");

                    int cod1 = sc1.nextInt();
                    int cod2 = sc1.next().toUpperCase().charAt(0);
                    cod1 = cod1 - 1;
                    cod2 = cod2 - 65;
                    if (((cod1 >= 0) && (cod1 <= 8)) && ((cod2 >= 0) && (cod2 <= 8))) {
                        if (board.getPosition(cod1, cod2).canPlay(current.getColour())) {
                            makeMove(cod1, cod2);
                            printBoard();
                            if (current.getColour() == 'B') {
                                current.setColour('W');
                                current.setName(second.getName());
                                System.out.println("The current player is " + second.getName() + " and the piece is WHITE");

                            } else if(current.getColour() == 'W') {
                                current.setColour('B');
                                current.setName(first.getName());
                                System.out.println("The current player is " + first.getName() + " and the piece is BLACK");

                            }

                        } else {
                            System.out.println("The position cannot be played");
                        }


                    } else {
                        System.out.println("You didn't enter co-ordinates inside the board itself");
                        play();

                    }
                }
                else{endGame();}
            }

            //I have to make a method that prints stuff and ends the game


        } while (true);

    }

    //The main method to start the game
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.println("What Would you like to do? Please enter 1, 2 or 3\n" +
                    "1. Quit\n" +
                    "2. Start a New Game\n" +
                    "3. Load a Game");
            input = sc.nextInt();

            //Quitting the game
            if (input == 1) {
                System.out.println("Thank you for trying our Simple Othello Game!");
                System.exit(0);
            }
            //If the user want
            else if (input == 2) {
                System.out.println("Please enter player1 name");
                String name1 = sc.next();
                System.out.println("Please enter player2 name");
                String name2 = sc.next();
                Player p1 = new Player(name1, Position.BLACK);
                Player p2 = new Player(name2, Position.WHITE);
                Game game1 = new Game(p1, p2);
                game1.start();
                game1.play();
            } else if (input == 3) {
                Game g1 = load();
                g1.printBoard();
                g1.play();
            }


        }while(input<=0 || input>3);
    }
}



