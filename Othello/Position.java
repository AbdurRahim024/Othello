package Othello;

abstract public class Position {
    public static final char EMPTY = ' ';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';
    protected char piece;


    //These are all the attributes I added for my program to work
    //int x and y are just to hold the co-ordinates
    // left, bottom etc are booleans to hold which side can be flipped
    protected Board fakeBoard;
    protected int x;
    protected int y;
    protected boolean top;
    protected boolean bottom;
    protected boolean left;
    protected boolean right;
    protected boolean topleft;
    protected boolean topright;
    protected boolean bottomleft;
    protected boolean bottomright;

    //I added int x and y also the piece and board inside
    public Position(int x, int y, char piece, Board b) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.fakeBoard = b;
    }
    abstract public boolean canPlay(char colour);

    public void setPiece(char c1) {
        piece = c1;
    }

    public char getPiece() {
        return piece;
    }


}


class PlayablePosition extends Position {

    public PlayablePosition(int x, int y, char piece, Board b) {
        super(x, y, EMPTY, b);
    }
    //The canPlay method just returns true or false based on you can flip or no
    public boolean canPlay(char currentColour) {
        boolean valid = false;
        if(isTopValid(currentColour)){valid = true;}
        if(isBottomValid(currentColour)){valid = true;}
        if(isRightValid(currentColour)){valid = true;}
        if(isleftValid(currentColour)){valid = true;}
        if(isTopRightValid(currentColour)){valid = true;}
        if(isTopleftValid(currentColour)){valid = true;}
        if(isBottomleftValid(currentColour)){valid = true;}
        if(isBottomRightValid(currentColour)){valid = true;}



        return valid;

    }
    // I had to create 8 methods to check all the directions from the position itself
    public boolean isTopValid ( char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row <= 0) return false;
            Position nextPos = fakeBoard.getBoard()[--row][col];
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row < 0)) {
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[--row][col];
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row < 0)) {
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    top = true;
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isBottomValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row >= 7) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[++row][col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row > 7)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[++row][col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row > 7)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    bottom = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isleftValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (col <= 0) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[row][--col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (col < 0)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[row][--col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (col <0)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    left = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isRightValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (col >= 7) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[row][++col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (col > 7)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[row][++col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (col > 7)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    right = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isTopRightValid(char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row<=0||col >= 7) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[--row][++col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row<0 ||col > 7)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[--row][++col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row<0||col > 7)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    topright = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isTopleftValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row<=0||col <= 0) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[--row][--col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row<0||col < 0)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[--row][--col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row <0 || col<0)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    topleft = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isBottomleftValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row>=7 ||col <= 0) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[++row][--col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row>7||col < 0)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[++row][--col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row>7 ||col <0)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    bottomleft = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }
    public boolean isBottomRightValid (char currentColour){
        if (piece == EMPTY) {
            int row = x;
            int col = y;
            if (row>=7 || col >= 7) return false; // I have to change
            Position nextPos = fakeBoard.getBoard()[++row][++col]; // I have to change
            if (nextPos.piece == EMPTY || nextPos.piece == currentColour || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row>7 ||col > 7)) { // I have to change
                return false;
            }

            do {
                try {
                    nextPos = fakeBoard.getBoard()[++row][++col]; // I have to change
                }catch(Exception e) {return false;}

                if (nextPos.piece == EMPTY || nextPos.piece == UnplayablePosition.UNPLAYABLE || (row>7 ||col > 7)) { // I have to change
                    return false;
                }
                if (nextPos.piece == currentColour) {
                    bottomright = true; // I have to change
                    return true;
                }
            } while (true);
        }

        return false;
    }


}



    class UnplayablePosition extends Position {
        public static final char UNPLAYABLE = '*';

        public UnplayablePosition(int x, int y, char piece, Board b) {
            super(x, y, UNPLAYABLE, b);
        }

        public boolean canPlay(char colour) {
            return false;
        }

    }


