import java.awt.*;
import javax.swing.*;
import java.lang.Math;

public class canvasGraph extends Canvas{
	int xLeft;
	int xRight;
	int yUp;
	int yDown;
	double xMod;
	double yMod;
	int middlePointX;
	int middlePointY;
	int inCX;
	int inCY;
	String equation;
	
	public canvasGraph(int xS,int xE,int yS, int yE, String eqn){
		xLeft = xS;
		xRight = xE;
		yUp = yS;
		yDown = yE;
		equation = eqn;
		modCalc();
	}
	
	public void modCalc(){
    	xMod = (double)(1000/(double)(Math.abs(xLeft)+Math.abs(xRight)));
    	yMod = (double)(1000/(double)(Math.abs(yUp)+Math.abs(yDown)));
    	System.out.println (xMod);
		System.out.println (yMod);
    	middlePointX = (int)(xMod*Math.abs(xRight));
    	middlePointY = (int)(yMod*Math.abs(yUp));
    	System.out.println (middlePointX);
    	System.out.println (middlePointY);
    	
	}

  	public void paint(Graphics g){
    		g.setColor(Color.gray);
    		g.drawRect(middlePointX,0,1,1001);
    		g.drawRect(0,middlePointY,1001,1);
    		g.setColor(Color.black);
    		int gap = 0;
    		int current = 0;
    		int y = 0;
    		boolean decreasing = false;
    		Operation op1 = new Operation(equation);
    		for (int x = -middlePointX; x <=1000-middlePointX; x++){
    			y = (int)(middlePointY-((op1.calculate((double)x/xMod))*yMod));
    			//y=(int)(middlePointY-((testFeeder(x))*yMod));
    			gap = Math.abs(y - current);
    			
    			if(x==(-middlePointX)){
    				gap=1;
    			}
    			else{
    				if((y-current)>0){
    					decreasing = true;
    				}
    				else{
    					decreasing = false;
    				}
    			}
    			if(Math.abs(gap)>1001){
    				asymptote(x);
    			}
    			else{
    				if(decreasing){
    					g.drawRect(x+middlePointX,current-1,1,gap);
    				}
    				else{
    					g.drawRect(x+middlePointX,y,1,gap);
    				}
    			}
    			current = y;
    		}
   	}
   	public double testFeeder(int x){
   		double xBase = (double)x/xMod;
   		return xBase*xBase*xBase;
   	}
   	
   	public void asymptote(int x){
   		System.out.println ("asymptote");
   	}
}
	