
import java.util.Date;

public class Episode{

	int episodeID;
	int episodeNumber;
	int season;
	Date episodeAirDate;
	
	public Episode(int episodeID,int episodeNumber,int season,Date episodeAirDate)
	{
		this.episodeID=episodeID;
		this.episodeAirDate=episodeAirDate;
		this.episodeNumber=episodeNumber;
		this.season=season;
	}
	
	public Episode()
	{
		
	}
	
	public int getEpisodeID()
	{
		return episodeID;
	}
	
	public int getEpisodeNumber()
	{
		return episodeNumber;
	}
	
	public Date getepisodeAirDate()
	{
		return episodeAirDate;
	}
	
	public int getSeason()
	{
		return season;
	}
	

	public void setEpisodeID(int episodeID)
	{
		this.episodeID=episodeID;
	}
	
	public void setEpisodeNumber(int episodeNumber)
	{
		this.episodeNumber=episodeNumber;
	}
	
	public void setEpisodeAirDate(Date episodeAirDate)
	{
		this.episodeAirDate=episodeAirDate;
	}
	
	public void setSeason(int season)
	{
		this.season=season;
	}
}