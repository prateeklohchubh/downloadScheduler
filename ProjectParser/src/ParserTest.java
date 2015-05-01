import java.util.ArrayList;


public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> parserResult = new ArrayList<String>();
		XMLParser newparser = new XMLParser();
		newparser.setParserasSeries();
		//newparser.setParserasEpisode();
		parserResult = newparser.getAttributeValue("C:\\B.Tech\\6th Semester\\PP\\Project\\apiresult.xml");
		System.out.println("Parser Result : ");
		System.out.println(parserResult);
	}

}
