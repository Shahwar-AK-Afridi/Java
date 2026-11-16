
/**
 * Write a description of WordsInFiles here.
 * Write a program to determine which words occur in the greatest number of files, and for each word, which files they occur in. 
 * @author (Shahwar Afridi) 
 * @version (16/11/26)
 */
import java.io.File;
import java.util.*;
import edu.duke.*;

public class Main{

    public static void main(String args[]){
    
            WordsInFiles obj1 = new WordsInFiles();
            obj1.tester();
        
    }
}

class WordsInFiles{

    private HashMap<String, ArrayList<String>> wordsContainer;
    
    public WordsInFiles(){
    
          wordsContainer = new HashMap<String,ArrayList<String>>();
          
    
    }
    
    private void addWordsFromFile(File f){
        
        FileResource fr = new FileResource(f);
        
        for(String word: fr.words()){
            
            String lowerCaseWord = word.toLowerCase();
            if(wordsContainer.containsKey(lowerCaseWord)){
            
                ArrayList<String> files = wordsContainer.get(lowerCaseWord);
                
                if(files.contains(f.getName())){
                    
                    continue;
                
                }else{
                
                    files.add(f.getName());
                }
                
            }else{
                
                ArrayList<String> newFiles = new ArrayList<String>();
                newFiles.add(f.getName());
                wordsContainer.put(lowerCaseWord,newFiles);
            
            }
        }
    }
    
    public void buildWordFileMap(){
    
        wordsContainer.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()){
        
           addWordsFromFile(f);
           
        }
    }
    
    public int maxNumber(){
        
        int maxNumber = 0;
        for(String word: wordsContainer.keySet()){
        
            ArrayList<String> files = wordsContainer.get(word);
            int noOfFiles = files.size();
            
            if(maxNumber < noOfFiles){
            
                maxNumber = noOfFiles;
            }
                     
        }
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
    
        ArrayList<String> words = new ArrayList<String>();
        for(String word: wordsContainer.keySet()){
            
            ArrayList<String> listOfFiles = wordsContainer.get(word);
            if(number == listOfFiles.size()){
            
                words.add(word);
                
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        
        if(word == null || word.equals("")){
        
            System.out.println("No word found");
            return;
        }
        
        ArrayList<String> noOfFiles = wordsContainer.get(word.toLowerCase());
        
        if(noOfFiles == null){
        
            System.out.println("Word not found in HashMap");
            return;
        }
        
        for(String file: noOfFiles){
        
        System.out.println(file);
        
        }
    }
    
    public void tester(){
    
        buildWordFileMap();
        
        int maximumFiles = this.maxNumber();
        
        System.out.println("Maximum Number of Files: " + maximumFiles);
        
        ArrayList<String> listOfWords = wordsInNumFiles(maximumFiles);
        
        for(String word: listOfWords){
    
            System.out.println("Word: " + word);
    
            printFilesIn(word);
    
            System.out.println();
        }   
        
        for(String word: wordsContainer.keySet()){
        
        System.out.println("Word: "+word+" | "+"Files:"+ wordsContainer.get(word));
        
        }
    }
}
