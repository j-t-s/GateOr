import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;


public class Power extends Node{
	Image imgON = null;
	Image imgOFF = null;
	Image img = null;
  public boolean setInput(Node addedInput){
		return false;
	}
	public Node getInput(int index){
		return null;
	}
	public void clrInput(int index){}
	public Power(State state, Point coord, String name){
		super(state, coord, name);
		height = 64;
		width = 64;
		imgName = "powerOff.png";
		
		//get the images
		try {
			imgON = ImageIO.read(this.getClass().getClassLoader().getResource("powerOn.png"));
			imgOFF = ImageIO.read(this.getClass().getClassLoader().getResource("powerOff.png"));
		}catch (IOException e){
			System.out.println("Could not get Power images");
		}
	}
	@Override
	public void updateState(){}
	@Override
	public void draw(Graphics g){
		if (imgON == null || imgOFF == null){
			return;//Images were not loaded.
		}
		if (getState() == Node.State.ON){//Change the images appearance according to state
			img = imgON;
		}else{
			img = imgOFF;
		}
		//draw the image
		g.drawImage(img.getScaledInstance(width, height,  Image.SCALE_SMOOTH), getLocation().x, getLocation().y, null);
	}
}
