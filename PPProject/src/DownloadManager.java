import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;


public class DownloadManager {
			
	private static DownloadManager manager=new DownloadManager();
	private static final Logger logging = Logger.getGlobal(); 
	boolean runInBackground;
	static GUI mainGUI;
	boolean showNotificationPopup;
	UserSeriesRepository userSeriesRepository;
	TorrentClient torrentClient;
	Spider getMagnetLink;
	GUI gui;
	private DownloadManager()
	{	FileHandler fh;
		try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("./Log.txt");  
	        logging.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        // the following statement is used to log any messages  
	        logging.info("Download Scheduler Logging started");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		logging.log(Level.INFO,"Class 1 constructor called");		
	}
	
	public static DownloadManager getInstance()
	{
		logging.entering(DownloadManager.class.getName(), "getInstance");
		return manager;
	}
	
	public void initialiseDownloadManager()
	{
		logging.entering(DownloadManager.class.getName(), "initaliseDownloadManager");
		userSeriesRepository = new UserSeriesRepository();
		runInBackground = false;
		torrentClient = new TorrentClient();
		getMagnetLink = new Spider();
		logging.exiting(DownloadManager.class.getName(), "initaliseDownloadManager");
	}
	
	public void setAsBackground(){
		logging.entering(DownloadManager.class.getName(), "setAsBackground");
		ArrayList<String> result = new ArrayList<String>(); 
        result = ScanUserRepositoriesForUpdates();
		for(String userSeries: result)
			{
				torrentClient.startDownloadSilent(userSeries);
			}
		logging.exiting(DownloadManager.class.getName(), "setAsBackground");
}
	
	public void setAsForeground(){
		logging.entering(DownloadManager.class.getName(), "setAsForeground");
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
		logging.exiting(DownloadManager.class.getName(), "setAsForeground");
    }

	
	public ArrayList<String> ScanUserRepositoriesForUpdates()
	{
		logging.entering(DownloadManager.class.getName(), "ScanUserRepositoriesForUpdates");
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
		logging.exiting(DownloadManager.class.getName(), "ScanUserRepositoriesForUpdates");
		return result;		
	}
	
	public static void main(String args[]){
		DownloadManager downloadManager = DownloadManager.getInstance();
		logging.log(Level.INFO, "Started Main");
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
		logging.log(Level.INFO, "Exited Main");
	}	
}
