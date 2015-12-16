public class NOT extends Gate{
  public NOT(java.awt.Point coord, String name){super(coord, name, "NOT.png");}
  @Override
  public void updateState(){//needs implemented
    if (this.getInput(0) == null){//IF null input, state is undefined.
		this.setState(Node.State.UNDEF);
		return;
	}
    if (this.getInput(0).getState() == Node.State.ON){//ON, so state is OFF
      this.setState(Node.State.OFF);
    }else if (this.getInput(0).getState() == Node.State.OFF ){//OFF, so ON
      this.setState(Node.State.ON);
    }else{//undefined then
      this.setState(Node.State.UNDEF);
    }
  }
}
