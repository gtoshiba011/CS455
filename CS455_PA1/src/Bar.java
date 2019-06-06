// Name: PO HAO CHEN
// USC NetID: pohaoche
// CS 455 PA1
// Fall 2018

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 *
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 *
 */
public class Bar {

    // Instance Variables
    // for label
    private int bottomOfLabel;
    private String labelString;

    // for rectangle
    private int barX;
    private int barWidth;
    private int barHeight;
    private double scale;
    private Color color;


    // Methods
    // Public methods
    /**
     Creates a labeled bar.  You give the height of the bar in application
     units (e.g., population of a particular state), and then a scale for how
     tall to display it on the screen (parameter scale).

     @param bottom  location of the bottom of the label
     @param left  location of the left side of the bar
     @param width  width of the bar (in pixels)
     @param barHeight  height of the bar in application units
     @param scale  how many pixels per application unit
     @param color  the color of the bar
     @param label  the label at the bottom of the bar
     */
    public Bar(int bottom, int left, int width, int barHeight,
               double scale, Color color, String label) {

        this.bottomOfLabel = bottom;
        this.labelString = label;
        this.barX = left;
        this.barWidth = width;
        this.barHeight = (int) (barHeight * scale) ;
        this.color = color;
    }

    /**
     Draw the labeled bar.
     @param g2  the graphics context
     */
    public void draw(Graphics2D g2) {

        // create label
        Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBound = font.getStringBounds(this.labelString, context);
        int widthOfLabel = (int) labelBound.getWidth();
        int heightOfLabel = (int) labelBound.getHeight();

        // draw rectangle
        int barY = this.bottomOfLabel - heightOfLabel - this.barHeight;
        Rectangle rect = new Rectangle(this.barX, barY, this.barWidth, this.barHeight);
        g2.setColor(this.color);
        g2.fill(rect);

        // draw string
        int labelX = this.barX - (widthOfLabel - this.barWidth)/2;
        int labelY = this.bottomOfLabel;
        g2.setColor(Color.BLACK);
        g2.drawString(this.labelString, labelX, labelY);
    }
}