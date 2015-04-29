import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider
{
  private static final int MAX_PAGES_TO_SEARCH = 100;
  private Set<String> pagesVisited = new HashSet<String>();
  String seriesURL = "https://kickass.to/usearch/";

  /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for
   */
  public String search(String seriesName, int seriesSeason, int seriesEpisode)
  {    

	      StringBuffer searchURL = new StringBuffer();
	      
	      searchURL.append("https://kickass.to/usearch/");
	      searchURL.append(seriesName.toLowerCase());
	      searchURL.append("/");
	      String url = searchURL.toString();
	      
	      StringBuffer seasonEpisode = new StringBuffer();
	      
	      seasonEpisode.append("s");
	      if (seriesSeason>9){
	    	  seasonEpisode.append(seriesSeason);
	      }
	      else{
	    	  seasonEpisode.append("0");
	    	  seasonEpisode.append(seriesSeason);
	      }
	      
	      seasonEpisode.append("e");
	      if (seriesSeason>9){
	    	  seasonEpisode.append(seriesSeason);
	      }
	      else{
	    	  seasonEpisode.append("0");
	    	  seasonEpisode.append(seriesSeason);
	      }
	      
	      String searchWord = seasonEpisode.toString();
          Crawler searchForMagnet = new Crawler();
          
          for (int i = 1; i <= MAX_PAGES_TO_SEARCH; i++){
        	  StringBuffer found = new StringBuffer();
        	  found.append(url);
        	  found.append(i);
        	  pagesVisited.add(found.toString());
        	  
        	  if (searchForMagnet.crawl(found.toString(), searchWord) == true){
        		  //System.out.println("going out");
        		  break;
        	  }
          }
          
          //System.out.println("Found link" + try1.getfoundURL());
          System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
          return searchForMagnet.getmagnetLink();
          
  }

}