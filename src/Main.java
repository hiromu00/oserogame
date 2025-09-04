import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("オセロゲーム開始！");
        System.out.println("座標は行、列の順で入力してください（0-7）");
        
        while (!game.isGameOver()) {
            game.displayBoard();
            System.out.println("現在のプレイヤー: " + game.getCurrentPlayer());
            System.out.print("行を入力: ");
            
            try {
                int row = scanner.nextInt();
                System.out.print("列を入力: ");
                int col = scanner.nextInt();
                
                if (game.makeMove(row, col)) {
                    System.out.println("手を打ちました！");
                } else {
                    System.out.println("無効な手です。もう一度入力してください。");
                }
            } catch (Exception e) {
                System.out.println("数字を入力してください。");
                scanner.nextLine(); // バッファをクリア
            }
        }
        
        game.displayBoard();
        System.out.println("ゲーム終了！");
        System.out.println("勝者: " + game.getWinner());
        
        scanner.close();
    }
}