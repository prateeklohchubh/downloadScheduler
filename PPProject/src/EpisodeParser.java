
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Concrete Class of Strategy Pattern

public class EpisodeParser implements ParseType{
	private static final Logger logging = Logger.getGlobal();
	
	//**GLOBAL VARIABLES**//
	
	ParserType parser;
	ArrayList<Object> episode = new ArrayList<>();
	Episode episodeElement=new Episode();
	
	public EpisodeParser(ParserType episode2)
	{
		logging.log(Level.FINER, "epParser", episode2);
		this.parser = episode2;
	}
	
	@Override
	public ParserType getType() {
		// TODO Auto-generated method stub		
		return parser;
	}

	//Parses XML file using filepath variable in @param searchItem and returns an arraylist
	//of Episode cast as Objects
	@Override
	public ArrayList<Object> getValueFromXML(String searchItem) {
		// TODO Auto-generated method stub
		logging.log(Level.FINER, "getValueFromXML", searchItem);
		File XML=new File(searchItem);

		try 
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sparser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
				boolean id = false;
				boolean episodeNumber = false;
				boolean episodeSeason = false;
				boolean firstAired = false;
				

				//Method to access starting element of XML file
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{	
					Object[] o = {uri,localName, qname, attributes};
					logging.entering(EpisodeParser.class.getName(), "startElement", o);
					if(qname.equalsIgnoreCase("Episode"))
					{
						episodeElement = new Episode();
					}
					if(qname.equalsIgnoreCase("id"))
					{
						id = true;
					}
					if(qname.equalsIgnoreCase("EpisodeNumber"))
					{
						episodeNumber = true;
					}
					if(qname.equalsIgnoreCase("SeasonNumber"))
					{
						episodeSeason = true;
					}
					if(qname.equalsIgnoreCase("FirstAired"))
					{
						firstAired = true;
					}

				}
				
				//Method to access last element of XML file
				public void endElement(String uri, String localName,String qname) throws SAXException
				{
					Object[] o = {uri,localName, qname};
					logging.entering(EpisodeParser.class.getName(), "endElement", o);
					if ((qname.equalsIgnoreCase("Episode"))&&(episodeElement.getepisodeAirDate() != null)){
						episode.add(episodeElement);
					}
				}
				
				//Method to parse through values of various tags in XML file
				public void characters(char ch[], int start, int length) throws SAXException
				{
					Object[] o = {ch,start, length};
					logging.entering(EpisodeParser.class.getName(), "characters", o);
					if(id)
					{
						id = false;
						((Episode)episodeElement).setEpisodeID(Integer.parseInt(new String(ch, start, length)));

					}
					if(episodeNumber)
					{
						episodeNumber = false;
						((Episode)episodeElement).setEpisodeNumber(Integer.parseInt(new String(ch, start, length)));

					}
					if(episodeSeason)
					{
						System.out.println("Season : " + new String(ch, start, length));
						episodeSeason = false;
						episodeElement.setSeason(Integer.parseInt(new String(ch, start, length)));

					}
					if(firstAired)
					{
						
						firstAired = false;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date date = null;
						try {
							date = formatter.parse(new String(ch, start, length));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							logging.throwing(EpisodeParser.class.getName(), "characters", e);
							e.printStackTrace();
						}
						
						(episodeElement).setEpisodeAirDate(date);
						
					}
				}
				
			};
			
			sparser.parse(XML, handler);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			logging.throwing(EpisodeParser.class.getName(), "characters outermost catch", e);
			e.printStackTrace();
		}
		logging.exiting(EpisodeParser.class.getName(), "characters", episode);
		return episode;
	}

}