import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DownloadManager {
			
	private static DownloadManager manager=new DownloadManager();
	boolean runInBackground;
	static GUI mainGUI;
	boolean showNotificationPopup;
	UserSeriesRepository userSeriesRepository;
	TorrentClient torrentClient;
	Spider getMagnetLink;
	GUI gui;
	private DownloadManager()
	{	
	}
	
	public static DownloadManager getInstance()
	{
		return manager;
	}
	
	public void initialiseDownloadManager()
	{
		userSeriesRepository = new UserSeriesRepository();
		runInBackground = false;
		torrentClient = new TorrentClient();
		getMagnetLink = new Spider();
	}
	
	public void setAsBackground(){
        
		ArrayList<String> result = new ArrayList<String>(); 
        result = ScanUserRepositoriesForUpdates();
		for(String userSeries: result)
			{
				torrentClient.startDownloadSilent(userSeries);
			}
}
	
	public void setAsForeground(){
		this.initialiseDownloadManager();

        ArrayList<String> result = new ArrayList<String>(); 
        result = ScanUserRepositoriesForUpdates();
        {
		for(String magneticLink: result)
			{
				torrentClient.startDownload(magneticLink);
			}
        }
		mainGUI = new GUI();
    }

	
	public ArrayList<String> ScanUserRepositoriesForUpdates()
	{
		Status status = Status.INPROGRESS;
		List<UserSeriesRepository> allSeriesList= new ArrayList<UserSeriesRepository>();
		allSeriesList=userSeriesRepository.findAllLatestEpisodes();
		Long currentDateInMillis=System.currentTimeMillis();
		Date currentDate=new Date(currentDateInMillis);
		ArrayList<String> result = new ArrayList<String>();
		//if(allSeriesList!=null)
		{
		for(UserSeriesRepository userSeries: allSeriesList)
		{
			if(currentDate.after(userSeries.nextEpisodeRelease))
			{
				//System.out.println("LINK"+getMagnetLink.search("Breaking Bad", 1,1));
				//result.add(getMagnetLink.search("Supernatural", 1,1));
				Status linkStatus = getMagnetLink.search(userSeries.seriesName, userSeries.season, userSeries.episodeID);
				if (linkStatus == Status.LINK_FOUND){
					String magnetLinkFound = getMagnetLink.getMagnetLink();
					result.add(magnetLinkFound);
				}
				
				else{
					System.out.println(userSeries.seriesName +" season"+ userSeries.season+ " episode"+ userSeries.episodeNumber+"not found");
				}
			}
		}
		}
		//else
			//return null;
		//System.out.println(result.get(0));
		
		return result;
		
		
	}
	
	public static void main(String args[]){
		DownloadManager downloadManager = DownloadManager.getInstance();
		UserSeriesRepository rep=new UserSeriesRepository();
		Series s = new Series("Supernatural",78901);
		Date d=new Date(2141431);
		UserSeriesList list=new UserSeriesList(s,new Episode(12, 1, 1, d),d);
		//rep.saveSeries(list);
		//rep.deleteAllSeries();
		if (args.length>1)
		{	
	//		downloadManager.setAsBackground();
		}
		else
		{
			downloadManager.setAsForeground();
		}
	}
	
}
