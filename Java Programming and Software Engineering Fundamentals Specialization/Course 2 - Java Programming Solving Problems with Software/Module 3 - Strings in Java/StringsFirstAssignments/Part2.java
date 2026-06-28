
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
        public String findSimpleGene(String dna, String startCodon, String stopCodon){
        //Start codeon = ATG
        //Stop codon = TAA
        String result = "";
        String start;
        String end;
        
        if(dna.equals(dna.toUpperCase())){
             
            start = startCodon.toUpperCase();
            end = stopCodon.toUpperCase();
        }
        else if(dna.equals(dna.toLowerCase())){
            
            start = startCodon.toLowerCase();
            end = stopCodon.toLowerCase();
        }
        else{                                                 //for mixed case
            start = startCodon;
            end = stopCodon;
        
        }
        int startIndex = dna.indexOf(start);
        if(startIndex == -1){
        
            return "";
        }
        int stopIndex = dna.indexOf(end,startIndex + 3);
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
        
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);   
        
        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
        
        dna = "ATGTAA";
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
        
        dna = "TTATAA";  //String with no "ATG"
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
        
        dna = "CGATGGTTTG";  //String with no "TAA"
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
               
        dna = "CGATGGGGTTTUUUKKKTAALOL"; 
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
        
        dna = "gatgctataat"; 
        System.out.println("DNA strand is "+dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is "+ gene);
    }

}
