import java.io.File;
import java.util.ArrayList;


public class XMLParser {
	
	ParserType series = ParserType.SERIES;
	ParserType episode = ParserType.EPISODE;
	ParseType dataParseType;

	ArrayList<String> parserResult = new ArrayList<String>();

	public void setParserasSeries()
	{
		dataParseType = new SeriesParser(series);
	}
	
	public void setParserasEpisode()
	{
		dataParseType = new EpisodeParser(episode);
	}
	
	public ArrayList<String> getAttributeValue(String attributeName)
	{	
		if(dataParseType.getType().equals(series))
		{
			File XML = new File(attributeName);
			parserResult = dataParseType.getValueFromXML(XML);
			return parserResult;
		}
		else if(dataParseType.getType().equals(episode))
		{
			File XML = new File(attributeName);
			parserResult = dataParseType.getValueFromXML(XML);
			return parserResult;
		}
		else
		{
			System.out.println("Set Parser first!");
			return null;
		}
	}
	
}
