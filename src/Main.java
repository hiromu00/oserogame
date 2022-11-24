import java.util.Scanner;

public class Main {
    public static String black = "◯";
    public static String white = "●";
    public static String empty = " ";
    public static String board[][] = new String[9][9];

    private static int verticalInput;
    private static int besideInput;
    private static String tmpVerticalInput;
    private static String tmpBesideInput;

    static boolean  gameFlag;

    public static void main(String[] args){
        ShowBoard SB = new ShowBoard();
        StartConfig startConfig = new StartConfig();
        Reverse RC = new Reverse();
        CheckInputValue CII = new CheckInputValue();
        Scanner SC = new Scanner(System.in);

        System.out.println("オセロ開始");
        startConfig.boardInit();
        startConfig.boardSetup();
        boolean gameFlag = true;

        SB.showBoard(board);

        System.out.println("横の番号から数字で入力してください");
        tmpVerticalInput = SC.next();
        System.out.println("縦の番号から数字で入力してください");
        tmpBesideInput = SC.next();

        RC.putReverse(verticalInput,besideInput);
        SB.showBoard(board);
    }

}