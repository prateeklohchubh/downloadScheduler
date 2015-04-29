package tutorial;
 
import javax.persistence.*;

import repository.Episode;
import repository.Series;
import repository.UserSeriesList;
import repository.UserSeriesRepository;

import java.util.*;
 
public class Main {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/pp_try.odb");
        EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
       em.getTransaction().begin();
        /*for (int i = 0; i < 100; i++) {
            Point p = new Point(i*100, i*100);
            em.persist(p);
        }
        em.getTransaction().commit();
 
        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
       // System.out.println("Total Points: " + q1.getSingleResult());
 
        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        //System.out.println("Average X: " + q2.getSingleResult());*/
 
        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p WHERE p.x=100", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }
 
        Query q1 = em.createQuery("DELETE FROM Point p WHERE p.x=100");
        q1.executeUpdate();
        em.getTransaction().commit();

        // Close the database connection:    
        em.close();
        emf.close();
        
        Episode ep=new Episode(1, 2, 2, new Date(123042));
        Series s=new Series("c'mon!", 1241);
        UserSeriesList userSeries = new UserSeriesList(s,ep,new Date(System.currentTimeMillis()));
        
        UserSeriesRepository rep=new UserSeriesRepository();
        //rep.saveSeries(userSeries);
        s.setSeriesName("hello");
        //s.setSeriesID(213);
        UserSeriesList userSeries2 = new UserSeriesList(s,ep,new Date((System.currentTimeMillis()+10000L)));
        //rep.saveSeries(userSeries2);

        
        rep.findBySeriesID(s);
        rep.deleteSeries(s);
    }
}