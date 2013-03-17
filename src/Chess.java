
public class Chess {
  public static void main(String[] argv) {
    Board board = new Board();
    String player_turn = "1";
    
    // start the REPL
    System.out.println(board);
    board.flipBoard();
    player_turn = nextPlayer(player_turn);
    System.out.println(board);
    
    String coord = "a1";
    int[] pair = coordToRankFilePair(coord);
    System.out.println("{" + pair[0] + ", " + pair[1] + "}");
    coord = "d7";
    pair = coordToRankFilePair(coord);
    System.out.println("{" + pair[0] + ", " + pair[1] + "}");
    
    /*while (!board.hasWinner()) {
      
    }*/
  }
  
  private static String nextPlayer(String current_player) {
    return (current_player.equals("1") ? "2" : "1");
  }
  
  /**
   * Turns a FileRank pair into indices in the form {rank, file}. It's right, trust me.
   * 
   * Example:
   *  a1 => {0,0}
   *  d7 => {6,3}
   * 
   * @param coord
   * @return
   */
  private static int[] coordToRankFilePair(String coord) {
    return (new int[] {(coord.charAt(1) - '1'), coord.charAt(0) - 'a'});
  }
}
