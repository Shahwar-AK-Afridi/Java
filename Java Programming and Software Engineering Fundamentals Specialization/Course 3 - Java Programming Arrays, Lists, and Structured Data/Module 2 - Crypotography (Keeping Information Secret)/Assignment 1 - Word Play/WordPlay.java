
/**
 * Write a description of WordPlay here.
 * You will write a program to transform words from a file into another form, such as replacing vowels with an asterix. 
 * @author (Shahwar Afridi) 
 * @version (08/10/2026)
 */
public class WordPlay {
    
    public boolean isVowel(char ch){
        
        char lowerChar = Character.toLowerCase(ch);   
        if(lowerChar == 'a'){
        
            return true;
        }else if(lowerChar == 'e'){
        
            return true;
        }else if(lowerChar == 'i'){
        
            return true;
        
        }else if(lowerChar == 'o'){
            
            return true;
        
        }else if(lowerChar == 'u'){
        
            return true;
        }else{
        
            return false;
        }
        
    }

    public String replaceVowels(String phrase, char ch){
    
    StringBuilder newString = new StringBuilder(phrase);
    for(int i = 0; i < newString.length(); i++){
           
        char eachChar = newString.charAt(i);
        if(isVowel(eachChar)){
        
            newString.setCharAt(i,ch);
        }
        
    }
     return newString.toString();
    }
    
    public String emphasize(String phrase, char ch){
    
        StringBuilder newString = new StringBuilder(phrase);
        for(int i = 0; i < newString.length(); i++){
            
            char pickChar = newString.charAt(i);
            if(Character.toLowerCase(pickChar) == Character.toLowerCase(ch)){
        
                if(i % 2 == 0){
    
                    newString.setCharAt(i,'*');
                
                }else{
                 
                    newString.setCharAt(i,'+');
                }
    
        }
    }
        return newString.toString();
    }
    
    public void testemphasize(){
    
    String answer = emphasize("dna ctgaaactga", 'a');
    System.out.println(answer);
    answer = emphasize("Mary Bella Abracadabra", 'a');
    System.out.println(answer);
    
    }
    public void testisVowel(){
    
    boolean answer = isVowel('F');
    System.out.println(answer);
    answer = isVowel('G');
    System.out.println(answer);
    answer = isVowel('I');
    System.out.println(answer);
    answer = isVowel('O');
    System.out.println(answer);
    answer = isVowel('u');
    System.out.println(answer);
    answer = isVowel('A');
    System.out.println(answer);
    }
    
    public void testreplaceVowels(){
    
    String answer = replaceVowels("hello world",'*');
    System.out.println(answer);
    answer = replaceVowels("",'*');
    System.out.println(answer);
    }
}
