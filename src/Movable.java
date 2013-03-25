import java.util.ArrayList;

public interface Movable {
  /**
   * A function that determines if a piece can move to a certain location.
   * For every side, we flip the board around so that increasing rank is 
   * considered "forward".
   * 
   * @param rank
   * @param file
   * @return
   */
  public boolean canMoveTo(int rank, int file);
  
  /**
   * Determine if the piece can attack a certain square. This will be a
   * copy of canMoveTo for almost all pieces but the pawn.
   * 
   * @param rank
   * @param file
   * @return
   */
  public boolean canAttack(int rank, int file);
  
  /**
   * Lists all the possible moves for the piece from the current position.
   *
   * @param rank
   * @param file
   * @return
   */
  public ArrayList<String> availableMoves();
}
