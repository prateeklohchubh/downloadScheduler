import java.io.File;
import java.util.ArrayList;


public class XMLParser {
	
	ParserType series = ParserType.SERIES;
	ParserType episode = ParserType.EPISODE;
	ParseType dataParseType;

	ArrayList<Object> parserResult = new ArrayList<Object>();

	public void setParserasSeries()
	{
		dataParseType = new SeriesParser(series);
	}
	
	public void setParserasEpisode()
	{
		dataParseType = new EpisodeParser(episode);
	}
	
	public ArrayList<Object> getAttributeValue(String attributeName)
	{	
		if(dataParseType.getType().equals(series))
		{
			parserResult=dataParseType.getValueFromXML(attributeName);
			return parserResult;
		}
		else if(dataParseType.getType().equals(episode))
		{
			parserResult=dataParseType.getValueFromXML(attributeName);
			System.out.println(((Episode)parserResult.get(0)).getEpisodeID());
			return parserResult;
		}
		else
		{
			System.out.println("Set Parser first!");
			return null;
		}
	}
	
}