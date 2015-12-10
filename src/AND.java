public class AND extends Gate{
  public AND(java.awt.Point coord, String name){super(coord, name);}
  @Override
  public void updateState(){
    if (this.getInput(0).getState() == Node.State.ON && this.getInput(1).getState() == Node.State.ON){//Both are ON, so state is ON
      this.setState(Node.State.ON);
    }else if (this.getInput(0).getState() == Node.State.OFF || this.getInput(1).getState() == Node.State.OFF){//One of them is OFF, so off
      this.setState(Node.State.OFF);
    }else{//at least one is undefined and at most one is ON.
      this.setState(Node.State.UNDEF);
    }
  }
  @Override
  public void draw(java.awt.Graphics g){//Drawing to the input will need added
    java.awt.image.BufferedImage img = null;
    String filename = "AND.png";
    //get the image
    try {img = javax.imageio.ImageIO.read(new java.io.File(filename));}catch (java.io.IOException e){System.out.println("Could not get "+filename);}
    //draw the image
    g.drawImage(img, getLocation().x, getLocation().y, null);
  }
}
