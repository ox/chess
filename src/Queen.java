
public class Queen extends Piece {
  public Queen(String color, int rank, int file) {
    this.type = "Q";
    this.color = color;
    this.rank = rank;
    this.file = file;
    this.times_moved = 0;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    if (rank == this.rank && file == this.file) return false;
    
    // move diagonally
    if (Math.abs(rank - this.rank) == Math.abs(file - this.file)) return true;
    
    // move vertically
    if (Math.abs(rank - this.rank) > 0 && file == this.file) return true;
    
    // move horizontally
    if (Math.abs(file - this.file) > 0 && rank == this.rank) return true;
    return false;
  }
}
