
/**
 * Write a description of WordLengths here.
 * You will write a program to figure out the most common word length of words from a file. To solve this problem you will need to keep track of how many words from a file are of each possible length. You should group all words of length 30 or more together, and you should not count basic punctuation that are the first or last characters of a group of characters.
 * @author (Shahwar Afridi) 
 * @version (10/10/2026)
 */

import edu.duke.*;

public class WordLengths {
        
    public void countWordLengths(FileResource resource, int counts[]){
        
        String wordWithoutPunctuation;
        for(String word: resource.words()){
            
            if(word.length() == 0){
            
                continue;
            }
            
            if(Character.isLetter(word.charAt(0)) && Character.isLetter(word.charAt(word.length()-1))){
                
                wordWithoutPunctuation = word;
            
            }else if(Character.isLetter(word.charAt(0))){
            
                wordWithoutPunctuation = word.substring(0,word.length()-1);
                
            
            }else if(Character.isLetter(word.charAt(word.length()-1))){
            
                wordWithoutPunctuation = word.substring(1);
            
            }else{
            
                wordWithoutPunctuation = word.substring(1,word.length()-1);  
            
            }
            
            if(wordWithoutPunctuation.length() >= counts.length){
            
                counts[counts.length - 1] += 1;
            
                }else{
                
                    counts[wordWithoutPunctuation.length()] += 1;
                    
                }
            
        }
    
    
    }
    
    public void testcountWordLengths(){
    
       FileResource fr = new FileResource();
       int[] counts = new int[31]; 
       countWordLengths(fr,counts);
        
       for(int i=0; i<counts.length; i++){
        
        System.out.println(counts[i]);
        
        }
        
       int maxValue = indexOfMax(counts);
       System.out.println("Max Value: "+maxValue);
    }
    
    public int indexOfMax(int values[]){
    
        int largestSoFar = 0;
        for(int i=0;i<values.length;i++){
            
            if(values[i] > largestSoFar){
            
                largestSoFar = values[i];
            }
        }
        return largestSoFar;
    }
    
}
