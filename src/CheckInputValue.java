public class CheckInputValue {
    public boolean checkInputInt(String s) {
        if (s.matches("[1-8]")) {  // 正規表現を修正
            return true;
        }
        return false;
    }
}
