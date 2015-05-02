/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;

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
public class EpisodeParserTest {
    
    public EpisodeParserTest() {
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
     * Test of getType method, of class EpisodeParser.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        EpisodeParser instance = new EpisodeParser(ParserType.EPISODE);
        ParserType expResult = ParserType.EPISODE;
        ParserType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
        return;
    }

    /**
     * Test of getValueFromXML method, of class EpisodeParser.
     */
    @Test(expected=java.lang.NullPointerException.class)
    public void testGetValueFromXML() {
        System.out.println("getValueFromXML");
        File XML = new File("afdsdf","asdf");
        String searchItem = "";
        EpisodeParser instance = null;
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = null;
        		instance.getValueFromXML(searchItem);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetValueFromXML2() {
        System.out.println("getValueFromXML");
        String searchItem = ".//episodes_Supernatural.xml";
        XMLParser newparser = new XMLParser();
		newparser.setParserasEpisode();
		ArrayList<Object> objectList=newparser.getAttributeValue(searchItem);
        assertEquals(2,((Episode)objectList.get(0)).getEpisodeNumber());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
