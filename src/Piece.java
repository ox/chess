
public class Piece implements Movable {
  protected int file, rank, times_moved;
  protected String color;
  protected String type;
  
  public int getTimesMoved() {
    return this.times_moved;
  }
  
  public boolean neverMoved() {
    return this.times_moved == 0;
  }
  
  public void incrMoved() {
    this.times_moved += 1;
  }
  
  public String getColor() {
    return color.equals("white") ? "w" : "b";
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

  @Override
  public boolean canMoveTo(int rank, int file) {
    return false;
  }
  
  @Override
  public String[] availableMovesFrom(int rank, int file) {
    return null;
  }
}
