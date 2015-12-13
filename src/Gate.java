import java.awt.geom.CubicCurve2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;

public abstract class Gate extends Node{
	Node[] inputs = new Node[2];//For simplicity, inputs is an array of two items. The graphics only support two inputs.
	public boolean setInput(Node addedInput){
		if(addedInput instanceof Output){
			return false;
		}
		if (inputs[0] == null){
			inputs[0] = addedInput;
		}else if (inputs[1] == null && !(this instanceof NOT)){
			inputs[1] = addedInput;
		}else{
			return false;
		}
		return true;
	}
	public Node getInput(int index){
		if (index == 0 || index == 1){
			return inputs[index];
		}else{
			return null;
		}
	}
	public void clrInput(int index){
		if (index == 0 || index == 1){
			inputs[index] = null;
		}
	}
	public Gate(java.awt.Point coord, String name){
		super(Node.State.UNDEF, coord, name);
	}
	@Override
  public void draw(java.awt.Graphics g){//Drawing to the input will need added
	Graphics2D g2 = (Graphics2D)g;
	java.awt.image.BufferedImage img = null;
	String filename = imgName;
	//get the image
	try {img = javax.imageio.ImageIO.read(new java.io.File(filename));}catch (java.io.IOException e){System.out.println("Could not get "+filename);}
	//draw the image
	g.drawImage(img, getLocation().x, getLocation().y, null);
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
	if (this.getInput(1) != null){
		CubicCurve2D c = new CubicCurve2D.Double();
		c.setCurve(getLocation().x, getLocation().y+height/2,
			getLocation().x - 64, getLocation().y+height/2,
			getInput(1).getLocation().x+getInput(1).width + 64, getInput(1).getLocation().y+getInput(1).height/2,
			getInput(1).getLocation().x+getInput(1).width, getInput(1).getLocation().y+getInput(1).height/2);
		Stroke tmpStroke = g2.getStroke();	
		g2.setStroke(new BasicStroke(3));
		g2.draw(c);
		g2.setStroke(tmpStroke);
		//g.drawLine((int)getLocation().x, (int)getLocation().y+height/2, (int)this.getInput(0).getLocation().x+width, (int)this.getInput(0).getLocation().y+height/2);
		//g.drawLine((int)getLocation().x, (int)getLocation().y+height/2, (int)this.getInput(0).getLocation().x+width, (int)this.getInput(0).getLocation().y+height/2);
	}
  }
}
