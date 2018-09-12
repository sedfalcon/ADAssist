package techtool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class test {
	
	static ArrayList<String> lineArray1 = new ArrayList<>();
	static ArrayList<String> lineArray2 = new ArrayList<>();
	public static InputStream getURLSource(String url) throws IOException
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return urlConnection.getInputStream();
    }

    private static void toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)
            {
            	inputLine = inputLine.replaceAll("\\<[^>]*>","");
            	inputLine = inputLine.replaceAll("(?m)^\\s+$", "");
            	{
            	
                lineArray1.add(inputLine.trim());
            	
            }
            }
            
            String bastardCharacter = lineArray1.get(0);
            
            for(String line : lineArray1) {
            	if(!line.equals(bastardCharacter)) {
            		lineArray2.add(line);
            	}
            }
            
           // for(String line : lineArray2) {
            //	System.out.println(line);
          //  }
            
            for(String line : lineArray2.subList(lineArray2.indexOf("Matchup"), lineArray2.indexOf("Most recent fights (Newest First)"))){
            	System.out.println(line);
            }
            	
            
          
        }
       
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			
	try {
		
		 toString(getURLSource("http://fightmetric.com/fight-details/cd117bfce747184f"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				

	}

}
