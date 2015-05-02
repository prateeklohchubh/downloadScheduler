/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class TorrentClientTest {
    
    public TorrentClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of startDownload method, of class TorrentClient.
     */
    @Test
    public void testStartDownload() {
        System.out.println("startDownload");
        Spider spider=new Spider();
        Status linkStatus=spider.search("gotham",1, 1);
		String downloadLink = spider.getMagnetLink();
		TorrentClient torrentClient=new TorrentClient();
		torrentClient.startDownload(downloadLink);
		assertEquals(Status.LINK_FOUND, linkStatus);
   
        
        return;
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of startDownloadSilent method, of class TorrentClient.
     */
    @Test
    public void testStartDownloadSilent() {
        System.out.println("startDownloadSilent");
        String magnetLink = "movie";
        TorrentClient instance = new TorrentClient();
        
        instance.startDownloadSilent("magnet:?xt=urn:btih:B72BDD7769107B14BCD9AC1FA0CF618BA625D63F&dn=fast+and+furious+7+2015+hdcam+readnfo+x264+cpg&tr=udp%3A%2F%2Fopen.demonii.com%3A1337%2Fannounce");
        assertEquals(magnetLink, "movie"  );        
        return;
        //fail("The test case is a prototype.");
    }

    
}
