
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    /*
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    */
   
    private ArrayList<String> wordsAlreadyUsed;
    private ArrayList<String> labelsUsed;
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>(); 
        myRandom = new Random();
        wordsAlreadyUsed = new ArrayList<String>();
        labelsUsed = new ArrayList<String>();
        
        initializeFromSource(dataSourceDirectory);
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        wordsAlreadyUsed = new ArrayList<String>();
        labelsUsed = new ArrayList<String>();
        
        initializeFromSource(source);
    }
    
    private void initializeFromSource(String source) {
        /*
        adjectiveList= readIt(source+"/adjective.txt");    
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");        
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        */
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
            
        for(String category: categories){
        
            ArrayList<String> words = readIt(source+"/"+category+".txt");
            myMap.put(category,words);
            
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        /*           
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        */
        
        if (label.equals("number")){
            
            return "" + (myRandom.nextInt(50) + 5);
        }
        else if(myMap.containsKey(label)){
            
            if(!labelsUsed.contains(label)){
        
                labelsUsed.add(label);
            }
            ArrayList<String> listOfWords = myMap.get(label);
            return randomFrom(listOfWords);
            
        }else{
        
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
       
        while (true){
            
            String sub = getSubstitute(w.substring(first+1,last));
            if(wordsAlreadyUsed.contains(sub)){
                
                continue;
            
            }else{
                
                wordsAlreadyUsed.add(sub);
                return prefix+sub+suffix;
            }
        }
            
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
    
        int totalWordsSoFar = 0;
        for(ArrayList<String> listOfWords: myMap.values()){
        
             totalWordsSoFar += listOfWords.size();   
            
        }
        return totalWordsSoFar;
    }
    
    public int totalWordsConsidered(){
        
        int totalWordsConsideredSoFar = 0;
        
        for(String words: labelsUsed){
        
            ArrayList<String> listOfWordsUsed = myMap.get(words);
            totalWordsConsideredSoFar += listOfWordsUsed.size();
        }
        
        
        return totalWordsConsideredSoFar;
    }
    public void makeStory(){
        
        labelsUsed.clear();
        wordsAlreadyUsed.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total words replaced: " + wordsAlreadyUsed.size());
        System.out.println("Total words available: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
    }
    


}

