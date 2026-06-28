
/**
 * Write a description of Part1 here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Part1, Part2, Part3 :Storing All Genes)
 */

import edu.duke.StorageResource;
import edu.duke.FileResource;

public class Part1 {
    
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
    
    
    public String findGene(String dna,int where){
     
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
    
    public void testFindStopCodon(){
    
    String test = "ATGATCGCTGATTAGGCTTAAATGACG";
    System.out.println(findStopCodon(test,0,"TAA"));  
    // Expected: 12
    
    System.out.println(findStopCodon(test,0,"TAG"));  
    // Expected: 18
    
    System.out.println(findStopCodon(test,0,"TGA")); 
    // Expected: This will return the length of DNA, as there is no valid gene with "TGA" 
}
    
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
    
    
    public void printAllGenes(String dna){
        
        int startIndex = 0;
    
        while(true){
        
        String currentGene = findGene(dna,startIndex);
        
        if(currentGene.isEmpty()){                             //We can also check wheather length == 0 or not, and not use isEmpty()
        
            break;
        }

        System.out.println(currentGene);
        
        startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        
        }
    
    }
    
    //Point 1
    public StorageResource getAllGenes(String dna){
    
    int startIndex = 0;
    StorageResource sr = new StorageResource();
    
    while(true){
    
    String currentGene = findGene(dna,startIndex);
    
    if(currentGene.isEmpty()){
    
        break;
    } 
    
    sr.add(currentGene);
    
    startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
    
    }
    return sr;
    
    }
    
    public void testGetAllGenes(){
    
    String test1 = "ATGATCGCTGATTAGGCTTAAATGACGAAAAATGATCGCTGATTAGGCTTAAATGACGAAAAATGATCGCATGATTBGGCTTAAATGACG";
    getAllGenes(test1);
    
    }
    
    //Point 2
    public double cgRatio(String dna){
        
    int counter = 0;
    double ratio;
    
    for(char character: dna.toCharArray()){
    
        if(character == 'C' || character == 'G'){
         
            counter += 1;
    
        }
        
    }
    
    ratio = (double)counter/dna.length();
    
    return ratio;
    }
    
    public void testCGRatio(){
    
    String test1 = "AQWSCCAQWSCCAQWSGGAQWSGG";
    System.out.println(cgRatio(test1));
    
    }
    
    public int countCTG(String dna){
    
    int marker = 0;
    int counterCTG = 0;
    while(true){
        
        int index = dna.indexOf("CTG",marker);
        if(index == -1){
            break;
        }
        marker = index + 3;
        counterCTG += 1;
    
    }
    return counterCTG;
    }
    
    public void testCountCTG(){
    
    String test1 = "AASDFCTGQQWDDCTGASDECTGJ";
    System.out.println(countCTG(test1));
    
    
    }
    
    //Point 3
    public void processGenes(StorageResource sr){
    
    int geneLengthGt60 = 0;
    int geneCGRatioGT035 = 0;
    int maxLength = 0;
    int temp = 0;
    double cgRatioCounter = 0.0;
    
    for(String gene:sr.data()){
    
        if(gene.length() > 60){
        
        System.out.println(gene);
        geneLengthGt60 += 1;
        }
        
        cgRatioCounter = cgRatio(gene);
        
        if(cgRatioCounter > 0.35){
        
        //System.out.println(gene);
        geneCGRatioGT035 += 1;
        }
    
        temp = gene.length();
        
        if(temp > maxLength){
        
            maxLength = temp;
            
        }
    }
    System.out.println("Number Of Gene With Length Greater Then 60: " + geneLengthGt60);
    System.out.println("Gene With CG Ratio Greater Then 0.35: " + geneCGRatioGT035);
    System.out.println("Gene With Longest Length: "+ maxLength);
    }
    
    //Point 3
    public void testProcessGenes(){
    
    StorageResource test1 = new StorageResource(); 
    //test1.add("ATGATCG");
    //test1.add("ATGATCGCTGATTAGGCTTAAATGACG");
    //test1.add("AQWSCCAQWSCCAQWSGGAQWSGGASDFWERFCCASDFASGGASDQW");
    //test1.add("AQWSCCAQWSCCAQWSGGAQWSGGASDFWERFCCASDFASGGASDQWADASDASDASDSAD");
    
    FileResource fr = new FileResource("GRch38dnapart.fasta");
    String dna = fr.asString();
    
    System.out.println("DNA from file: "+dna);
    
    test1 = getAllGenes(dna);
    System.out.println("Total Genes Found: "+test1.size());
    
    processGenes(test1);
    
    }

}
