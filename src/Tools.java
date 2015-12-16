import java.util.LinkedList;
import java.util.List;

import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import java.awt.Container;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	
	public String stateParser (Node n){
		if (n.getState().equals(Node.State.ON)){
			return "1";
		}
		else if (n.getState().equals(Node.State.OFF)){
			return "0";
		}
		else{
			return "-1";
		}
	}
	
	public void save(){
		
		int code = -1;
		
		code = fileSelect.showOpenDialog(null); // the null means we aren't parented with a jframe. We can change this so it minimizes/opens at a specific spot dictated by the parent.
		
		Element Gate;
		Element Power;
		Element Output;
		try{
			//TODO[Matt]have to make sure the selected save location is good. We can come back and add some code so that the current working file auto saves.
			if(code == JFileChooser.APPROVE_OPTION){
				DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuild = documentFactory.newDocumentBuilder();
				
				Document mainDoc = documentBuild.newDocument();
				Element root = mainDoc.createElement("NodeList");
				mainDoc.appendChild(root);
				
				for(Node n : nodeList){
					
					if (n.getClass().getName().equals("AND")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "and");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords",(int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							try{//We do this because if a node doesn't have inputs it can't access the names of it's inputs.
								Gate.setAttribute("inputs", n.getInput(0).name+","+n.getInput(1).name);
							} catch(NullPointerException e){
								Gate.setAttribute("inputs", "null,null");
							}
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("NAND")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "nand");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							Gate.setAttribute("inputs", n.getInput(0).name+","+n.getInput(1).name);
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("NOT")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "not");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							try{
								Gate.setAttribute("input", n.getInput(0).name);
							} catch(NullPointerException e){
								Gate.setAttribute("input", "null");
							}
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("NOR")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "nor");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							try{
								Gate.setAttribute("inputs", n.getInput(0).name+","+n.getInput(1).name);
							} catch(NullPointerException e){
								Gate.setAttribute("inputs", "null,null");
							}
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("OR")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "or");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0" );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							try{
								Gate.setAttribute("inputs", n.getInput(0).name+","+n.getInput(1).name);
							} catch(NullPointerException e){
								Gate.setAttribute("inputs", "null,null");
							}
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("XOR")){
							Gate = mainDoc.createElement("gate");
							Gate.setAttribute("type", "xor");
							Gate.setAttribute("name", n.name);
							Gate.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );//0,0 is just a blank value since we don't need them anymore. easier then taking them out.
							try{
								Gate.setAttribute("inputs", n.getInput(0).name+","+n.getInput(1).name);
							} catch(NullPointerException e){
								Gate.setAttribute("inputs", "null,null");
							}
							root.appendChild(Gate);
							Gate = null;
					}else if (n.getClass().getName().equals("Power")){
							Power = mainDoc.createElement("power");
							Power.setAttribute("name", n.name);
							Power.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );
							Power.setAttribute("state", stateParser(n));
							root.appendChild(Power);
					}else if (n.getClass().getName().equals("Output")){
							Output = mainDoc.createElement("output");
							Output.setAttribute("name", n.name);
							Output.setAttribute("coords", (int)n.getLocation().getX() + "," + (int)n.getLocation().getY() + ",0,0"  );
							try{
								Output.setAttribute("input", n.getInput(0).name);
							} catch(NullPointerException e){
								Output.setAttribute("input", "null");
							}
							root.appendChild(Output);
					}// end switch
				}// end loop
				
				//add actual file stuff. Right now it's just outputing to console.
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(mainDoc);
				StreamResult result = new StreamResult(fileSelect.getSelectedFile());
				
				//make it look nice.
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				
				transformer.transform(source, result);
				
			}
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public void load(){

		try{
			NamedNodeMap n;
			List<Integer> coordHolder = new LinkedList<Integer>();
			List<String> inputHolder = new LinkedList<String>();
			int code = fileSelect.showOpenDialog(null);
			if(code == JFileChooser.APPROVE_OPTION){
				
				GateOr.getNodeList().clear();
				GateOr.getSelectedList().clear();
				
				
				
				//TODO[Matt]Used if we have time, makes it so only xml files can be selected.  FileFilter.
				//fileSelect.setFileFilter(xmlFileFilter);
				
				
				Document xmlDoc = getDocument(fileSelect.getSelectedFile().toString());
				System.out.println("finished loading xml");
				
				xmlDoc.getDocumentElement();
				
				
				//lists holding all the <gate/>, <power/> and <output/> nodes.
				NodeList gate = xmlDoc.getDocumentElement().getElementsByTagName("gate");
				NodeList power = xmlDoc.getDocumentElement().getElementsByTagName("power");
				NodeList output = xmlDoc.getDocumentElement().getElementsByTagName("output");
				
				Node node = null;
				
				
				//parse gates.
				System.out.println("\nREADING GATES. . . ");
				
				for(int i = 0; i < gate.getLength(); i++){

					
					
					n = gate.item(i).getAttributes();
					if (n.getNamedItem("type").getTextContent().equals("and")){
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
						node = new AND(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
						
					} else if (n.getNamedItem("type").getTextContent().equals("or")){
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new OR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						node = new OR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
					} else if (n.getNamedItem("type").getTextContent().equals("not")){
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the input
						inputHolder.add(n.getNamedItem("input").getTextContent());
						
						//nodeList.add(new NOT(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						node = new NOT(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0)}//Not only has one input
							);
					} else if (n.getNamedItem("type").getTextContent().equals("nand")){
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new NAND(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						node = new NAND(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
					} else if (n.getNamedItem("type").getTextContent().equals("nor")){
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new NOR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						node = new NOR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
					} else if (n.getNamedItem("type").getTextContent().equals("xor")){
						//get the coordinates
						for(String s : n.getNamedItem("coords").getTextContent().split(",")){
							coordHolder.add(Integer.parseInt(s));
						}
						
						//get the inputs
						for(String s : n.getNamedItem("inputs").getTextContent().split(",")){
							inputHolder.add(s);
						}
						
						//nodeList.add(new XOR(n.getNamedItem("name").getTextContent()), coordHolder, inputHolder));
						node = new XOR(
							new Point(coordHolder.get(0), coordHolder.get(1)),
							n.getNamedItem("name").getTextContent());
						nodeList.add(node);
						nodeLookupTable.put(
							n.getNamedItem("name").getTextContent(), node);
						nodeInputLookup.put(
							n.getNamedItem("name").getTextContent(),
							new String[]{inputHolder.get(0), inputHolder.get(1)}
							);
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
					
					//find state
					Node.State state;
					switch(Integer.parseInt(n.getNamedItem("state").getTextContent())){
						case 1: state = Node.State.ON;break;
						case 0: state = Node.State.OFF;break;
						default: state = Node.State.UNDEF;
					}
					node = new Power(
						state,
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
					node = new Output(
						new Point(coordHolder.get(0), coordHolder.get(1)),
						n.getNamedItem("name").getTextContent());
					nodeList.add(node);
					nodeLookupTable.put(
						n.getNamedItem("name").getTextContent(), node);
					nodeInputLookup.put(
						n.getNamedItem("name").getTextContent(),
						new String[]{n.getNamedItem("input").getTextContent()}//output only has one input
						);
					coordHolder.clear();
				}
				for (Node nodes: nodeList){//Loop through all the nodes
					String[] inputs = nodeInputLookup.get(nodes.name);//Get the string array of inputs
					if (inputs != null){
						for (String inputName: inputs){//For all the inputs
							if (!nodes.setInput(//Check to see if too many inputs are given
								nodeLookupTable.get(inputName)//Get the node reference from the name
							)){
								System.out.println(nodes.name+" was not added correctly.");//Say that it too many inputs were given.
							}
						}
					}
				}
			}
			
		} catch(Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	public void export(){
		int code = fileSelect.showSaveDialog(GateOr.getJframe());
		
		Container container = GateOr.getJframe().getContentPane();
		
		System.out.println(container.getWidth() + " : " + container.getHeight());
		BufferedImage outImage = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		container.paint(outImage.getGraphics());
		
		if (code == JFileChooser.APPROVE_OPTION){
			try {
				if (fileSelect.getSelectedFile().getName().endsWith(".png")){
					ImageIO.write(outImage, "PNG", fileSelect.getSelectedFile());
				}else{
					ImageIO.write(outImage, "PNG", new File(fileSelect.getSelectedFile().getAbsolutePath()+".png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
