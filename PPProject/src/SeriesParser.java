
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Concrete Class of Strategy Pattern

public class SeriesParser implements ParseType{
	private final static Logger logging = Logger.getGlobal();
	
	//**GLOBAL VARIABLES**//

	ParserType parser;
	ArrayList<Object> series = new ArrayList<>();
	Series seriesElement; 
	
	public SeriesParser(ParserType series2) {
		// TODO Auto-generated constructor stub
		this.parser = series2;
	}

	public ParserType getType() {
		// TODO Auto-generated method stub
		return parser;
	}

	//Parses XML file using filepath variable in @param searchItem and returns an arraylist
	//of Series cast as Objects
	public ArrayList<Object> getValueFromXML(String searchItem) {
		// TODO Auto-generated method stub
		File XML=new File(searchItem);
		try 
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sparser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
				boolean bfname = false;
				boolean blname = false;
				
				//Method to access starting element of XML file
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{
					
					if (qname.equalsIgnoreCase("series")){
						seriesElement=new Series();
					}
					if(qname.equalsIgnoreCase("id"))
					{
						bfname = true;
					}
					if(qname.equalsIgnoreCase("SeriesName"))
					{
						blname = true;
						
					}
				}
				
				//Method to access last element of XML file
				public void endElement(String uri, String localName,String qname) throws SAXException
				{
					if (qname.equalsIgnoreCase("series")){
						series.add(seriesElement);
					}
				}
				
				//Method to parse through values of various tags in XML file
				public void characters(char ch[], int start, int length) throws SAXException
				{
					
					if(bfname)
					{
						System.out.println("ID: " + new String(ch, start, length));
						bfname = false;
						((Series)seriesElement).setSeriesID(Integer.parseInt(new String(ch, start, length)));
					}
					if(blname)
					{
						System.out.println("Series Name: " + new String(ch, start, length));
						blname = false;
						((Series)seriesElement).setSeriesName(new String(ch, start, length));
						
					}
					
				}
				
			};
			
			sparser.parse(XML, handler);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "series parser", e);
			e.printStackTrace();
		}
		return series;
	}

}