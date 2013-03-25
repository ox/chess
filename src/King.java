
public class King extends Piece {
  protected boolean under_check;
  
  public King(String color, int rank, int file) {
    this.type = "K";
    this.color = color;
    this.rank = rank;
    this.file = file;
    this.times_moved = 0;
    this.under_check = false;
  }
  
  /**
   * Is the King under check?
   * @return The checked'ed-ness of the King
   */
  public boolean isUnderCheck() {
    return this.under_check;
  }
  
  /**
   * Place a King under check
   */
  public void check() {
    this.under_check = true;
  }
  
  /**
   * A King has escaped check
   */
  public void free() {
    this.under_check = false;
  }
  
  @Override
  public boolean canMoveTo(int rank, int file) {
    // if we are moving left or right horizontally and haven't moved yet, this is probably
    // a castle. We don't know if we pass any dangerous squares so we'll return true and
    // let the Board figure that out
    // if (Math.abs(file - this.file) == 2 
    //     && this.times_moved == 0
    //     && Math.abs(rank - this.rank) == 0) return true;
    
    if (Math.abs(rank - this.rank) > 1 || Math.abs(file - this.file) > 1) return false;
    return true;
  }
}
