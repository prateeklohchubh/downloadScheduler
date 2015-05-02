import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SeriesParserTest {

	@Test
	public void testSeriesParser() {
	}

	@Test
	public void testGetType() {
	}

	@Test
	public void testGetValueFromXML() {
		XMLParser newparser = new XMLParser();
		newparser.setParserasSeries();
		ArrayList<Object> objectList=newparser.getAttributeValue(".//series_supernatural.xml");
		assertEquals(78901,((Series)objectList.get(0)).getSeriesID());
	}

}
