package ppProject;

import java.io.*;

public class TorrentClient {
	//private String saveDirectoryPath;
	public TorrentClient(){}
	public void startDownload(){
		String magnetLink = new String("magnet:?xt=urn:btih:B72BDD7769107B14BCD9AC1FA0CF618BA625D63F&dn=fast+and+furious+7+2015+hdcam+readnfo+x264+cpg&tr=udp%3A%2F%2Fopen.demonii.com%3A1337%2Fannounce");
		try {
			Process p = Runtime.getRuntime().exec("deluge "+magnetLink);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startDownloadSilent(){
		String magnetLink = new String("magnet:?xt=urn:btih:B72BDD7769107B14BCD9AC1FA0CF618BA625D63F&dn=fast+and+furious+7+2015+hdcam+readnfo+x264+cpg&tr=udp%3A%2F%2Fopen.demonii.com%3A1337%2Fannounce");
		String s;
		try {
            
	        // run the deluged.exe command
	        // using the Runtime exec method:
	            Process p = Runtime.getRuntime().exec("deluged");
	            p = Runtime.getRuntime().exec("deluge-console add "+magnetLink);
	             
	            BufferedReader stdInput = new BufferedReader(new
	                 InputStreamReader(p.getInputStream()));
	 
	            BufferedReader stdError = new BufferedReader(new
	                 InputStreamReader(p.getErrorStream()));
	 
	            // read the output from the command
	            System.out.println("Here is the standard output of the command:\n");
	            while ((s = stdInput.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            while ((s = stdError.readLine()) != null) {
	                System.out.println(s);
	            }
	             
	            System.exit(0);
	        }
	        catch (IOException e) {
	            System.out.println("exception happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
	}
	
}
