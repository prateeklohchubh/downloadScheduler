
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Episode{
	private static final Logger logging = Logger.getGlobal();
	int episodeID;
	int episodeNumber;
	int season;
	Date episodeAirDate;
	
	public Episode(int episodeID,int episodeNumber,int season,Date episodeAirDate)
	{
		Object[] o = {episodeID,episodeNumber,season,episodeAirDate};
		logging.entering(Episode.class.getName(), "Episode",o);
		this.episodeID=episodeID;
		this.episodeAirDate=episodeAirDate;
		this.episodeNumber=episodeNumber;
		this.season=season;
	}
	
	public Episode()
	{
		logging.log(Level.FINEST,"Episode class created");
	}
	
	public int getEpisodeID()
	{
		logging.exiting(Episode.class.getName(), "getEpisodeID",episodeID);
		return episodeID;
	}
	
	public int getEpisodeNumber()
	{
		logging.exiting(Episode.class.getName(), "getEpisodeNumber",episodeNumber);
		return episodeNumber;
	}
	
	public Date getepisodeAirDate()
	{
		logging.exiting(Episode.class.getName(), "getEpisodeAirDate",episodeAirDate);
		return episodeAirDate;
	}
	
	public int getSeason()
	{
		logging.exiting(Episode.class.getName(), "getSeason",season);
		return season;		
	}
	

	public void setEpisodeID(int episodeID)
	{
		logging.log(Level.FINER, "set epID", episodeID);
		this.episodeID=episodeID;
	}
	
	public void setEpisodeNumber(int episodeNumber)
	{
		logging.log(Level.FINER, "set epNo", episodeNumber);
		this.episodeNumber=episodeNumber;
	}
	
	public void setEpisodeAirDate(Date episodeAirDate)
	{
		logging.log(Level.FINER, "set epAirDate", episodeAirDate);
		this.episodeAirDate=episodeAirDate;
	}
	
	public void setSeason(int season)
	{
		logging.log(Level.FINER, "set season", season);
		this.season=season;
	}
}