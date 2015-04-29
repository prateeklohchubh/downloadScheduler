package repository;

import java.util.Date;


public class UserSeriesList{
	
	Series seriesInfo;
	Episode lastEpisodeDownloaded;
	Date nextEpisodeRelease;
	
	public UserSeriesList(Series seriesInfo,Episode lastEpisodeDownloaded,Date nextEpisodeRelease)
	{
		this.seriesInfo=seriesInfo;
		this.lastEpisodeDownloaded=lastEpisodeDownloaded;
		this.nextEpisodeRelease=nextEpisodeRelease;
	}
	
	public UserSeriesList()
	{
		
	}
	
	public Series getSeriesInfo()
	{
		return seriesInfo;
	}
	
	public Episode getLastEpisodeDownloaded()
	{
		return lastEpisodeDownloaded;
	}
	
	public Date getNextEpisodeRelease()
	{
		return nextEpisodeRelease;
	}
	
	public void setSeriesInfo(Series seriesInfo)
	{
		this.seriesInfo=seriesInfo;
	}
	
	public void setLastEpisodeDownloaded(Episode lastEpisodeDownloaded)
	{
		this.lastEpisodeDownloaded=lastEpisodeDownloaded;
	}
	
	public void setNextEpisodeRelease(Date nextEpisodeRelease)
	{
		this.nextEpisodeRelease=nextEpisodeRelease;
	}

}
