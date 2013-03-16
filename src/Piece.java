
public class Piece {
  private int file, rank, color;
  private String type;
  
  public String getColor() {
    return color == 0 ? "b" : "w";
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getFile() {
    // lol tricks
    return (String) (new Object[] {'a', 'b','c','d','e','f','g','h'})[this.file];
  }
  
  public String getRank() {
    return String.valueOf(this.rank);
  }
  
  public String toString() {
    return getColor() + getType();
  }
}
