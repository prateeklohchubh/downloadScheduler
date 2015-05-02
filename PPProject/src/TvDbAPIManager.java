
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TvDbAPIManager {
	private static final Logger logging = Logger.getGlobal();
	static final String APIKey="";
	Series series;
	Episode episode;
	
	public ArrayList<Series> getAllAvailableSeries(String series)
	{
		
		String splitted[]=series.split("\\s+");
		String queryString="";
		
		for(int i=0;i<(splitted.length-1);i++)
		{
			queryString=queryString+splitted[i]+"+";
		}
		queryString=queryString+splitted[splitted.length-1];
    	String uri ="http://thetvdb.com/api/GetSeries.php?seriesname="+queryString;    
		try {
				series.length();
		} catch (NullPointerException e3) {
				// TODO Auto-generated catch block
				logging.log(Level.SEVERE, "getAllAvailableSeries", e3);
				e3.printStackTrace();
		}
		URL url;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
		} 
		catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "getAllAvailableSeries", e2);
			e2.printStackTrace();
		}
		catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "getAllAvailableSeries", e1);
			e1.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "getAllAvailableSeries", e);
			e.printStackTrace();
		}
		
		connection.setRequestProperty("Accept", "application/xml");

		InputStream xml = null;
		try {
			xml = connection.getInputStream();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "getAllAvailableSeries", e1);
			e1.printStackTrace();
		}
		
		OutputStream outputStream = null;
		String xmlFilepath=".//series_"+series+".xml";
		
		try {	 
			outputStream =  new FileOutputStream(new File(xmlFilepath));
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = xml.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} 
		catch (IOException e) {
			logging.log(Level.SEVERE, "getAllAvailableSeries", e);
			e.printStackTrace();
		} 
		finally {
			if (xml != null) {
				try {
					xml.close();
				} 
				catch (IOException e) {
					logging.log(Level.SEVERE, "getAllAvailableSeries", e);
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} 
				catch (IOException e) {
					logging.log(Level.SEVERE, "getAllAvailableSeries", e);
					e.printStackTrace();
				}
	 
			}
		}
		
		XMLParser newparser = new XMLParser();
		newparser.setParserasSeries();
		ArrayList<Object> objectList=newparser.getAttributeValue(xmlFilepath);
		ArrayList<Series>  seriesList=new ArrayList<Series>();
		for(Object obj:objectList)
		{
			seriesList.add((Series)obj);
		}
		for(int i=0;i<seriesList.size();i++)
		{
			System.out.println(((Series)seriesList.get(i)).getSeriesName());
			System.out.println(((Series)seriesList.get(i)).getSeriesID());
		}
		return seriesList;
	}
	
	public ArrayList<Episode> searchSeries(Series series)
	{
		String uri ="http://thetvdb.com/api/03DFA0589C42B05D/series/"+series.getSeriesID()+"/all/en.xml";    

		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
		} 
		catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "SearchSeries", e2);
			e2.printStackTrace();
		}
		catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "SearchSeries", e1);
			e1.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "SearchSeries", e);
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept", "application/xml");

		InputStream xml = null;
		try {
			xml = connection.getInputStream();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			logging.log(Level.SEVERE, "SearchSeries", e1);
			e1.printStackTrace();
		}
		
		OutputStream outputStream = null;
		String xmlFilepath=".//episodes_"+series.getSeriesName()+".xml";
		
		try {	 
			outputStream =  new FileOutputStream(new File(xmlFilepath));
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = xml.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} 
		catch (IOException e) {
			logging.log(Level.SEVERE, "SearchSeries", e);
			e.printStackTrace();
		} 
		finally {
			if (xml != null) {
				try {
					xml.close();
				} 
				catch (IOException e) {
					logging.log(Level.SEVERE, "SearchSeries", e);
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} 
				catch (IOException e) {
					logging.log(Level.SEVERE, "SearchSeries", e);
					e.printStackTrace();
				}
	 
			}
		}
		
		XMLParser newparser = new XMLParser();
		newparser.setParserasEpisode();
		ArrayList<Object> objectList=newparser.getAttributeValue(xmlFilepath);
		ArrayList<Episode> episodeList=new ArrayList<Episode>();
		for(int i=0;i<objectList.size();i++)
		{
			episodeList.add((Episode)objectList.get(i));
			//System.out.println(((Episode)episodeList.get(i)).getEpisodeID());
		}
		return episodeList;
		
	}
	
	
}