import java.util.ArrayList;

import exceptions.IllegalMoveException;
import exceptions.IllegalPromotionException;


public class Board {
  // This is the correct layout for the board. The 'origin' is [7][0]
  public BoardSquare[][] squares;
  private King[] kings;
  private ArrayList<Piece> white_pieces;
  private ArrayList<Piece> black_pieces;
  
  public Board() {
    String color = "black";
    squares = new BoardSquare[8][8];
    kings = new King[2];
    
    // keep track of both black and white's pieces
    white_pieces = new ArrayList<Piece>();
    black_pieces = new ArrayList<Piece>();
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new BoardSquare(color);
        color = (color == "white" ? "black" : "white" );
      }
      color = (color == "white" ? "black" : "white" );
    }
       
    // set up the black pieces
//    for (int j = 0; j < 8; j++) {
//      black_pieces.add(squares[6][j].occupy(new Pawn("black", 6, j)));
//    }
//    
//    black_pieces.add(squares[7][0].occupy(new Rook("black", 7, 0)));
//    black_pieces.add(squares[7][7].occupy(new Rook("black", 7, 7)));
//    
//    black_pieces.add(squares[7][1].occupy(new Knight("black", 7, 1)));
//    black_pieces.add(squares[7][6].occupy(new Knight("black", 7, 6)));
//    
//    black_pieces.add(squares[7][2].occupy(new Bishop("black", 7, 2)));
//    black_pieces.add(squares[7][5].occupy(new Bishop("black", 7, 5)));
//    
//    black_pieces.add(squares[7][3].occupy(new Queen("black", 7, 3)));
    black_pieces.add(squares[7][4].occupy(new King("black", 7, 4)));
    white_pieces.add(squares[5][5].occupy(new Pawn("white", 5, 5)));
    kings[1] = (King) squares[7][4].getOccupant();

    // set up the white pieces
//    for (int j = 0; j < 8; j++) {
//      white_pieces.add(squares[1][j].occupy(new Pawn("white", 1, j)));
//    }
//    
//    white_pieces.add(squares[0][0].occupy(new Rook("white", 0, 0)));
//    white_pieces.add(squares[0][7].occupy(new Rook("white", 0, 7)));
//    
//    white_pieces.add(squares[0][1].occupy(new Knight("white", 0, 1)));
//    white_pieces.add(squares[0][6].occupy(new Knight("white", 0, 6)));
//    
//    white_pieces.add(squares[0][2].occupy(new Bishop("white", 0, 2)));
//    white_pieces.add(squares[0][5].occupy(new Bishop("white", 0, 5)));
//    
//    white_pieces.add(squares[0][3].occupy(new Queen("white", 0, 3)));
    white_pieces.add(squares[0][4].occupy(new King("white", 0, 4)));
    kings[0] = (King) squares[0][4].getOccupant();
   }
  
  public void movePieceTo(String from, String to) throws IllegalMoveException {
    int[] from_pair, to_pair;
    from_pair = coordToRankFilePair(from);
    to_pair = coordToRankFilePair(to);
    
    int rank = from_pair[0], file = from_pair[1];
    int drank = to_pair[0], dfile = to_pair[1];
    
    Piece piece = squares[rank][file].getOccupant();
    
    if (piece == null) 
      throw new IllegalMoveException("piece at " + from + " does not exist");
    if (rank < 0 || file < 0 || rank > 7 || file > 7) 
      throw new IllegalMoveException(from + " is outside the board's bounds");
    if (drank < 0 || dfile < 0 || drank > 7 || dfile > 7) 
      throw new IllegalMoveException(to + " is outside the board's bounds");
    if (!piece.canMoveTo(drank, dfile))
      throw new IllegalMoveException("piece cannot move from " + from + " to " + to);

    // are there any pieces in between if we are not a knight?
    if (!piece.getClass().equals(Knight .class) && piecesBetween(rank, file, drank, dfile))
      throw new IllegalMoveException("there are pieces between " + from + " and " + to);

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
        piece.incrMoved();
      }
    } else {
      // special case for pawns, can't actually move sideways, only attack
      if (piece.getClass().equals((Pawn .class)) && Math.abs(file - dfile) == 1) {
        throw new IllegalMoveException("pawns can't move diagonally, only attack");
      }
      // black castle e8 g8
      if (piece.getClass().equals((King .class))
          && piece.neverMoved()
          && squares[7][7].getOccupant().getClass().equals((Rook .class))
          && squares[7][7].getOccupant().neverMoved()) {
        // TODO need to check if the king is in danger between those spots
        squares[rank][file].vacate();
        squares[drank][dfile].occupy(piece);
        squares[drank][dfile-1].occupy(squares[drank][dfile+1].getOccupant());
        squares[drank][dfile+1].vacate();
      }

      // black castle e8 c8
      if (piece.getClass().equals((King .class))
          && piece.neverMoved()
          && squares[7][0].getOccupant().getClass().equals((Rook .class))
          && squares[7][0].getOccupant().neverMoved()) {
        // TODO need to check if the king is in danger between those spots
        squares[rank][file].vacate();
        squares[drank][dfile].occupy(piece);
        squares[drank][dfile+1].occupy(squares[drank][dfile-2].getOccupant());
        squares[drank][dfile-2].vacate();
      }

      squares[drank][dfile].occupy(piece);
      squares[rank][file].vacate();
      piece.incrMoved();
    }
    
    checkForCheck();
  }
  
  private void checkForCheck() {
    for (King king : kings) {
      System.out.println("checking the " + king.getColor() + " king");
      if (canPieceBeAttackedAt(king.rawRank(), king.rawFile())) {
        System.out.println(king + " is under check");
      } else {
        System.out.println(king + " is safe");
      }
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
  
  public boolean canPieceBeAttackedAt(int rank, int file) {
    Piece piece = squares[rank][file].getOccupant();
    
    ArrayList<Piece> enemy = (piece.getColor().equals("w") ? black_pieces : white_pieces);
    
    for (Piece e : enemy) {
      System.out.println("checking " + e);
      if (e.canMoveTo(rank, file)) {
        return true;
      } else {
        System.out.println(e + " cannot move to " + piece);
        System.out.println(e + " can move to:");
        ArrayList<String> moves = e.availableMovesFrom(rank, file);
        
        for (String move : moves) {
          System.out.print(move + ", ");
        }
        
        System.out.println("");
      }
    }
    
    return false;
  }
  
  public void checkForUpgrades(String type) throws IllegalPromotionException {
    // are there pawns that need to become queens at the ends of the board
    for (int i = 0; i < 8; i+=7) {
      for (int j = 0; j < 8; j++) {
        if (squares[i][j].getOccupant() != null
            && squares[i][j].getOccupant().getClass().equals(Pawn .class)) {
          
          if (type.equals("Q")) {
            squares[i][j].occupy(new Queen(squares[i][j].getOccupant().getColor(), i, j));
          } else if (type.equals("N")) {
            squares[i][j].occupy(new Knight(squares[i][j].getOccupant().getColor(), i, j));
          } else if (type.equals("B")) {
            squares[i][j].occupy(new Bishop(squares[i][j].getOccupant().getColor(), i, j));
          } else if (type.equals("R")) {
            squares[i][j].occupy(new Rook(squares[i][j].getOccupant().getColor(), i, j));
          } else {
            throw new IllegalPromotionException("unknown promotion to " + type + ". Can be [Q],N,B,R");
          }
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
  
  public static String rankFileToCoord(int rank, int file) {
    return (new String[] {"a","b","c","d","e","f","g","h"})[file] + String.valueOf(rank + 1);
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
