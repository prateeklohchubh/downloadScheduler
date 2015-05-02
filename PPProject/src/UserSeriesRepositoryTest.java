/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dell
 */

public class UserSeriesRepositoryTest {
    
    public UserSeriesRepositoryTest() {
    return;
    }
    
    @BeforeClass
    public static void setUpClass() {
    return;
    }
    
    @AfterClass
    public static void tearDownClass() {
    return;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findBySeriesID method, of class UserSeriesRepository.
     */
    @Test(expected=java.lang.NullPointerException.class)
    public void testFindBySeriesID() {
        System.out.println("findBySeriesID");
        Series series = null;
        UserSeriesRepository instance = new UserSeriesRepository();
        instance.findBySeriesID(series);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test(expected=java.lang.NullPointerException.class)
    public void testFindBySeriesID2() {
        System.out.println("findBySeriesID");
        Series series = new Series("Game of Thrones",121361);
        UserSeriesRepository instance = new UserSeriesRepository();
        instance=instance.findBySeriesID(series);
        assertEquals(5,instance.season);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of saveSeries method, of class UserSeriesRepository.
     */
    @Test
    public void testSaveSeries() {
        System.out.println("saveSeries");
        
        UserSeriesList newUserShow=new UserSeriesList(new Series("test",1),new Episode(),new Date(325232));

        UserSeriesRepository instance = new UserSeriesRepository();
        instance.saveSeries(newUserShow);
        instance=instance.findBySeriesID(new Series("test",1));
        assertEquals(1,instance.seriesID);
        return;
// TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSeries method, of class UserSeriesRepository.
     */
    @Test
    public void testDeleteSeries() {
        System.out.println("deleteSeries");
        Series series = (new Series("test",1));
        UserSeriesRepository instance = new UserSeriesRepository();
        instance.deleteSeries(series);
        assertEquals(null,instance.findBySeriesID(series));
        return;
// TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    
    @Test
    public void testdeleteAll() {
        System.out.println("updateSeries");
        UserSeriesList seriesList = null;
        UserSeriesRepository instance = new UserSeriesRepository();
        instance.deleteAllSeries();
        List<UserSeriesRepository> list=instance.findAllLatestEpisodes();
        assertEquals(0,list.size());
        return;
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
