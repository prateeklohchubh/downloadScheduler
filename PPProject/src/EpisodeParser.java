
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EpisodeParser implements ParseType{

	ParserType parser;
	ArrayList<Object> episode = new ArrayList<>();
	Episode episodeElement=new Episode();
	
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
	public ArrayList<Object> getValueFromXML(String searchItem) {
		// TODO Auto-generated method stub
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
				

				
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{
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
				
				public void endElement(String uri, String localName,String qname) throws SAXException
				{
					
					if ((qname.equalsIgnoreCase("Episode"))&&(episodeElement.getepisodeAirDate() != null)){
						episode.add(episodeElement);
						/*System.out.println(episodeElement.getSeason());
						System.out.println(episodeElement.getepisodeAirDate());
						//System.out.println(episodeElement.getEpisodeNumber());
						System.out.println("______________________________________");
						*/
					}
				}
				
				public void characters(char ch[], int start, int length) throws SAXException
				{

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
			e.printStackTrace();
		}
		System.out.println("episode list " + episode);
		return episode;
	}

}