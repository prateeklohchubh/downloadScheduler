import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EpisodeParser implements ParseType{

	ParserType parser;
	ArrayList<String> episode = new ArrayList<String>();
	
	
	public EpisodeParser(ParserType episode2)
	{
		this.parser = episode2;
	}
	
	@Override
	public ParserType getType() {
		// TODO Auto-generated method stub
		return parser;
	}

	@Override
	public ArrayList<String> getValueFromXML(File XML) {
		// TODO Auto-generated method stub
		try 
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sparser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
				boolean id = false;
				boolean episodeName = false;
				//boolean bnname = false;
				//boolean bsalary = false;
				
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{
					//System.out.println("Start Element: " + qname);
					if(qname.equalsIgnoreCase("id"))
					{
						id = true;
					}
					if(qname.equalsIgnoreCase("EpisodeName"))
					{
						episodeName = true;
					}
					/*if(qname.equalsIgnoreCase("EpisodeName"))
					{
						bnname = true;
					}
					if(qname.equalsIgnoreCase("Rating"))
					{
						bsalary = true;
					}*/
				}
				
				public void endElement(String uri, String localName,String qname) throws SAXException
				{
					//System.out.println("End Element: " + qname);
				}
				
				public void characters(char ch[], int start, int length) throws SAXException
				{
					//System.out.println("Printing Episode Information:");
					if(id)
					{
						System.out.println("ID: " + new String(ch, start, length));
						id = false;
					}
					if(episodeName)
					{
						System.out.println("Episode Name: " + new String(ch, start, length));
						episodeName = false;
						episode.add(new String(ch, start, length));
					}
					/*if(bnname)
					{
						System.out.println("Episode Name: " + new String(ch, start, length));
						bnname = false;
					}
					if(bsalary)
					{
						System.out.println("Rating: " + new String(ch, start, length));
						bsalary = false;
					}*/
				}
				
			};
			
			sparser.parse(XML, handler);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return episode;
	}

}
