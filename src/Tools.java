import java.util.LinkedList;
import java.util.List;

import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.swing.JFileChooser;

import java.awt.Point;
import java.util.HashMap;

public class Tools{
	private LinkedList<Node> nodeList;
	//I just put the file chooser in the tools method.
	private JFileChooser fileSelect = new JFileChooser();
	
	//The string key is the nodes name, the Node is the reference to the node.
	private HashMap<String, Node> nodeLookupTable = new HashMap<String, Node>();
	//The string key is the nodes name, the string array is the name of nodes that are the input
	private HashMap<String, String[]> nodeInputLookup = new HashMap<String, String[]>();


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
						Node node = new AND(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
						
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
						Node node = new OR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
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
						Node node = new NOT(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0)}//Not only has one input
							);
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
						Node node = new NAND(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
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
						Node node = new NOR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
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
						Node node = new XOR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
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
					Node node = new Power(
						new Point(coordHolder.get(0), coordHolder.get(1)),
						n.getNamedItem("name").getTextContent());
					nodeList.add(node);
					nodeLookupTable.put(
						n.getNamedItem("name").getTextContent(), node);
					//nodeInputLookup.put();//Has no inputs
					
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
					Node node = new Output(
						new Point(coordHolder.get(0), coordHolder.get(1)),
						n.getNamedItem("name").getTextContent());
					nodeList.add(node);
					nodeLookupTable.put(
						n.getNamedItem("name").getTextContent(), node);
					nodeInputLookup.put(
						n.getNamedItem("name").getTextContent(),
						new String[]{inputHolder.get(0)}//output only has one input
						);
					coordHolder.clear();
				}
				for (Node nodes: NodeList){//Loop through all the nodes
					String[] inputs = nodeInputLookup.get(nodes.name);//Get the string array of inputs
					for (String inputName: inputs){//For all the inputs
						if (!nodes.setInput(//Check to see if too many inputs are given
							nodeLookUpTable.get(inputName)//Get the node reference from the name
						)){
							System.out.println(nodes.name+" was not added correctly.");//Say that it too many inputs were given.
						}
					}
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
