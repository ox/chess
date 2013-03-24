import java.util.ArrayList;


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
  
  public int rawRank() {
    return this.rank;
  }
  
  public int rawFile() {
    return this.file;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getFile() {
    // lol tricks
    return (new String[] {"a", "b","c","d","e","f","g","h"})[this.file];
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
  public ArrayList<String> availableMovesFrom(int rank, int file) {
    ArrayList<String> spaces = new ArrayList<String>();
    String[] files = {"a", "b","c","d","e","f","g","h"};
    
    for (int r = 0; r < 8; r++) {
      for (int f = 0; f < 8; f++) {
        if (this.canMoveTo(r, f)) {
          spaces.add(files[f] + String.valueOf(r+1) );
        }
      }
      
    }
    
    return spaces;
  }
}
