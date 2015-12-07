import java.util.LinkedList;
import java.util.List;

import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.swing.JFileChooser;

public class Tools{
	private LinkedList<Node> nodeList;
	//I just put the file chooser in the tools method.
	private JFileChooser fileSelect = new JFileChooser();


	public Tools(LinkedList<Node> nodeList){
		this.nodeList = nodeList;
	}

	//used for testing, remove when actually finishing this up.
	public Tools(){
		
	}
	public void save(){

	}

	public void load(){

		try{
			NamedNodeMap n;
			List<Integer> coordHolder = new LinkedList<Integer>();
			List<String> inputHolder = new LinkedList<String>();
			int code = fileSelect.showOpenDialog(null);
			if(code == JFileChooser.APPROVE_OPTION){
				
				//TODO[Matt]Used if we have time, makes it so only xml files can be selected.  FileFilter.
				//fileSelect.setFileFilter(xmlFileFilter);
				
				Document xmlDoc = getDocument(fileSelect.getSelectedFile().toString());
				System.out.println("finished loading xml");
				
				xmlDoc.getDocumentElement();
				
				
				//lists holding all the <gate/>, <power/> and <output/> nodes.
				NodeList gate = xmlDoc.getDocumentElement().getElementsByTagName("gate");
				NodeList power = xmlDoc.getDocumentElement().getElementsByTagName("power");
				NodeList output = xmlDoc.getDocumentElement().getElementsByTagName("output");
				
				
				//parse gates.
				System.out.println("\nREADING GATES. . . ");
				
				for(int i = 0; i < gate.getLength(); i++){

					
					n = gate.item(i).getAttributes();
					
					switch(n.getNamedItem("type").getTextContent()){
					case "and":
						
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						

						//where we actually implement the new node.
						//nodeList.add(new AND(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder);//
						break;
					case "or":
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new OR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						break;
					case "not":
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new NOT(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						break;
					case "nand":
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new NAND(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						break;
					case "nor":
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new NOR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						break;
					case "xor":
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new XOR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						break;
					}
					
					System.out.println("\nCreating a " + n.getNamedItem("type").getTextContent().toUpperCase() + " gate with coords:");
					for (int j : coordHolder){
						System.out.print(j + ",");
					}
					
					System.out.println("Named: " + n.getNamedItem("name").getTextContent());
					coordHolder.clear();
					inputHolder.clear();

				} //end read gates

				System.out.println("\nREADING POWERS. . . ");
				//read powers
				for (int i = 0; i < power.getLength(); i++){
					//make a new power.
					n = power.item(i).getAttributes();
					
					for(String s : n.getNamedItem("coords").getTextContent().split(",")){
						coordHolder.add(Integer.parseInt(s));
					}

					
					System.out.print("\ncreating POWER with coords: ");
					for (int j : coordHolder){
						System.out.print(j + ",");
					}
					System.out.println("\nwith a state of: " + n.getNamedItem("state").getTextContent());
					
					
					System.out.println("named: " + n.getNamedItem("name").getTextContent());
					
					//The actual creation of the power node.
					//nodeList.add(new Power(n.getNamedItem("name").getTextContent()), coordHolder, Integer.parseInt(n.getNamedItem("state").getTextContent())));
					
					coordHolder.clear();
					inputHolder.clear();
					
				} //End powers
				
				
				System.out.println("\nREADING OUTPUTS. . . ");
				for (int i = 0; i < output.getLength(); i++){
					
					n = output.item(i).getAttributes();
					
					for(String s : n.getNamedItem("coords").getTextContent().split(",")){
						coordHolder.add(Integer.parseInt(s));
					}
					
					System.out.print("\ncreating OUTPUT with coords: ");
					for (int j : coordHolder){
						System.out.print(j + ",");
					}
					
					
					//We don't need to hold multiple inputs for the outputs.
					System.out.println("\nwith an input of: " + n.getNamedItem("input").getTextContent());
					
					
					System.out.println("named: " + n.getNamedItem("name").getTextContent());
					
					//nodeList.add(new Output(n.getNamedItem("name").getTextContent()), coordHolder, n.getNamedItem("input").getTextContent()));
					
					coordHolder.clear();
				}
			}
			
		} catch(Exception e){
			System.out.println(e.toString());
		}
	}

	private Document getDocument(String s){
		try{
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();

			fact.setIgnoringComments(true);

			DocumentBuilder builder = fact.newDocumentBuilder();

			return builder.parse(new InputSource(s));
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return null;

	} 
}
