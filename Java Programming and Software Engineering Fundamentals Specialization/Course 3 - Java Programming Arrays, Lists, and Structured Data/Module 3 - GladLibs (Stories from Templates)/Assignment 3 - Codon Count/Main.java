
/**
 * Write a description of CountCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.FileResource;

public class Main{

     public static void main(String args[]){
            
         CountCodon obj1 = new CountCodon();
         CountCodon obj2 = new CountCodon();
         CountCodon obj3 = new CountCodon();
         
         obj1.buildCodonMap(0,"CGTTCAAGTTCAA");
         System.out.println("Most Common Codon: " + obj1.getMostCommonCodon());
         obj2.buildCodonMap(1,"CGTTCAAGTTCAA");
         System.out.println("Most Common Codon: " + obj2.getMostCommonCodon());
         obj3.buildCodonMap(2,"CGTTCAAGTTCAA");
         System.out.println("Most Common Codon: " + obj3.getMostCommonCodon());
         
         obj1.printCodonCounts(2,5);
         obj2.printCodonCounts(2,5);
         obj3.printCodonCounts(2,5);
         
         CountCodon obj4 = new CountCodon();
         System.out.println("Check here");
         obj4.tester();
    }   
}

class CountCodon {

    private HashMap<String,Integer> codonCount;
    
    public CountCodon(){
    
        codonCount = new HashMap<String,Integer>();
        
    }
    
    public void buildCodonMap(int start, String dna){
        
        String dnaStrand  = dna.substring(start); 
        String codon;
        this.codonCount.clear();
        
        for(int i = 0; i + 3 <= dnaStrand.length(); i += 3){
               
                codon = dnaStrand.substring(i, i + 3);
                
                if(codonCount.containsKey(codon)){
                    
                    this.codonCount.put(codon, this.codonCount.get(codon)+1);
                
                }else{
                
                    this.codonCount.put(codon,1);
                
                }
               
        }
        System.out.println(this.codonCount);
    }
    
    public String getMostCommonCodon(){
        
        String largestCodon = "";
        int largestCodonCount = 0;
        for(String codon: this.codonCount.keySet()){
        
            if(this.codonCount.get(codon) > largestCodonCount){
                
                largestCodonCount = this.codonCount.get(codon);
                largestCodon = codon;
            }
        
        }
        return largestCodon;
    }
    
    public void printCodonCounts(int start, int end){
    
        for(String codon: this.codonCount.keySet()){
        
            int value = this.codonCount.get(codon);
            if(value >= start && value <= end){
            
                System.out.println("Codon: "+ codon +"|"+ "Count: "+ value);
            
            }
        }
        
        
    
    }
    
    public void tester(){

        FileResource fr = new FileResource();
    
        String dna = fr.asString()
                       .replaceAll("\\s+", "")   //Remove all whitspaces, tabs and newlines.
                       .toUpperCase();
    
        for(int start = 0; start < 3; start++){
    
            this.buildCodonMap(start, dna);
    
            System.out.println(
                "Reading frame starting with " + start +
                " results in " + this.codonCount.size() +
                " unique codons"
            );
    
            String commonCodon = this.getMostCommonCodon();
    
            if(!commonCodon.isEmpty()){
    
                System.out.println(
                    "Most common codon is " + commonCodon +
                    " with count " + this.codonCount.get(commonCodon)
                );
            }
    
            System.out.println(
                "Counts of codons between 1 and 5 inclusive:"
            );
    
            this.printCodonCounts(1, 5);
    
            System.out.println();
        }
    }   
}


