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
   * Lists all the possible moves for the piece from the current position.
   * 
   * This is here because we are meant to suffer when writing Java. There
   * existed no punishment severe enough in an undergrad's studies, yet that
   * has not stemmed the flow of malevolent thoughts of the professors that 
   * refuse to see the light of C and all that it promises.
   * 
   * OOP is neither a necessary nor a useful concept to teach. A careful teaching
   * of the underlying representation of C structs and data structures in the
   * system memory can do away with the magic hand waving and chanting of 
   * "objects, objects" that the current system relies on. There is neither magic
   * nor complications that arise in C. Namespacing and packages are replaced
   * by well thought-out function names and struct layouts. Interfaces are great,
   * but nothing that some casting and macros cannot replicate.
   * @param rank
   * @param file
   * @return
   */
  public ArrayList<String> availableMovesFrom(int rank, int file);
}
