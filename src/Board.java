
public class Board {
  private BoardSquare[][] squares;
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
      squares[1][j].occupy(new Pawn("white", j, 1));
    }
    
    squares[0][0].occupy(new Rook("white", 0, 0));
    squares[0][7].occupy(new Rook("white", 0, 7));
    
    squares[0][1].occupy(new Horse("white", 0, 1));
    squares[0][6].occupy(new Horse("white", 0, 6));
    
    squares[0][2].occupy(new Bishop("white", 0, 2));
    squares[0][5].occupy(new Bishop("white", 0, 5));
    
    squares[0][3].occupy(new Queen("white", 0, 3));
    squares[0][4].occupy(new King("white", 0, 4));
    kings[0] = (King) squares[0][4].getOccupant();
    
    // set up the white pieces
    for (int j = 0; j < 8; j++) {
      squares[6][j].occupy(new Pawn("black", j, 1));
    }
    
    squares[7][0].occupy(new Rook("black", 7, 0));
    squares[7][7].occupy(new Rook("black", 7, 7));
    
    squares[7][1].occupy(new Horse("black", 7, 1));
    squares[7][6].occupy(new Horse("black", 7, 6));
    
    squares[7][2].occupy(new Bishop("black", 7, 2));
    squares[7][5].occupy(new Bishop("black", 7, 5));
    
    squares[7][3].occupy(new Queen("black", 7, 3));
    squares[7][4].occupy(new King("black", 7, 4));
    kings[1] = (King) squares[7][4].getOccupant();
  }
  
  public int movePieceTo(String from, String to) {
    int[] from_pair, to_pair;
    from_pair = coordToRankFilePair(from);
    to_pair = coordToRankFilePair(to);
    
    int rank = from_pair[0], file = from_pair[1];
    int drank = to_pair[0], dfile = to_pair[1];
    
    Piece piece = squares[rank][file].getOccupant();
    
    // there is no piece at the source square
    if (piece == null) return -1;
    // the source is outside the bounds of the board
    if (rank < 0 || file < 0 || rank > 7 || file > 7) return -2;
    // the destination is outside the bounds of the board
    if (drank < 0 || dfile < 0 || drank > 7 || dfile > 7) return -3;
    
    // just move the piece
    if (piece.canMoveTo(drank, dfile) && squares[rank][file] == null) {
      squares[drank][dfile].occupy(piece);
      squares[rank][file].vacate();
      checkForUpgrades();
      return 0;
    }
    
    if (piece.canMoveTo(drank, dfile)) {
      // is there someone there?
      if (squares[drank][dfile].getOccupant() != null) {
        // is it one of our own pieces?
        if (squares[rank][file].getOccupant().getColor().equals(piece.color)) {
          // the piece cannot capture it's own brethren
          return -4;
        } else {
          // there is a capture happening!!!
          squares[drank][dfile].occupy(piece);
          squares[rank][file].vacate();
          checkForUpgrades();
          return 2;
        }
      } else {
        // just move
        squares[rank][file].vacate();
        squares[drank][dfile].occupy(piece);
        checkForUpgrades();
        return 1;
      }
    } else {
      // the piece can't move to there
      return 3;
    }
  }
  
  public void checkForUpgrades() {
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
    return (new int[] {(coord.charAt(1) - '1'), coord.charAt(0) - 'a'});
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
