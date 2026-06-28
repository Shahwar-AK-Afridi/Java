
/**
 * Write a description of Part3 here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Part 3: How Many Genes?)
 */
//Point 1
public class Part3 {
    
    //Point 3
    public int findStopCodon(String dna, int startIndex, String stopCodon){
    
    int currIndex = dna.indexOf(stopCodon, startIndex + 3);
    
    while(currIndex != -1){
    
        int diff = currIndex - startIndex;
        if(diff % 3 == 0){
        
            return currIndex;
        
        }
        else{
        
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        
    }
    return dna.length();
    }
    
    //Method overloading because of point 7 and point 5 
    public String findGene(String dna) {                 //Method Overloading for point 7
        return findGene(dna, 0); 
    
    }
    
    //Point 5
    // Your enhanced version for Part 7
    public String findGene(String dna, int where){
     
    int temp;
    int minIndex;
    int startIndex = dna.indexOf("ATG", where);
    if(startIndex == -1){
    
        return "";
    }
    int indexOfTAA = findStopCodon(dna,startIndex,"TAA");
    int indexOfTAG = findStopCodon(dna,startIndex,"TAG");
    int indexOfTGA = findStopCodon(dna,startIndex,"TGA");
    
    if(indexOfTAA < indexOfTAG){                    //int minIndex = Math.min(indexOfTAA, Math.min(indexOfTAG, indexOfTGA));
                                                    //Alternative approach
        temp = indexOfTAA;    
    }
    else{
    
        temp = indexOfTAG;
    }
    
    if(temp < indexOfTGA){
    
     minIndex = temp;
        
    }
    else{
    
     minIndex = indexOfTGA;
    }
    
    if(minIndex == dna.length()){
    
        return "";
    }
    
    String gene = dna.substring(startIndex,minIndex + 3);
    
    return gene;
    
}
    //Point 4
    public void testFindStopCodon(){
    
    String test = "ATGATCGCTGATTAGGCTTAAATGACG";
    System.out.println(findStopCodon(test,0,"TAA"));  
    // Expected: 12
    
    System.out.println(findStopCodon(test,0,"TAG"));  
    // Expected: 18
    
    System.out.println(findStopCodon(test,0,"TGA")); 
    // Expected: This will return the length of DNA, as there is no valid gene with "TGA" 
    
}
    //Point 6
    public void testFindGene(){
    
    String test = "ATGATCGCTGATTAGGCTTAAATGACG";
    System.out.println("Original DNA:"+test);
    System.out.println("Valid Gene: "+findGene(test));  
    //Expected: ATGATCGCTGATTAG
    
    String test2 = "ATGATCGCTGATTBGGCTTAAATGACG";
    System.out.println("Original DNA:"+test2);
    System.out.println("Valid Gene: "+findGene(test2));  
    //Expected: ATGATCGCTGATTBGGCTTAA
    
    String test3 = "ATGATCGCATGATTBGGCTTAAATGACG";
    System.out.println("Original DNA:"+test3);
    System.out.println("Valid Gene: "+findGene(test3));  
    //Expected: ATGATCGCATGA
    
    String test4 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    System.out.println("Original DNA:"+test4);
    System.out.println("Empty String: "+ findGene(test4));  
    //Expected: This will return empty string
    
    }
    
    //Point 7
    public void printAllGenes(String dna){
        int startIndex = 0;
    
        while(true){
        
        String currentGene = findGene(dna,startIndex);
        
        if(currentGene.isEmpty()){
        
            break;
        }

        System.out.println(currentGene);
        
        startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        
        }
    
    }
    
    public int countGenes(String dna){
    
        int startIndex = 0;
        int count = 0;
        
        while(true){
        
        String currentGene = findGene(dna,startIndex);
        
        if(currentGene.isEmpty()){
        
            break;
        }

        count += 1;
        
        startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        
        }
    
    return count;    
    }
    
    public void testCountGenes(){

    String test1 = "ATGTAAGATGCCCTAGT";
    System.out.println(countGenes(test1));
    // Expected: 2

    String test2 = "ATGTAA";
    System.out.println(countGenes(test2));
    // Expected: 1

    String test3 = "AAAAAAAAAAAA";
    System.out.println(countGenes(test3));
    // Expected: 0

    String test4 = "ATGAAATAAATGCCCTAGATGTTTTGA";
    System.out.println(countGenes(test4));
    // Expected: 3

    String test5 = "ATGAAAAAAA";
    System.out.println(countGenes(test5));
    // Expected: 0
}
    
    
}



