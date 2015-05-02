import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

//Singleton class and Entry point of project 

public class DownloadManager {
	
	//**STATIC GLOBAL VARIABLES**//
	private static final Logger logging = Logger.getGlobal(); 
	
	//Singleton instance
	private static DownloadManager manager=new DownloadManager();
	
	//**GLOBAL VARIABLES**//
	boolean runInBackground;
	static GUI mainGUI;
	boolean showNotificationPopup;
	UserSeriesRepository userSeriesRepository;
	TorrentClient torrentClient;
	Spider getMagnetLink;
	GUI gui;
	
	//Private constructor of Singleton class
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
	
	//Creates one object of singleton and returns it
	public static DownloadManager getInstance()
	{
		logging.entering(DownloadManager.class.getName(), "getInstance");
		return manager;
	}
	
	
	//Initialises global variables
	public void initialiseDownloadManager()
	{
		logging.entering(DownloadManager.class.getName(), "initaliseDownloadManager");
		userSeriesRepository = new UserSeriesRepository();
		runInBackground = false;
		torrentClient = new TorrentClient();
		getMagnetLink = new Spider();
		logging.exiting(DownloadManager.class.getName(), "initaliseDownloadManager");
	}
	
	//Runs download scheduler in background without UI
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
	
	//Runs download scheduler in foreground with UI
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

	//Scans User show repositories for updates available
	public ArrayList<String> ScanUserRepositoriesForUpdates()
	{
		logging.entering(DownloadManager.class.getName(), "ScanUserRepositoriesForUpdates");
		Status status = Status.INPROGRESS;
		List<UserSeriesRepository> allSeriesList= new ArrayList<UserSeriesRepository>();
		allSeriesList=userSeriesRepository.findAllLatestEpisodes();
		Long currentDateInMillis=System.currentTimeMillis();
		Date currentDate=new Date(currentDateInMillis);
		ArrayList<String> result = new ArrayList<String>();

		for(UserSeriesRepository userSeries: allSeriesList)
		{
			if(currentDate.after(userSeries.nextEpisodeRelease))
			{

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
		logging.exiting(DownloadManager.class.getName(), "ScanUserRepositoriesForUpdates");
		return result;		
	}
	
	
	//Main method - entry point of DownloadScheduler
	public static void main(String args[]){
		DownloadManager downloadManager = DownloadManager.getInstance();
		logging.log(Level.INFO, "Started Main");

		if (args.length>1)
		{	
			downloadManager.setAsBackground();
		}
		else
		{
			downloadManager.setAsForeground();
		}
		logging.log(Level.INFO, "Exited Main");
	}	
}
