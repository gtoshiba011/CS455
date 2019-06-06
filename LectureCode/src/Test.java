
// this is a test file
//import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.*;
import java.awt.Rectangle;
import java.util.Arrays;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class Test {
    public static void main(String [] args) {


        Set<int[]> mineSet = new HashSet<>();
        boolean[][] mineData = {{true, false}, {false, true}};

        for(int i = 0; i < mineData.length; i++) {
            for(int j = 0; j < mineData[0].length; j++) {
                if(mineData[i][j]) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    mineSet.add(arr);
                    if(mineSet.contains(arr)) {
                        System.out.println("mine (" + i + "," + j + ") exist.");
                    }
                }
            }
        }
        for(int i = 0; i < mineData.length; i++) {
            for (int j = 0; j < mineData[0].length; j++) {
                int[] arr = new int[2];
                arr[0] = i;
                arr[1] = j;
                if(mineSet.contains(arr)){
                    System.out.println("mine (" + i + "," + j + ") exist.");
                }
                else{
                    System.out.println("mine (" + i + "," + j + ") doesn't exist.");
                }
            }
        }

        //String str = "TEST";
        //System.out.println(str.substring(3));

        //Integer i = 0;
        //System.out.println("before modify: " + i);
        //modify(i);
        //System.out.println("after modify: " + i);



        // *** Scanner *** //
        // solution1: use nextLine to consume '\n' in "27\n"
        //Scanner in = new Scanner(System.in);
        //System.out.print("Enter your age:");
        //int age = in.nextInt();
        //// consume '\n'
        ////in.nextLine();
        //System.out.print("Enter your name:");
        ////String name = in.next();
        //String name = in.nextLine();
        //System.out.print("Age:"  + age + ", name:" + name + "END\n");


        // solution2: use nextLine and transfer to Type you want
        //System.out.print("Enter your age:");
        //int age = Integer.parseInt(in.nextLine());
        //System.out.print("Enter your name:");
        //String name = (String) in.nextLine();
        //System.out.println("Age: "  + age + ", name: " + name + "END");
        // " 123 " -> error, parseInt cannot have space
        // "123"
        // "   Howard   "
        // >> "Age: 123, name:   Howard   END"
        // (String) will not consume space

        // Solution 3: use trim()
        //System.out.print("Enter your age(int):");
        //int age = Integer.parseInt(in.nextLine().trim());
        //System.out.print("Enter your eyesight:");
        //double eyesight = Double.parseDouble(in.nextLine().trim());
        //System.out.print("Enter your name:");
        //String name = in.nextLine().trim();
        //System.out.println("Age: "  + age + ", eyesignt: " + eyesight + ", name: " + name + "<END>");

        //if (in.hasNextInt()) {
        //    int testin = in.nextInt();
        //}

        /// *** copyOf array *** //
        //int[] names = {1, 2, 3, 4, 5};
        //int[] newNames = Arrays.copyOf(names, 10);
        //out.println(Arrays.toString(newNames));


        // *** double *** ///
        //double i = (1 - 0.1) / 3;
        //// exp: 0.3
        //System.out.println(i);


        // *** ArrayList *** //
        //ArrayList<String> nameAL = new ArrayList<>();
        //nameAL.add("Howard");
        //nameAL.add("Amy");
        //for (int j = 0; j < nameAL.size(); j++) {
        //    nameAL.set(j, "The " + nameAL.get(j) + "-inator");
        //}


        // *** Spring 18 Midterm 1 P4 *** //
        //int second = 59;
        //int minute = 59;
        //int hour = 22;

        //if (second == 59) {
        //    second = 0;
        //    if (minute == 59) {
        //        minute = 0;
        //        if (hour == 23) {
        //            hour = 0;
        //        }
        //        else {
        //            hour++;
        //        }
        //    }
        //    else {
        //        minute++;
        //    }
        //}
        //else {
        //    second++;
        //}
        //out.printf("%02d:%02d:%02d\n", hour, minute, second);

        // *** Spring 18 Midterm 1 P6 *** //
        //ArrayList<Integer> list1 = new ArrayList<>();
        //ArrayList<Integer> list2 = new ArrayList<>();
        ////list1.add(3);
        ////list1.add(4);
        ////list1.add(6);
        ////list2.add(9);
        ////list2.add(2);
        ////list2.add(7);
        //ArrayList<Integer> newList = new ArrayList<>();
        //int i = 0;
        //while (i < Math.max(list1.size(), list2.size())) {
        //    int num1 = i < list1.size() ? list1.get(i) : 0;
        //    int num2 = i < list2.size() ? list2.get(i) : 0;
        //    newList.add(num1 + num2);
        //    i++;
        //}
        //out.println(newList);
        //out.println(2 + 3 + 5);

        // *** Spring 17 Midterm 1 P1 *** //
        //Rectangle box = new Rectangle(6, 8, 20, 30);
        //foo(box);
        //Rectangle house = null;
        //foo(house);
        //out.println(box.getX() + " " + box.getY());
        //out.println(house.getX() + " " + house.getY());

        // *** Spring 17 Midterm 1 P5 *** //
        //int[] vals = {};
        ////vals = {5, 7, 5, 8, 5};
        //int max5row = 0, fiveRow = 0;
        //int max8row = 0, eightRow = 0;
        //for (int i = 0; i < vals.length; i++) {
        //    if (vals[i] == 5) {
        //        fiveRow++;
        //    }
        //    else {
        //        fiveRow = 0;
        //    }
        //    if (vals[i] == 8) {
        //        eightRow++;
        //    }
        //    else {
        //        eightRow = 0;
        //    }
        //    max5row = Math.max(max5row, fiveRow);
        //    max8row = Math.max(max8row, eightRow);
        //}
        //if (max5row < 2 && max8row < 2) {
        //    out.println(false);
        //}
        //else if (max5row >= 2 && max8row >= 2) {
        //    out.println(false);
        //}
        //else {
        //    out.println(true);
        //}

        // *** implicit parameter this *** //
        //Student stud = new Student("Howard", 1.8);
        //out.println(stud.getName());
        //out.println(stud.getHeight());

        //// *** Fall 17 Midterm 1 P5 *** //
        //int[] vals1 = {3, 3};
        //int[] vals2 = {1, 1};
        //int[] vals3 = {3, 3, 3};
        //int[] vals4 = {3, 3, 1, 3, 3};
        //int[] vals5 = {1, 3, 3, 3, 3, 3, 3};
        //int[] vals6 = {2, 3, 3, 3, 3, 3, 2, 3};
        //int[] vals7 = {3, 3, 3, 1, 3, 3, 3};
        //out.println("[test1] exp: 0, ans: " + tripleCount(vals1));
        //out.println("[test2] exp: 0, ans: " + tripleCount(vals2));
        //out.println("[test3] exp: 1, ans: " + tripleCount(vals3));
        //out.println("[test4] exp: 0, ans: " + tripleCount(vals4));
        //out.println("[test5] exp: 2, ans: " + tripleCount(vals5));
        //out.println("[test6] exp: 1, ans: " + tripleCount(vals6));
        //out.println("[test7] exp: 2, ans: " + tripleCount(vals7));

    }
    public static int tripleCount(int [] vals) {
        int tripleCount = 0;
        for (int i = 2; i < vals.length; i++) {
            if (3 == vals[i] && vals[i] == vals[i-1] && vals[i-1] == vals[i-2]) {
                tripleCount++;
                i += 2;
            }
        }
        return tripleCount;
    }

    public static void foo(Rectangle r) {
        r = new Rectangle(60, 70, 15, 20);
        r.translate(5, 10);
        int r123 = 3;
        int[] x = new int[3];
    }

    public static void modify(Integer i) {
        i = 3;
    }
}