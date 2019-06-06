import java.util.ArrayList;

public class RackTester {
    public static void main(String[] args) {
        String word = "baa";
        // unique: abc, mult: [2, 2, 1]
        Rack rack = new Rack(word);
        ArrayList<String> list = rack.getAllSubsets();
        System.out.println("Size of subset for multiset " + word + " is " + list.size());
        for (String str: list) {
            System.out.print(str + ", ");
        }
    }
}
