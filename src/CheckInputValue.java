public class CheckInputValue {
    public boolean checkInputInt(String s) {
        if (s.matches("[1-8]]")) {
            return true;
        }
        return false;
    }
}
