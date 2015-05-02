import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.logging.Logger;

public class Crawler
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private String foundURL = null;
    private String magnetLink = null;
    private Status linkFound = Status.LINK_NOT_FOUND;
    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     * 
     * @param url
     *            - The URL to visit
     * @return whether or not the crawl was successful
     */
    public Status crawl(String url, String searchWord)
    {
    	
        try
        {
        	linkFound = Status.LINK_NOT_FOUND;
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
            {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                
                return linkFound;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage)
            {
            	
            	String linkData = link.text().toLowerCase();
            	if (linkData.contains(searchWord.toLowerCase())){
            		
                	try
                    {
                        Connection foundURL = Jsoup.connect(link.absUrl("href")).userAgent(USER_AGENT);
                        Document foundURLhtmlDoc = foundURL.get();
                        this.fetchMagnetLink(link.absUrl("href"), foundURLhtmlDoc);
                        this.foundURL = (link.absUrl("href"));
                        linkFound = Status.LINK_FOUND;
                        return linkFound;
                    }
                	catch (IllegalArgumentException illegalURL){
                    	System.out.println("Found URL is invalid or site may be unavailable");
                    	
                    }
                	catch(IOException ioe)
                    {
                		
                        return linkFound;
                    }
            	}
            	this.links.add(link.absUrl("href"));
            }
            return linkFound;
        }
        catch (IllegalArgumentException illegalURL){
        	System.out.println("Url is invalid or site may be unavailable");
        	return linkFound;
        }
        catch(IOException ioe)
        {
            return linkFound;
        }
    }

    public Status magnetLinkFound(){
    	return linkFound;
    }
	public String fetchMagnetLink(String url, Document foundURLhtmlDoc){
		
		System.out.println("something ------"+foundURLhtmlDoc.select("a[class*=magnetlinkButton]").attr("href"));
		this.magnetLink = foundURLhtmlDoc.select("a[class*=magnetlinkButton]").attr("href");
		return null;
	}
	
	public String getmagnetLink(){
		return this.magnetLink;
	}
	
    public String getfoundURL(){
    	return this.foundURL;
    }

}