
public class Rook extends Piece {
  public Rook(String color, int rank, int file) {
    this.type = "R";
    this.color = color;
    this.file = file;
    this.rank = rank;
    this.times_moved = 0;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    // move vertically
    if (Math.abs(rank - this.rank) > 0 && file == this.file) return true;
    
    // move horizontally
    if (Math.abs(file - this.file) > 0 && rank == this.rank) return true;
    return false;
  }
}
