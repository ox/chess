
public class Board {
  private BoardSquare[][] squares;
  
  public Board() {
    String color = null;
    
    for (int i = 0; i < 8; i++) {
      color = "white";
      
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new BoardSquare(color);
        color = (color == "white" ? "black" : "white" );
      }
      color = (color == "white" ? "black" : "white" );
    }
  }
  
  public String toString() {
    String ret = "";
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        ret = ret.concat(squares[i][j].toString());
      }
      ret = ret.concat("\n");
    }
 
    return ret;
  }
}
