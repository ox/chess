
public class Chess {
  public static void main(String[] argv) {
    Board board = new Board();
    String player_turn = "1";

    try {
      board.movePieceTo("d7", "d5");
      board.movePieceTo("d2", "d4");
    } catch (Exception e) {
      System.out.println("Error moving: " + e.getMessage());
    }
    
    System.out.println(board);    
    player_turn = nextPlayer(player_turn);
    
    /*while (!board.hasWinner()) {
      
    }*/
  }
  
  private static String nextPlayer(String current_player) {
    return (current_player.equals("1") ? "2" : "1");
  }
  
}
