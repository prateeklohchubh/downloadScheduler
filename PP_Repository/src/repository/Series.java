package repository;


public class Series{

	int seriesID;
	String seriesName;
	
	
	public Series()
	{
		
	}
	
	public Series(String seriesName, int seriesID)
	{
		this.seriesID=seriesID;
		this.seriesName=seriesName;
	}
	
	public int getSeriesID()
	{
		return seriesID;
	}
	
	public String getSeriesName()
	{
		return seriesName;
	}
	
	public void setSeriesName(String seriesName)
	{
		this.seriesName=seriesName;
	}
	
	public void setSeriesID(int seriesID)
	{
		this.seriesID=seriesID;
	}
}
