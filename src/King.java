
public class King extends Piece {
  public King(String color, int rank, int file) {
    this.type = "K";
    this.color = color;
    this.rank = rank;
    this.file = file;
    this.times_moved = 0;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    // if we are moving left or right horizontally and haven't moved yet, this is probably
    // a castle. We don't know if we pass any dangerous squares so we'll return true and
    // let the Board figure that out
    if (Math.abs(file - this.file) == 2 && this.times_moved == 0 
        && Math.abs(rank - this.rank) == 0) return true;
    
    if (Math.abs(rank - this.rank) > 1 || Math.abs(file - this.file) > 1) return false;
    return true;
  }
}
