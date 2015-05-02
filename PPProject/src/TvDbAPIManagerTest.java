import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;



public class TvDbAPIManagerTest {

	@Test
	public void testGetAllAvailableSeries() {
		ArrayList<Series> seriesList=new ArrayList<Series>();
		TvDbAPIManager manager=new TvDbAPIManager();
        seriesList=manager.getAllAvailableSeries("supernatural");
        assertTrue(seriesList.get(0).getSeriesID()==78901);

	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetAllAvailableSeries2() {
		ArrayList<Series> seriesList=new ArrayList<Series>();
		TvDbAPIManager manager=new TvDbAPIManager();
        seriesList=manager.getAllAvailableSeries(null);

	}
	
	@Test()
	public void testGetAllAvailableSeries3() {
		ArrayList<Series> seriesList=new ArrayList<Series>();
		TvDbAPIManager manager=new TvDbAPIManager();
        seriesList=manager.getAllAvailableSeries("test!!!??@@");

	}

	@Test
	public void testSearchSeries() {
		ArrayList<Episode> epList=new ArrayList<Episode>();
		TvDbAPIManager manager=new TvDbAPIManager();
        epList=manager.searchSeries(new Series("Supernatural",78901));
        assertTrue(epList.get(epList.size()-1).getSeason()==10);
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testSearchSeries2() {
		ArrayList<Episode> epList=new ArrayList<Episode>();
		TvDbAPIManager manager=new TvDbAPIManager();
        epList=manager.searchSeries(new Series());
        //assertTrue(epList.get(epList.size()-1).getSeason()==10);
	}
	
	@Test(expected=java.lang.NumberFormatException.class)
	public void testSearchSeries3() {
		ArrayList<Episode> epList=new ArrayList<Episode>();
		TvDbAPIManager manager=new TvDbAPIManager();
        epList=manager.searchSeries(new Series("test",Integer.parseInt("test")));
        //assertTrue(epList.get(epList.size()-1).getSeason()==10);
	}

}
