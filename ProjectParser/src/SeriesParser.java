import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SeriesParser implements ParseType{
	
	ParserType parser;
	ArrayList<String> series = new ArrayList<String>();
	
	public SeriesParser(ParserType series2) {
		// TODO Auto-generated constructor stub
		this.parser = series2;
	}

	@Override
	public ParserType getType() {
		// TODO Auto-generated method stub
		return parser;
	}

	@Override
	public ArrayList<String> getValueFromXML(File XML, String searchItem) {
		// TODO Auto-generated method stub
		try 
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sparser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
				boolean bfname = false;
				boolean blname = false;
				//boolean bnname = false;
				//boolean bsalary = false;
				
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{
					//System.out.println("Start Element: " + qname);
					if(qname.equalsIgnoreCase("id"))
					{
						bfname = true;
					}
					if(qname.equalsIgnoreCase("SeriesName"))
					{
						blname = true;
						series.add(qname);
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
					//System.out.println("Printing Series Information:");
					if(bfname)
					{
						System.out.println("ID: " + new String(ch, start, length));
						bfname = false;
					}
					if(blname)
					{
						System.out.println("Series Name: " + new String(ch, start, length));
						blname = false;
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
		return series;
	}

}
