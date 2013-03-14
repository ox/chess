
public class BoardSquare {
  private Piece piece;
  
  public BoardSquare() {
    piece = null;
  }
  
  public boolean isOccupied() {
    return piece == null;
  }
  
  public void occupy(Piece piece) {
    this.piece = piece;
  }
}
