
public class BoardSquare {
  private Piece piece;
  private String color;
  
  public BoardSquare(String color) {
    this.color = color;
    this.piece = null;
  }
  
  public boolean isOccupied() {
    return piece == null;
  }
  
  public Piece getOccupant() {
    return this.piece;
  }
  
  public void occupy(Piece piece) {
    this.piece = piece;
  }
  
  public String getColor() {
    return this.color;
  }
  
  public String toString() {
    return this.color;
  }
}
