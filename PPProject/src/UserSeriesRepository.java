
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
	
	public void findBySeriesID(Series series)
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
	}
	
	public List<UserSeriesRepository> findAllLatestEpisodes()
	{
		//System.out.println("HERE I aMA FDSASDFASDFASDFASDFSAFDASFDASFDASFDASFD");
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	        //ArrayList<Date> releaseDates=new ArrayList<Date>();
	        //System.out.println("HERE");

	        TypedQuery<UserSeriesRepository> query = em.createQuery("SELECT c FROM UserSeriesRepository c", UserSeriesRepository.class);
	        List<UserSeriesRepository> results =null;
	        results=query.getResultList();
	        //System.out.println("HERE");

	        for(UserSeriesRepository userRep: results)
	        {
		        System.out.println(userRep.nextEpisodeRelease+" FOUND!");
		        //releaseDates.add(userRep.nextEpisodeRelease);
	        }
	        
	 
	        // Close the database connection:
	        em.close();
	        emf.close();
	        
	        return results;
	}

	public void saveSeries(UserSeriesList series)
	{
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

		            System.out.println(series.getSeriesInfo().getSeriesName());
		            em.persist(rep);
		        
		        em.getTransaction().commit();
		 
		        // Close the database connection:
		        em.close();
		        emf.close();
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
	
	public void deleteAllSeries()
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	  
	        em.getTransaction().begin();

	        TypedQuery<UserSeriesRepository> query = em.createQuery("SELECT c FROM UserSeriesRepository c", UserSeriesRepository.class);
	        List<UserSeriesRepository> results =null;
	        results=query.getResultList();
	        //System.out.println("HERE");

	        for(UserSeriesRepository userRep: results)
	        {
	        	em.remove(userRep);
	        }
	        	        
    		em.getTransaction().commit();

	        // Close the database connection:
	        em.close();
	        emf.close();
	}
	
	public void updateSeries(UserSeriesList seriesList)
	{
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("$objectdb/db/userrepository.odb");
	        EntityManager em = emf.createEntityManager();
	  
	        em.getTransaction().begin();

	        int seriesID=seriesList.getSeriesInfo().getSeriesID();
	        UserSeriesRepository record=em.find(UserSeriesRepository.class, seriesID);
	        em.remove(record);
	        
	        UserSeriesRepository rep=new UserSeriesRepository();
            rep.episodeAirDate=seriesList.getLastEpisodeDownloaded().getepisodeAirDate();
            rep.episodeID=seriesList.getLastEpisodeDownloaded().getEpisodeID();
            rep.episodeNumber=seriesList.getLastEpisodeDownloaded().getEpisodeNumber();
            rep.season=seriesList.getLastEpisodeDownloaded().getSeason();
            rep.seriesName=seriesList.getSeriesInfo().getSeriesName();
            rep.seriesID=seriesList.getSeriesInfo().getSeriesID();
            rep.nextEpisodeRelease=seriesList.getNextEpisodeRelease();
            
	        em.persist(rep);
	        
	        System.out.println("UPDATED! \r\n");
	        
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