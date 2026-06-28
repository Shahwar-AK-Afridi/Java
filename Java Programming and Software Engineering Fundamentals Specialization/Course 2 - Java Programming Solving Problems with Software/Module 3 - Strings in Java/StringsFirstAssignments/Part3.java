
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public boolean twoOccurrences(String a, String b){
        
        int count = 0;
        int startIndex = 0;
        
        while(true){
            
            int index = b.indexOf(a,startIndex);
            
            if(index == -1){
                   
                break;
            }
            
            count = count + 1;
            if(count >= 2){
        
                return true;
            }
            
            startIndex = index + a.length();
        
        }
        
            return false;
    }
    
    public String lastPart(String stringa, String stringb){
    
        int index = stringb.indexOf(stringa);
        if(index == -1){
        
        
            return stringb;
        }
        else{
        
        //int lengthStringa = stringa.length();
        String answer = stringb.substring(index+stringa.length());
        
            return answer;
        }
        
    
    }
    
    public void test(){
    
    String big = "abababa";
    String small = "aba";
    boolean output = twoOccurrences(small,big);
    System.out.println(output);
    
    big = "A story by Abby Long";
    small = "by";
    output = twoOccurrences(small,big);
    System.out.println(output);

    big = "banana";
    small = "a";
    output = twoOccurrences(small,big);
    System.out.println(output);
    
    big = "ctgtatgta";
    small = "atg";
    output = twoOccurrences(small,big);
    System.out.println(output);
    
    big = "banana";
    small = "an";
    String output2 = lastPart(small,big);
    System.out.println(output2);
    
    big = "bananaananana";
    small = "an";
    output2 = lastPart(small,big);
    System.out.println(output2);
    
    big = "forest";
    small = "zoo";
    output2 = lastPart(small,big);
    System.out.println(output2);
    
    }
}
