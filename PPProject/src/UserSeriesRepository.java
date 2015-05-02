
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//Entity class stored in form of records in ObjectDB sql database
@Entity
public class UserSeriesRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 
    @Id @GeneratedValue
    int seriesID;
	String seriesName;
	int episodeID;
	int episodeNumber;
	int season;
	Date episodeAirDate;
	Date nextEpisodeRelease;
	
	public UserSeriesRepository()
	{
		
	}
	
	//Returns a record by comparing seriesID, if found
	public UserSeriesRepository findBySeriesID(Series series)
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	  
	        int seriesID=series.getSeriesID();

	        UserSeriesRepository rep=em.find(UserSeriesRepository.class, seriesID);
	        System.out.println(rep.seriesName+" FOUND!");
	 
	        // Close the database connection:
	        em.close();
	        emf.close();
	        return rep;
	}
	
	//Returns all records in an arraylist
	public List<UserSeriesRepository> findAllLatestEpisodes()
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();

	        TypedQuery<UserSeriesRepository> query = em.createQuery("SELECT c FROM UserSeriesRepository c", UserSeriesRepository.class);
	        List<UserSeriesRepository> results =null;
	        results=query.getResultList();

	        // Close the database connection:
	        em.close();
	        emf.close();
	        
	        return results;
	}

	//Adds records to database
	public Status saveSeries(UserSeriesList series)
	{
		Status status=Status.INPROGRESS;
		 EntityManagerFactory emf =
		            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
		        EntityManager em = emf.createEntityManager();
	            
	            UserSeriesRepository rep=new UserSeriesRepository();
	            rep.episodeAirDate=series.getLastEpisodeDownloaded().getepisodeAirDate();
	            rep.episodeID=series.getLastEpisodeDownloaded().getEpisodeID();
	            rep.episodeNumber=series.getLastEpisodeDownloaded().getEpisodeNumber();
	            rep.season=series.getLastEpisodeDownloaded().getSeason();
	            rep.seriesName=series.getSeriesInfo().getSeriesName();
	            rep.seriesID=series.getSeriesInfo().getSeriesID();
	            rep.nextEpisodeRelease=series.getNextEpisodeRelease();

		        em.getTransaction().begin();
		        UserSeriesRepository foundRepo=em.find(UserSeriesRepository.class, rep.seriesID);
		        
		        if(foundRepo==null)
		        {
		        	System.out.println("Series already in user repository");
		        	status=Status.DUPLICATE_DatabaseException;
		        	//LOGGING
		        }
		        else
		        {
		            em.persist(rep);
		            status=Status.SUCCESS;
		        }
		        
		        em.getTransaction().commit();
		 
		        // Close the database connection:
		        em.close();
		        emf.close();
		        return status;
	}
	
	public void deleteSeries(Series series)
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	  
	        em.getTransaction().begin();

	        int seriesID=series.getSeriesID();
	        UserSeriesRepository record=em.find(UserSeriesRepository.class, seriesID);
	        em.remove(record);
	        System.out.println("DELETED! \r\n"+series.seriesName);
	        
    		em.getTransaction().commit();

	        // Close the database connection:
	        em.close();
	        emf.close();
	}
	
	//Clears entire database of user shows information
	public void deleteAllSeries()
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	  
	        em.getTransaction().begin();

	        TypedQuery<UserSeriesRepository> query = em.createQuery("SELECT c FROM UserSeriesRepository c", UserSeriesRepository.class);
	        List<UserSeriesRepository> results =null;
	        results=query.getResultList();

	        for(UserSeriesRepository userRep: results)
	        {
	        	em.remove(userRep);
	        }
	        	        
    		em.getTransaction().commit();

	        // Close the database connection:
	        em.close();
	        emf.close();
	}
	

	
	public static void main(String args[])
	{	UserSeriesRepository u = new UserSeriesRepository();
		u.findAllLatestEpisodes();
		
	}
	
}