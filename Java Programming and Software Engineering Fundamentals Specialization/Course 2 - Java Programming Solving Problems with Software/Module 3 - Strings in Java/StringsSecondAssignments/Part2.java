
/**
 * Write a description of Part2 here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Part 2: HowMany - Finding Multiple Occurrences)
 */
//Point 1
public class Part2 {
    
    //Point 2
    public int howMany(String stringa, String stringb){
    
        int count  = 0;
        int marker = 0;
        while(true){
            
            int find = stringb.indexOf(stringa,marker);
            if(find == -1){
                   
                break;
                
            }  
            marker = find + stringa.length();
            count += 1;
            
            }
        return count;
    }
    
    //Point 3
    public void testHowMany(){
    
    String test1 = "ATGAACGAATTGAATC";
    System.out.println("Total Number of stringa: "+howMany("GAA",test1));
    
    String test2 = "ATAAAA";
    System.out.println("Total Number of stringa: "+howMany("AA",test2));
    
    String test3 = "ATAAAAAAAAAA";
    System.out.println("Total Number of stringa: "+howMany("AA",test3));
    
    String test4 = "AAAAAAAA";
    System.out.println("Total Number of stringa: "+howMany("A", test4));
    
    }

}
