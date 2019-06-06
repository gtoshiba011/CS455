// Name: PO HAO CHEN
// USC NetID: pohaoche
// CS 455 PA1
// Fall 2018

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

// for dummy label
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * class CoinSimComponent
 *
 * This component draw three bars
 *
 */
public class CoinSimComponent extends JComponent {

    // Constants
    private static final Color TWO_HEAD_COLOR = Color.RED;
    private static final Color TWO_TAIL_COLOR = Color.BLUE;
    private static final Color HEAD_TAIL_COLOR = Color.GREEN;

    private static final int BAR_WIDTH = 50;
    private static final int VERTICAL_BUFFER = 50;



    // Instance variables
    private CoinTossSimulator simulator;



    // Methods
    // Public methods
    /**
     * Constructor will execute the method "run" for simulator
     * @param trials trial times for run
     */
    public CoinSimComponent(int trials) {

        this.simulator = new CoinTossSimulator();
        this.simulator.run(trials);

        //printInfo(simulator, trials);
    }

    /**
     * Override the method in JComponent, and draw three bars
     * This method will be called every time a window gets resized or iconified and de-iconified
     * @param g graph reference for draw
     */
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        // dummy label
        Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds("DUMMY", context);
        double heightOfLabel = labelBounds.getHeight();

        // common usage
        int bottomOfLabel = getHeight() - VERTICAL_BUFFER;
        int spaceWidth = (getWidth() - 3 * BAR_WIDTH) / 4;
        double scale = (getHeight() - 2 * VERTICAL_BUFFER - heightOfLabel) / this.simulator.getNumTrials();

        // create two head bar
        Bar twoHeadBar = createTwoHeadBar(bottomOfLabel, spaceWidth, scale);

        // create head tail bar
        Bar headTailBar = createHeadTailBar(bottomOfLabel, spaceWidth, scale);

        // create two tail bar
        Bar twoTailBar = createTwoTailBar(bottomOfLabel, spaceWidth, scale);

        twoHeadBar.draw(g2);
        headTailBar.draw(g2);
        twoTailBar.draw(g2);
    }


    // Private methods
    /**
     * create the two head bar
     * @param bottomOfLabel bottom location of the label
     * @param spaceWidth space width between each bar
     * @param scale pixels each unit
     * @return two head bar
     */
    private Bar createTwoHeadBar(int bottomOfLabel, int spaceWidth, double scale) {

        int barX = spaceWidth;
        int barHeight = this.simulator.getTwoHeads();
        int percentage = (int) Math.round(100.0 * this.simulator.getTwoHeads() / this.simulator.getNumTrials());
        String labelString = "Two Heads: " + String.valueOf(this.simulator.getTwoHeads()) + " (" + String.valueOf(percentage) + "%)";
        return new Bar(bottomOfLabel, barX, BAR_WIDTH, barHeight, scale, TWO_HEAD_COLOR, labelString);
    }

    /**
     * create the head tail bar
     * @param bottomOfLabel bottom location of the label
     * @param spaceWidth space width between each bar
     * @param scale pixels each unit
     * @return head-tail bar
     */
    private Bar createHeadTailBar(int bottomOfLabel, int spaceWidth, double scale) {

        int barX = 2 * spaceWidth + BAR_WIDTH;
        int barHeight = this.simulator.getHeadTails();
        int percentage = (int) Math.round(100.0 * this.simulator.getHeadTails() / this.simulator.getNumTrials());
        String labelString = "A Head and a Tail: " + String.valueOf(this.simulator.getHeadTails()) + " (" + String.valueOf(percentage) + "%)";
        return new Bar(bottomOfLabel, barX, BAR_WIDTH, barHeight, scale, HEAD_TAIL_COLOR, labelString);
    }

    /**
     * create the two tail bar
     * @param bottomOfLabel bottom location of the label
     * @param spaceWidth space width between each bar
     * @param scale pixels each unit
     * @return two tail bar
     */
    private Bar createTwoTailBar(int bottomOfLabel, int spaceWidth, double scale) {

        int barX = 3 * spaceWidth + 2 * BAR_WIDTH;
        int barHeight = this.simulator.getTwoTails();
        int percentage = (int) Math.round(100.0 * this.simulator.getTwoTails() / this.simulator.getNumTrials());
        String labelString = "Two Tails: " + String.valueOf(this.simulator.getTwoTails()) + " (" + String.valueOf(percentage) + "%)";
        return new Bar(bottomOfLabel, barX, BAR_WIDTH, barHeight, scale, TWO_TAIL_COLOR, labelString);
    }

    /**
     * Print the information in CoinTossSimulator, including #twoHeads, #twoTails, #headTails
     * Only for personal test, not used in grading
     * @param simulator is the simulator from which you want to get information
     * @param expectValue is your expect output when unit test
     */
    private void printInfo(CoinTossSimulator simulator, int expectValue) {

        System.out.println("Number of trials [exp:" + expectValue + "]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println(expectValue == simulator.getNumTrials() ? "true" : "false");
    }
}
