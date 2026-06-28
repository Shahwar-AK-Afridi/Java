
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    
    public void youtubeFinder(){
    
       URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
       int youtubeIndex;

       for(String s : ur.words()){
           
           String stringLower = s.toLowerCase(); // Always normalize string to lower case for case in-sensitive check
           
        youtubeIndex = stringLower.indexOf("youtube.com");
        
        if(youtubeIndex != -1){
        
            int start = s.indexOf("http");
            int end = s.indexOf("\"",start);
            
            if(start != -1 && end != -1)
            {
                String link = s.substring(start,end);
                System.out.println(link);
            }
        }
                
       }
        
    }

}
