public class OR extends Gate{
  public OR(java.awt.Point coord, String name){super(coord, name, "OR.png");}
  @Override
  public void updateState(){//needs implemented
    if (this.getInput(0) == null || this.getInput(1) == null){//IF null inputs, state is undefined.
		this.setState(Node.State.UNDEF);
		return;
	}
    if (this.getInput(0).getState() == Node.State.ON || this.getInput(1).getState() == Node.State.ON){//One is ON, so state is ON
      this.setState(Node.State.ON);
    }else if (this.getInput(0).getState() == Node.State.OFF && this.getInput(1).getState() == Node.State.OFF){//Both of them are OFF, so off
      this.setState(Node.State.OFF);
    }else{//at least one is undefined and at most one is OFF.
      this.setState(Node.State.UNDEF);
    }
  }
  
}
