// Name: PO HAO CHEN
// USC NetID: pohaoche
// CS 455 PA1
// Fall 2018

/**
 * class CoinTossSimulatorTester
 *
 * This class contains a main function, and it is a unit test for CoinTossSimulator
 */
public class CoinTossSimulatorTester {

    /**
     * main
     * PURPOSE
     * 1. In the main function, the trails is given and fixed
     * 2. After finish simulation, it will compare the total trials with expected answer
     * 3. Finally, the information after each operation(i.e., new, run(), reset()) will be shown
     *
     * HOW TO RUN
     * After compile, enter "java CoinTossSimulator" in terminal
     * @param args input parameter of main function
     */
    public static void main(String[] args) {

        CoinTossSimulatorTester simuTester = new CoinTossSimulatorTester();
        int totalTrialCount = 0;
        int trials;


        // test1: constructor
        CoinTossSimulator simulator = new CoinTossSimulator();
        System.out.println("After constructor:");
        simuTester.printInfo(simulator, 0);

        // test2: run(10)
        trials = 10;
        totalTrialCount += trials;
        simulator.run(trials);
        System.out.println("After run(" + trials + ")");
        simuTester.printInfo(simulator, totalTrialCount);

        // test3: run(100)
        trials = 100;
        totalTrialCount += trials;
        simulator.run(trials);
        System.out.println("After run(" + trials + ")");
        simuTester.printInfo(simulator, totalTrialCount);

        // test4: reset
        simulator.reset();
        totalTrialCount = 0;
        System.out.println("After reset:");
        simuTester.printInfo(simulator, totalTrialCount);

        // test5: run(1000) after reset
        trials = 1000;
        totalTrialCount += trials;
        simulator.run(trials);
        System.out.println("After run(" + trials + ")");
        simuTester.printInfo(simulator, totalTrialCount);

        // test6: run(500) after reset
        trials = 500;
        totalTrialCount += trials;
        simulator.run(trials);
        System.out.println("After run(" + trials + ")");
        simuTester.printInfo(simulator, totalTrialCount);
    }

    // Methods
    // Private methods
    /**
     * Print the information in CoinTossSimulator, including #twoHeads, #twoTails, #headTails
     * @param simulator is the simulator from which you want to get information
     * @param expectValue is your expect output when unit test
     */
    private void printInfo(CoinTossSimulator simulator, int expectValue) {

        System.out.println("Number of trials [exp:" + expectValue + "]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        // [BUG, need to check whether add up is also correct
        System.out.println(expectValue == simulator.getNumTrials() ? "true" : "false");
        System.out.println(expectValue == simulator.getNumTrials() &&
                simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()) ? "true" : "false");
    }
}