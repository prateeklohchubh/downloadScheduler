package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class TvDbAPIManager {

	static final String APIKey="";
	Series series;
	Episode episode;
	
	public void getAllAvailableSeries(String series)
	{
		
    	String uri ="http://thetvdb.com/api/GetSeries.php?seriesname="+series;    

		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(uri);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept", "application/xml");

		InputStream xml = null;
		try {
			xml = connection.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		OutputStream outputStream = null;
		
		try {	 
			outputStream = 
	                    new FileOutputStream(new File(".//series_"+series+".xml"));
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = xml.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (xml != null) {
				try {
					xml.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	}
	
	public void searchSeries(Series series)
	{
		String uri ="http://thetvdb.com/api/03DFA0589C42B05D/series/"+series.getSeriesID()+"/all/en.xml";    

		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(uri);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept", "application/xml");

		InputStream xml = null;
		try {
			xml = connection.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		OutputStream outputStream = null;
		
		try {	 
			outputStream = 
	                    new FileOutputStream(new File(".//episodes_"+series.getSeriesName()+".xml"));
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = xml.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (xml != null) {
				try {
					xml.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	}
	
	public static void main(String[] args) {
		TvDbAPIManager manager=new TvDbAPIManager();
		
		//manager.getAllAvailableSeries("supernatural");
		manager.searchSeries(new Series("supernatural",78902));
		
	}
	
}
