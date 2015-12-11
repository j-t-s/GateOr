public class NAND extends Gate{
  public NAND(java.awt.Point coord, String name){super(coord, name);}
  @Override
  public void updateState(){//needs implemented
    
  }
  @Override
  public void draw(java.awt.Graphics g){//needs implemented
	java.awt.image.BufferedImage img = null;
    String filename = "NAND.png";
    //get the image
    try {img = javax.imageio.ImageIO.read(new java.io.File(filename));}catch (java.io.IOException e){System.out.println("Could not get "+filename);}
    //draw the image
    g.drawImage(img, getLocation().x, getLocation().y, null);
  }
}
