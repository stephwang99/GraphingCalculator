/**
 * @(#)graphingCalculatorOutput.java
 *
 *
 * @author 
 * @version 1.00 2016/11/3
 */
import java.awt.*;
import javax.swing.*;

public class graphingCalculatorOutput extends JFrame {
	public canvasGraph myCanvas;
	
    public graphingCalculatorOutput(int xS,int xE, int yS, int yE, String equation) {
    	this.setSize(1001,1001);
    	myCanvas = new canvasGraph(xS,xE,yS,yE, equation);
    	this.add(myCanvas);
    	this.setVisible(true);
    }
}
    
