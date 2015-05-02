import javax.xml.parsers.*;

import java.io.*;

import org.xml.sax.helpers.*;
import org.xml.sax.*;
public class apiParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			
			File xmlfile = new File("C:\\B.Tech\\6th Semester\\PP\\Project\\apiresult.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sparser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler()
			{
				boolean bfname = false;
				boolean blname = false;
				boolean bnname = false;
				boolean bsalary = false;
				
				public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException
				{
					//System.out.println("Start Element: " + qname);
					if(qname.equalsIgnoreCase("id"))
					{
						bfname = true;
					}
					if(qname.equalsIgnoreCase("SeriesName"))
					{
						blname = true;
					}
					if(qname.equalsIgnoreCase("EpisodeName"))
					{
						bnname = true;
					}
					if(qname.equalsIgnoreCase("Rating"))
					{
						bsalary = true;
					}
				}
				
				public void endElement(String uri, String localName,String qname) throws SAXException
				{
					//System.out.println("End Element: " + qname);
				}
				
				public void characters(char ch[], int start, int length) throws SAXException
				{
					if(bfname)
					{
						System.out.println("ID: " + new String(ch, start, length));
						bfname = false;
					}
					if(blname)
					{
						System.out.println("Series Name: " + new String(ch, start, length));
						blname = false;
					}
					if(bnname)
					{
						System.out.println("Episode Name: " + new String(ch, start, length));
						bnname = false;
					}
					if(bsalary)
					{
						System.out.println("Rating: " + new String(ch, start, length));
						bsalary = false;
					}
				}
				
			};
			
			sparser.parse(xmlfile, handler);
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}