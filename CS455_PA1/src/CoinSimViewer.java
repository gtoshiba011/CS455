// Name: PO HAO CHEN
// USC NetID: pohaoche
// CS 455 PA1
// Fall 2018

import java.util.Scanner;
import javax.swing.JFrame;

/**
 * This class contains main function
 */
public class CoinSimViewer {

    // Instance variables
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 500;

    /**
     * main
     * PURPOSE
     * 1. In the main function, you would be requested to enter the number of trials
     * 2. The enter number should be larger than 0, or it will show error message and you need to enter again
     * 3. After enter the trials, a component and a window will be created and the window with graph will shown
     *
     * HOW TO RUN
     * 1. After compile, enter "java CoinSimViewer" in the terminal
     * 2. Enter the trail number
     * 3. You can resize the window
     *
     * @param args input parameter of main function
     */

    public static void main(String[] args) {

        // prompt for number of trial and check whether > 0
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of trials: ");
        int trials = in.nextInt();
        in.nextLine();
        while (trials < 1) {
            System.out.println("ERROR: Number entered must be greater than 0.");

            // prompt again
            System.out.print("Enter number of trials: ");
            trials = in.nextInt();
            in.nextLine();
        }

        // create component with trials
        CoinSimComponent component = new CoinSimComponent(trials);

        JFrame frame = new JFrame();
        // [BUG, do not use magic number!]
        //frame.setSize(800, 500);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(component);
        frame.setVisible(true);
    }

}
