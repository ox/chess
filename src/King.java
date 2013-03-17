
public class King extends Piece {
  public King(String color, int rank, int file) {
    this.type = "K";
    this.color = color;
    this.rank = rank;
    this.file = file;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    if (Math.abs(rank - this.rank) > 1 || Math.abs(file - this.file) > 1) return false;
    return true;
  }
}
