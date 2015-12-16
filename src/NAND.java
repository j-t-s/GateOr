public class NAND extends Gate{
  public NAND(java.awt.Point coord, String name){super(coord, name, "NAND.png");}
  @Override
  public void updateState(){//needs implemented
    if (this.getInput(0) == null || this.getInput(1) == null){//IF null inputs, state is undefined.
		this.setState(Node.State.UNDEF);
		return;
	}
    if (this.getInput(0).getState() == Node.State.ON && this.getInput(1).getState() == Node.State.ON){//Both are ON, so state is OFF
      this.setState(Node.State.OFF);
    }else if (this.getInput(0).getState() == Node.State.OFF || this.getInput(1).getState() == Node.State.OFF){//One of them is OFF, so ON
      this.setState(Node.State.ON);
    }else{//at least one is undefined and at most one is ON.
      this.setState(Node.State.UNDEF);
    }
  }
}
