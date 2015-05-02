/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.jsoup.nodes.Document;
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
public class CrawlerTest {
    
    public CrawlerTest() {
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
     * Test of getmagnetLink method, of class Crawler.
     */
    @Test(expected=java.lang.NullPointerException.class)
    public void testgetmagnetLink() {
        System.out.println("getmagnetLink");
        Crawler instance = new Crawler();
        String expResult = instance.getmagnetLink();
        String result = null;
        assertEquals(expResult, result);
        return;
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of crawl method, of class Crawler.
     */
    @Test
    public void testCrawl() {
    	System.out.println("search");
        String seriesName = "friends";
        int seriesSeason = 4;
        int seriesEpisode = 3;
        Spider instance = new Spider();

        Status status=instance.search(seriesName, seriesSeason, seriesEpisode);
        assertEquals(Status.LINK_NOT_FOUND, status);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


   


    
}
