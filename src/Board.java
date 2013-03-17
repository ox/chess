import exceptions.IllegalMoveException;


public class Board {
  // This is the correct layout for the board. The 'origin' is [7][0]
  public BoardSquare[][] squares;
  private King[] kings;
  
  public Board() {
    String color = "black";
    squares = new BoardSquare[8][8];
    kings = new King[2];
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new BoardSquare(color);
        color = (color == "white" ? "black" : "white" );
      }
      color = (color == "white" ? "black" : "white" );
    }
       
    // set up the black pieces
    for (int j = 0; j < 8; j++) {
      //squares[6][j].occupy(new Pawn("black", 6, j));
    }
    
//    squares[7][0].occupy(new Rook("black", 7, 0));
//    squares[7][7].occupy(new Rook("black", 7, 7));
//    
//    squares[7][1].occupy(new Horse("black", 7, 1));
//    squares[7][6].occupy(new Horse("black", 7, 6));
//    
//    squares[7][2].occupy(new Bishop("black", 7, 2));
//    squares[7][5].occupy(new Bishop("black", 7, 5));
//    
//    squares[7][3].occupy(new Queen("black", 7, 3));
    
    squares[6][4].occupy(new Pawn("black", 6,4));
    squares[7][4].occupy(new King("black", 7, 4));
    kings[1] = (King) squares[7][4].getOccupant();

    // set up the white pieces
    for (int j = 0; j < 8; j++) {
      //squares[1][j].occupy(new Pawn("white", 1, j));
    }
    
//    squares[0][0].occupy(new Rook("white", 0, 0));
//    squares[0][7].occupy(new Rook("white", 0, 7));
//    
//    squares[0][1].occupy(new Horse("white", 0, 1));
//    squares[0][6].occupy(new Horse("white", 0, 6));
//    
//    squares[0][2].occupy(new Bishop("white", 0, 2));
//    squares[0][5].occupy(new Bishop("white", 0, 5));
//    
//    squares[0][3].occupy(new Queen("white", 0, 3));
//    squares[0][4].occupy(new King("white", 0, 4));
//    kings[0] = (King) squares[0][4].getOccupant();
   }
  
  public int movePieceTo(String from, String to) throws Exception {
    int[] from_pair, to_pair;
    from_pair = coordToRankFilePair(from);
    to_pair = coordToRankFilePair(to);
    
    int rank = from_pair[0], file = from_pair[1];
    int drank = to_pair[0], dfile = to_pair[1];
    
    Piece piece = squares[rank][file].getOccupant();
    
    // there is no piece at the source square
    if (piece == null) throw new IllegalMoveException("piece at " + from + " does not exist");
    // the source is outside the bounds of the board
    if (rank < 0 || file < 0 || rank > 7 || file > 7) throw new IllegalMoveException(from + " is outside the board's bounds");
    // the destination is outside the bounds of the board
    if (drank < 0 || dfile < 0 || drank > 7 || dfile > 7) throw new IllegalMoveException(to + " is outside the board's bounds");
    
    if (piece.canMoveTo(drank, dfile)) {
      // are there any pieces in between if we are not a horse?
      if (!piece.getClass().equals(Horse .class) && piecesBetween(rank, file, drank, dfile)) {
        throw new IllegalMoveException("there are pieces between " + from + " and " + to);
      }
      
      // is there someone there?
      if (squares[drank][dfile].getOccupant() != null) {
        // is it one of our own pieces?
        if (squares[drank][dfile].getOccupant().getColor() == piece.getColor()) {
          // the piece cannot capture it's own brethren
          throw new IllegalMoveException("cannot capture own piece");
        } else {
          // there is a capture happening!!!
          System.out.println(piece + " captured " + squares[drank][dfile].getOccupant());
          squares[drank][dfile].occupy(piece);
          squares[rank][file].vacate();
          checkForUpgrades();
          return 2;
        }
      } else {
        // special case for pawns, can't actually move sideways, only attack
        if (piece.getClass().equals((Pawn .class)) && Math.abs(file - dfile) == 1) {
          throw new IllegalMoveException("pawns can't move diagonally, only attack");
        }
        
        squares[drank][dfile].occupy(piece);
        squares[rank][file].vacate();
        checkForUpgrades();
        return 1;
      }
    } else {
      // the piece can't move to there
      throw new IllegalMoveException("piece cannot move from " + from + " to " + to);
    }
  }
  
  private boolean piecesBetween(int rank, int file, int drank, int dfile) {
    int r = rank, f = file;
    if (rank-drank != 0) r += (drank-rank)/Math.abs(rank-drank);
    if (file-dfile != 0) f += (dfile-file)/Math.abs(dfile-file);
    
    while ( r != drank || f != dfile ) {
      // if there is a piece and it's not the one we start on, then return true
      if (squares[r][f].getOccupant() != null) return true;
      
      if (rank-drank != 0) r += (drank-rank)/Math.abs(rank-drank);
      if (file-dfile != 0) f += (dfile-file)/Math.abs(dfile-file);
    }
    return false;
  }
  
  private void checkForUpgrades() {
    // are there pawns that need to become queens at the ends of the board
    for (int i = 0; i < 8; i+=7) {
      for (int j = 0; j < 8; j++) {
        if (squares[i][j].getOccupant() != null
            && squares[i][j].getOccupant().getClass().equals(Pawn .class)) {
          Queen new_queen = new Queen(squares[i][j].getOccupant().color, i, j);
          squares[i][j].vacate();
          squares[i][j].occupy(new_queen);
        }
      }
    }
  }
  
  /**
   * Turns a FileRank pair into indices in the form {rank, file}. It's right, trust me.
   * 
   * Example:
   *  a1 => {0,0}
   *  d7 => {6,3}
   * 
   * @param coord
   * @return
   */
  private static int[] coordToRankFilePair(String coord) {
    int[] pair =  {(coord.charAt(1) - '1'), coord.charAt(0) - 'a'};
    System.out.println(coord + " => {" + pair[0] + ", " + pair[1] + "}" );
    return pair;
  }
  
  public String toString() {
    String ret = "   a  b  c  d  e  f  g  h\n";
    
    for (int i = 7; i >= 0; i--) {
      ret = ret.concat(String.valueOf(i + 1) + " ");
      for (int j = 0; j < 8; j++) {
        ret = ret.concat(squares[i][j].toString() + " ");
      }
      ret = ret.concat("\n");
    }
 
    return ret;
  }
}
