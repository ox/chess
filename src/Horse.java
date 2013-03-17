
public class Horse extends Piece {
  public Horse(String color, int rank, int file) {
    this.type = "H";
    this.color = color;
    this.rank = rank;
    this.file = file;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    // can't move diagonally
    if (Math.abs(rank - this.rank) == Math.abs(file - this.file)) return false;
    
    // can't move vertically
    if (Math.abs(rank - this.rank) > 0 && file == this.file) return false;
    
    // can't move horizontally
    if (Math.abs(file - this.file) > 0 && rank == this.rank) return false;
    
    // can't move more than 2 squares away
    if (Math.abs(rank - this.rank) > 2 && Math.abs(file - this.file) > 2 ) return false;
    
    return true;

  }
}
