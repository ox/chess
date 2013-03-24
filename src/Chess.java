
public class Chess {
  public static void main(String[] argv) {
    Board board = new Board();
    String player_turn = "black";
    
    System.out.println(board);

    try {
      board.movePieceTo("f6", "f7");

    } catch (Exception e) {
      System.out.println("Error moving: " + e.getMessage());
    }
    
    System.out.println(board);    
    player_turn = nextPlayer(player_turn);
    
    /*while (!board.hasWinner()) {
      
    }*/
  }
  
  private static String nextPlayer(String current_player) {
    return (current_player.equals("black") ? "white" : "black");
  }
  
}
