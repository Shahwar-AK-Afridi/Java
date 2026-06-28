
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 */
public class Part1 {
    
    public String findSimpleGene(String dna){
        //Start codeon = ATG
        //Stop codon = TAA
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
        
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex + 3);
        if(stopIndex == -1){
            
            return "";
        }
        
        String subString = dna.substring(startIndex + 3 , stopIndex);
        if(subString.length() % 3 == 0)
        {
        
        result = dna.substring(startIndex,stopIndex + 3);
        return result;
        
        }
        
        return "";
        
        
        
    }
    public void testSimpleGene(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);   
        
        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
        
        dna = "ATGTAA";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
        
        dna = "TTATAA";  //String with no "ATG"
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
        
        dna = "CGATGGTTTG";  //String with no "TAA"
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
               
        dna = "CGATGGGGTTTUUUKKKTAALOL";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is "+ gene);
    }

}
