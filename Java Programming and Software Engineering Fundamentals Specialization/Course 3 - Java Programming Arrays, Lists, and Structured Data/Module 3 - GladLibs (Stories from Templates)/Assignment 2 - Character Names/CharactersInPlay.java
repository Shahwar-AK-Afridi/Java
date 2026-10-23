/**
 * Write a description of CharactersInPlay here.
 * Write a program to determine the characters in one of Shakespeare’s plays that have the most speaking parts. Consider the play “The Tragedy of Macbeth” in the file macbeth.txt. Here are a few lines from the file put into a much smaller file called macbethSmall.txt:
 * @author (Shahwar Afridi) 
 * @version (23/10/2026)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    
    private ArrayList<String> nameOfCharacters;
    private ArrayList<Integer> countOfCharacters;
    
    public CharactersInPlay(){
        
        nameOfCharacters = new ArrayList<String>();
        countOfCharacters = new ArrayList<Integer>();
    }
    
    public void update(String person){
        
        if(! nameOfCharacters.contains(person)){
            
            this.nameOfCharacters.add(person);
            this.countOfCharacters.add(1);
        
        }else{
        
            int indexOfchar = this.nameOfCharacters.indexOf(person);
            int countOfChar = this.countOfCharacters.get(indexOfchar);
            this.countOfCharacters.set(indexOfchar,countOfChar + 1);
        }
    
    }
    
    public void findAllCharacters(){
    
        FileResource fr = new FileResource();
        this.nameOfCharacters.clear();
        this.countOfCharacters.clear();
        for(String line: fr.lines()){
            
            if(line.indexOf(".") != -1){
                
                String[] arrayOfWords = line.trim().split("\\.");
                //System.out.println(Arrays.toString(arrayOfWords));
                this.update(arrayOfWords[0].trim());
        }
         
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        
        for(int i=0; i <this.nameOfCharacters.size(); i++){
            
            if(this.countOfCharacters.get(i) >= num1 && this.countOfCharacters.get(i) <= num2){
                
                System.out.println(this.nameOfCharacters.get(i) +" "+this.countOfCharacters.get(i));
            }
        }
        
    }
    
    public void tester(){
    
        this.findAllCharacters();
        for(int i=0; i <this.nameOfCharacters.size(); i++){
            
            if(this.countOfCharacters.get(i) > 1){
                
                System.out.println(this.nameOfCharacters.get(i) +" "+this.countOfCharacters.get(i));
            }
        }
        this.charactersWithNumParts(10,50);
    }
    
  
}
