import java.util.Scanner;
import java.util.ArrayList;

public class ReadTester {
   static public void main(String[] args) {

      Scanner in = new Scanner(System.in);
      ArrayList<Integer> arrList = new ArrayList<>();
      while (true) {
         System.out.print("Enter a space separated list of numbers: ");
         String line = in.nextLine();
         arrList.clear();
         Scanner lineScanner = new Scanner(line);
         while (lineScanner.hasNextInt()) {
            arrList.add(lineScanner.nextInt());
         }
         System.out.println("numbers were: " + arrList);
      }
   }
}
