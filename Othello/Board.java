package Othello;

public class Board {
    private Position[][] board = new Position[8][8];


    // Board constructor which takes in a string and changes that into a 2d array
    public Board(String positions) {
        for(int row=0; row<8;row++){
            for (int col=0; col<8; col++){
                char value = positions.charAt(row*8+col);
                //Takes the string that holds the values for the starting position and converts the characters found to the ones declared in Position class
                switch(value){
                    case ' ':
                        value = Position.EMPTY;
                        break;
                    case 'B':
                        value = Position.BLACK;
                        break;
                    case 'W':
                        value = Position.WHITE;
                        break;
                }
                //Instantiates unplayable position in the necessary spots and sets the pieces to value UNPLAYABLE
                if((row == 0 && col ==3) || (row==0 && col==4)){
                    board[row][col] = new UnplayablePosition(row,col,UnplayablePosition.UNPLAYABLE,this);
                    board[row][col].setPiece(UnplayablePosition.UNPLAYABLE);
                }else {
                    board[row][col] = new PlayablePosition(row,col,Position.EMPTY,this);
                    board[row][col].setPiece(value);
                    //Here the value that was found in the switch statement is what the piece is set to\
                }
            }
        }
    }


    // Another board constructor
    public Board() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
//
                if ((row == 0 && col == 3) || (row == 0 && col == 4)) {
                    board[row][col] = new UnplayablePosition(row,col,UnplayablePosition.UNPLAYABLE,this);
                }
                else {
                    board[row][col] = new PlayablePosition(row,col,Position.EMPTY,this);
                }
            }
        }

    }

    //Checking if there are any valid moves left on the board
    public boolean validMove(char colour){
        for(int i =0;i< board.length;i++){
            for(int j = 0;j< board[i].length;j++){
                if(board[i][j].canPlay(colour)){
                    return true;
                }
            }
        }
        return false;
    }

        //Adding a new parameter to the drawBoard Method
        public void drawBoard (int a) {
              if(a==1){
                board[0][3].setPiece('*');
                board[0][4].setPiece('*');
                board[2][2].setPiece('W');
                board[2][3].setPiece('W');
                board[3][2].setPiece('W');
                board[3][3].setPiece('W');
                board[4][4].setPiece('W');
                board[4][5].setPiece('W');
                board[5][4].setPiece('W');
                board[5][5].setPiece('W');
                board[2][4].setPiece('B');
                board[2][5].setPiece('B');
                board[3][4].setPiece('B');
                board[3][5].setPiece('B');
                board[4][2].setPiece('B');
                board[4][3].setPiece('B');
                board[5][2].setPiece('B');
                board[5][3].setPiece('B');
            }
            else {
                board[0][3].setPiece('*');
                board[0][4].setPiece('*');
                board[3][3].setPiece('W');
                board[3][4].setPiece('B');
                board[4][3].setPiece('B');
                board[4][4].setPiece('W');
            }
            System.out.println("  A B C D E F G H ");
            for(int i =0;i<8;i++){
                System.out.print((i +1) + " ");
                for(int j = 0;j<8;j++){
                    System.out.print(board[i][j].getPiece() + " ");
                }
                System.out.println();
            }

        }


        // Adding a new getBoard method which returns a 2d Array of positions
        public Position[][] getBoard(){
            return board;
        }

        //Adding a getPosition method which takes in 2 co-ordinates and return the position on the board
        public Position getPosition(int x, int y) {
            return board[x][y];
        }

}