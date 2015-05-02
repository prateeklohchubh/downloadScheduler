import java.util.logging.Logger;


public class Series{
	
	/**MEMBER VARIABLES*/

	private static final Logger logging = Logger.getGlobal();
	int seriesID;
	String seriesName;
	
	
	public Series()
	{
		
	}
	
	
        public Series(String seriesName, int seriesID)
	{
        Object[] o = {seriesName, seriesID};
        logging.entering(Series.class.getName(), "Series",o);
		this.seriesID=seriesID;
		this.seriesName=seriesName;
	}

        
    /**GETTER AND SETTER METHODS**/
	
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