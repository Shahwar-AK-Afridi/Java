
/**
 * Write a description of WordFrequencies here.
 * You will write a program to determine the word that occurs the most often in a file. If more than one word occurs as the most often, then return the first such word found. You should make all words lowercase before counting them. Thus, “This” and “this” will both be counted as the lowercase version of “this”. You should not consider punctuation, so “end” and “end,” will be considered different words. Use the WordFrequencies program in the lesson as a starting point.
 * @author (Shahwar Afridi) 
 * @version (22/10/2026)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
    
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
        
    }
    
    public void findUnique(){
    
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word: fr.words()){
            
            String lowerCaseWord = word.toLowerCase();
            if(! myWords.contains(lowerCaseWord)){
                
                myWords.add(lowerCaseWord);
                myFreqs.add(1);
        
            }else{
            
                int index = myWords.indexOf(lowerCaseWord);
                int countSoFar = myFreqs.get(index);
                myFreqs.set(index,countSoFar+1);
            }
        }
    }
        
    public int findIndexOfMax(){
    
        int largestValueSoFar = 0;
        int indexOfLargestWord;
        for(int i =0; i<this.myFreqs.size(); i++){
        
            if(this.myFreqs.get(i) > largestValueSoFar){
            
                largestValueSoFar = this.myFreqs.get(i); 
            }
        }
        
        indexOfLargestWord = this.myFreqs.indexOf(largestValueSoFar);
        return indexOfLargestWord;
    }
    
    public void tester(){
    
        findUnique();
    
        System.out.println("Number of unique words: " + myWords.size());
    
        for(int i = 0; i < myWords.size(); i++){
    
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
    
        int index = findIndexOfMax();
    
        String mostFrequentWord = myWords.get(index);
        int frequencyOfWord = myFreqs.get(index);
    
        System.out.println("Most Frequent Word: " + mostFrequentWord);
        System.out.println("Frequency: " + frequencyOfWord);

    }
}

