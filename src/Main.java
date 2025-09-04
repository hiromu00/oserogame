import java.util.Scanner;

public class Main {
    public static String[][] board = new String[9][9];
    public static String black = "●";
    public static String white = "○";
    public static String empty = " ";
    
    public static void main(String[] args) {
        StartConfig startConfig = new StartConfig();
        ShowBoard showBoard = new ShowBoard();
        Game game = new Game();
        CheckInputValue checkInput = new CheckInputValue();
        Scanner scanner = new Scanner(System.in);
        
        // ボード初期化
        startConfig.boardInit();
        startConfig.boardSetup();
        
        System.out.println("オセロゲーム開始！");
        System.out.println("座標は1-8で入力してください");
        
        while (!game.isGameOver()) {
            showBoard.showBoard(board);
            System.out.println("現在のプレイヤー: " + game.getCurrentPlayer());
            System.out.print("X座標を入力(1-8): ");
            
            try {
                String xInput = scanner.next();
                if (!checkInput.checkInputInt(xInput)) {
                    System.out.println("1-8の数字を入力してください");
                    continue;
                }
                
                System.out.print("Y座標を入力(1-8): ");
                String yInput = scanner.next();
                if (!checkInput.checkInputInt(yInput)) {
                    System.out.println("1-8の数字を入力してください");
                    continue;
                }
                
                int x = Integer.parseInt(xInput);
                int y = Integer.parseInt(yInput);
                
                if (game.makeMove(x, y)) {
                    System.out.println("手を打ちました！");
                } else {
                    System.out.println("無効な手です。もう一度入力してください。");
                }
            } catch (Exception e) {
                System.out.println("正しい形式で入力してください。");
                scanner.nextLine();
            }
        }
        
        showBoard.showBoard(board);
        System.out.println("ゲーム終了！");
        System.out.println("勝者: " + game.getWinner());
        
        scanner.close();
    }
}