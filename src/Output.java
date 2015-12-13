import java.awt.geom.CubicCurve2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;

public class Output extends Node{
	Node input = null;
  	public boolean setInput(Node addedInput){
		if(addedInput instanceof Output){
			return false;
		}
		if (input == null){
			input = addedInput;
		}else{
			return false;
		}
		return true;
	}
	public Node getInput(int index){//regardless of index, input is returned
		return input;
	}
	public void clrInput(int index){//regardless of index, input is set to null
		input = null;
	}
	public Output(java.awt.Point coord, String name){
		super(Node.State.UNDEF, coord, name);
		height = 64;
		width = 64;
		imgName = "OutputOFF.png";
	}
	@Override
	public void updateState(){//needs implemented
		this.setState(this.getInput(0).getState());
	}
	@Override
	public void draw(java.awt.Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		java.awt.Image img = null;
		String filename = imgName;
		if (getState() == Node.State.ON){
				filename = "OutputON.png";
		}
		
		//get the image
		try {img = javax.imageio.ImageIO.read(new java.io.File(filename));}catch (java.io.IOException e){System.out.println("Could not get "+filename);}
		//draw the image
		g.drawImage(img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH), getLocation().x, getLocation().y, null);
		if (this.getInput(0) != null){
			CubicCurve2D c = new CubicCurve2D.Double();
			c.setCurve(getLocation().x, getLocation().y+height/2,
				getLocation().x - 64, getLocation().y+height/2,
				getInput(0).getLocation().x+getInput(0).width + 64, getInput(0).getLocation().y+getInput(0).height/2,
				getInput(0).getLocation().x+getInput(0).width, getInput(0).getLocation().y+getInput(0).height/2);
			Stroke tmpStroke = g2.getStroke();	
			g2.setStroke(new BasicStroke(3));
			g2.draw(c);
			g2.setStroke(tmpStroke);
			//g.drawLine((int)getLocation().x, (int)getLocation().y+height/2, (int)this.getInput(0).getLocation().x+width, (int)this.getInput(0).getLocation().y+height/2);
			//g.drawLine((int)getLocation().x, (int)getLocation().y+height/2, (int)this.getInput(0).getLocation().x+width, (int)this.getInput(0).getLocation().y+height/2);
		}
	}
}
