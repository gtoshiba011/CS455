public class Recursive {
    public static void main(String[] args) {

        System.out.println("EXP: true");
        System.out.println("ANS: " + helper(""));
        System.out.println("EXP: true");
        System.out.println("ANS: " + helper("()"));
        System.out.println("EXP: true");
        System.out.println("ANS: " + helper("(())"));
        System.out.println("EXP: true");
        System.out.println("ANS: " + helper("(((())))"));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper("("));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper("a"));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper("(()"));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper("())"));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper("(a)"));
        System.out.println("EXP: false");
        System.out.println("ANS: " + helper(")("));

    }
    public static boolean helper(String str) {
        if (str.equals("")) {
            return true;
        }
        if (str.charAt(0) == '(') {
            return str.charAt(str.length() - 1) == ')' && helper(str.substring(1, str.length()-1));
        }
        else {
            return false;
        }
    }
}
