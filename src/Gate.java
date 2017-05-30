import java.awt.geom.CubicCurve2D;
import java.awt.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public abstract class Gate extends Node{
	Node[] inputs = new Node[2];//For simplicity, inputs is an array of two items. The gate graphics only support two inputs.
	BufferedImage img = null;
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
	public Gate(Point coord, String name, String imgName){
		super(Node.State.UNDEF, coord, name);
		this.imgName = imgName;
		//get the image
		try {
			img = ImageIO.read(this.getClass().getClassLoader().getResource(imgName));
		}catch (IOException e){System.out.println("Could not get "+imgName);}
	}
	@Override
  public void draw(Graphics g){//Drawing to the input will need added
	Graphics2D g2 = (Graphics2D)g;
	
	//draw the image
	g.drawImage(img, getLocation().x, getLocation().y, null);
	//draw the lines
	if (this instanceof NOT){
		if (this.getInput(0) != null){
			CubicCurve2D c = new CubicCurve2D.Double();
			c.setCurve(getLocation().x + 10, getLocation().y+height/2,
				getLocation().x - 64, getLocation().y+height/2,
				getInput(0).getLocation().x+getInput(0).width + 64, getInput(0).getLocation().y+getInput(0).height/2,
				getInput(0).getLocation().x+getInput(0).width - 10, getInput(0).getLocation().y+getInput(0).height/2);
			Stroke tmpStroke = g2.getStroke();	
			g2.setStroke(new BasicStroke(3));
			g2.draw(c);
			g2.setStroke(tmpStroke);
		}
	}else{
		for (int in = 0; in < 2; in++){
			if (this.getInput(in) != null){
				CubicCurve2D c = new CubicCurve2D.Double();
				c.setCurve(getLocation().x + 10, getLocation().y+height/2 - 15 + in*30,
					getLocation().x - 64, getLocation().y+height/2 - 15 + in*30,
					getInput(in).getLocation().x+getInput(in).width + 64, getInput(in).getLocation().y+getInput(in).height/2,
					getInput(in).getLocation().x+getInput(in).width - 10, getInput(in).getLocation().y+getInput(in).height/2);
				Stroke tmpStroke = g2.getStroke();	
				g2.setStroke(new BasicStroke(3));
				g2.draw(c);
				g2.setStroke(tmpStroke);
			}
		}
	}
  }
}
