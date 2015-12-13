public class Power extends Node{
  public boolean setInput(Node addedInput){
		return false;
	}
	public Node getInput(int index){
		return null;
	}
	public void clrInput(int index){}
	public Power(State state, java.awt.Point coord, String name){
		super(state, coord, name);
		height = 64;
		width = 64;
		imgName = "powerOff.png";
	}
	@Override
	public void updateState(){//needs implemented
	
	}
	@Override
	public void draw(java.awt.Graphics g){
		java.awt.Image img = null;
		String filename = imgName;
		if (getState() == Node.State.ON){
				filename = "powerOn.png";
		}
		
		//get the image
		try {img = javax.imageio.ImageIO.read(new java.io.File(filename));}catch (java.io.IOException e){System.out.println("Could not get "+filename);}
		//draw the image
		g.drawImage(img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH), getLocation().x, getLocation().y, null);
	}
}
