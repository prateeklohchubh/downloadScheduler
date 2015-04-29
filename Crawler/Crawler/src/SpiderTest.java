public class SpiderTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        //Crawler try1 = new Crawler();
        //try1.crawl("http://www.mit.edu/");
        Spider try1 = new Spider();
        System.out.println(try1.search("vampire diaries",6, 11));
    }
}