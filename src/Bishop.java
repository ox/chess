
public class Bishop extends Piece {
  public Bishop(String color, int rank, int file) {
    this.type = "B";
    this.color = color;
    this.rank = rank;
    this.file = file;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    // move diagonally
    if (Math.abs(rank - this.rank) == Math.abs(file - this.file)) return true;
    return false;
  }
}
