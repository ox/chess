import java.util.ArrayList;

import exceptions.IllegalMoveException;


public class Chess {
  public static void main(String[] argv) {
    Board board = new Board();
    String player_turn = "black";
    
    System.out.println(board);

    try {
      board.movePiece("d1", "a4");
    } catch (IllegalMoveException e) {
      System.out.println("Error: " + e.getMessage());
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
