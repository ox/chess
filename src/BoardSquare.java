
public class BoardSquare {
  private Piece piece;
  private String color;
  
  public BoardSquare(String color) {
    this.color = color;
    this.piece = null;
  }
  
  public boolean isOccupied() {
    return piece != null;
  }
  
  public Piece getOccupant() {
    return this.piece;
  }
  
  public void vacate() {
    this.piece = null;
  }
  
  public Piece occupy(Piece piece) {
    this.piece = piece;
    return piece; // so we can CHAIN
  }
  
  public String getColor() {
    return (this.color == "white" ? "  " : "##");
  }
  
  public String toString() {
    return (this.piece == null ? getColor() : this.piece.toString());
  }
}
