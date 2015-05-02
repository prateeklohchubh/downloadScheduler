import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserPreferences {
	
	File userPreference;
	String downloadLocation;
	String torrentCommunity;
	String notificationTime;
	long updateInterval;
	
	
	public String getDownloadLocation()
	{
		String[] userPreferences = null;

		try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
		{
 
			String userData;
 
			while ((userData = br.readLine()) != null) {
				System.out.println(userData);
				userPreferences=userData.split("\\s+");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return userPreferences[0];
	}
	
	public String getTorrentCommunity()
	{
		String[] userPreferences = null;

		try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
		{
 
			String userData;
 
			while ((userData = br.readLine()) != null) {
				System.out.println(userData);
				userPreferences=userData.split("\\s+");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return userPreferences[1];
	}
	
	public String getNotificationTime()
	{
		String[] userPreferences = null;

		try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
		{
 
			String userData;
 
			while ((userData = br.readLine()) != null) {
				System.out.println(userData);
				userPreferences=userData.split("\\s+");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return userPreferences[2];
	}
	
	public String getUpdateInterval()
	{
		String[] userPreferences = null;

		try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
		{
 
			String userData;
 
			while ((userData = br.readLine()) != null) {
				System.out.println(userData);
				userPreferences=userData.split("\\s+");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return userPreferences[3];
	}
	
	public void setUserPreference()
	{
		userPreference = new File(".\\UserPreference.txt");

		// if file doesnt exists, then create it
		if (!userPreference.exists()) {
			try {
				userPreference.createNewFile();
			
			FileWriter fw = new FileWriter(userPreference.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("null null null null");
			bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	
	public void setDownloadLocation(String downloadLocation)
	{
		try {
			 
			userPreference = new File(".\\UserPreference.txt");
			setUserPreference();
			String[] userPreferences = null;

			try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
			{
				String userData;
				while ((userData = br.readLine()) != null) {
					System.out.println(userData);
					userPreferences=userData.split("\\s+");
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} 
			userPreferences[0]=downloadLocation;
 
			userPreference.createNewFile();
			FileWriter fw = new FileWriter(userPreference.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(userPreferences[0]+" "+userPreferences[1]+" "+userPreferences[2]+" "+userPreferences[3]);
			bw.close();
 
			System.out.println("Updated reference.");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTorrentCommunity(String torrentCommunity)
	{
		try {
			 
			userPreference = new File(".\\UserPreference.txt");
			setUserPreference();
			String[] userPreferences = null;

			try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
			{
				String userData;
				while ((userData = br.readLine()) != null) {
					System.out.println(userData);
					userPreferences=userData.split("\\s+");
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} 
			userPreferences[1]=torrentCommunity;
 
			userPreference.createNewFile();
			FileWriter fw = new FileWriter(userPreference.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(userPreferences[0]+" "+userPreferences[1]+" "+userPreferences[2]+" "+userPreferences[3]);
			bw.close();
 
			System.out.println("Updated preference.");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNotificationTime(String notificationTime)
	{
		try {
			 
			userPreference = new File(".\\UserPreference.txt");
			setUserPreference();
			String[] userPreferences = null;

			try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
			{
				String userData;
				while ((userData = br.readLine()) != null) {
					System.out.println(userData);
					userPreferences=userData.split("\\s+");
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} 
			userPreferences[2]=notificationTime;
 
			userPreference.createNewFile();
			FileWriter fw = new FileWriter(userPreference.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(userPreferences[0]+" "+userPreferences[1]+" "+userPreferences[2]+" "+userPreferences[3]);
			bw.close();
 
			System.out.println("Updated preference.");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setUpdateInterval(long updateInterval)
	{
		try {
			 
			userPreference = new File(".\\UserPreference.txt");
			setUserPreference();
			String[] userPreferences = null;

			try (BufferedReader br = new BufferedReader(new FileReader(".\\UserPreference.txt")))
			{
				String userData;
				while ((userData = br.readLine()) != null) {
					System.out.println(userData);
					userPreferences=userData.split("\\s+");
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} 
			userPreferences[3]=Long.toString(updateInterval);
 
			userPreference.createNewFile();
			FileWriter fw = new FileWriter(userPreference.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(userPreferences[0]+" "+userPreferences[1]+" "+userPreferences[2]+" "+userPreferences[3]);
			bw.close();
 
			System.out.println("Updated reference.");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}