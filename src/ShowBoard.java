public class ShowBoard {

    public void showBoard(String[][] board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                System.out.print("｜" + board[j][i]);

                if (j == 8){
                    System.out.println("｜");
                }
            }
        }
    }
}
