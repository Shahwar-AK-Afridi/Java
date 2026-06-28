
/**
 * Write a description of BabyNames here.
 * 
 * @author (Shahwar Afridi) 
 * @version (MiniProject)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames {
    
    public void totalBirths(FileResource fr){
    
        int totalBornSoFar = 0;
        int girlsBornSoFar = 0;
        int boysBornSoFar = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){ //As there is no header "false"
            
            int numBorn = Integer.parseInt(rec.get(2)); 
            totalBornSoFar += numBorn;
            
            if(rec.get(1).equals("F")){
            
                girlsBornSoFar += numBorn;
            
            }
            else{
            
                boysBornSoFar += numBorn;
            
            }
        
        }
        System.out.println("Total Births = "+ totalBornSoFar);
        System.out.println("Total Boys = "+ boysBornSoFar);
        System.out.println("Total Boys = "+ girlsBornSoFar);
    }
    
    public void testTotalBirths(){
    
        FileResource fr = new FileResource();
        totalBirths(fr);
    
    }
    
    public int getRank(int year, String name, String gender){
    
        int currentRank = 1;
    
        FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord currentRecord: parser){
        
            if(currentRecord.get(1).equals(gender)){
            
                if(currentRecord.get(0).equals(name)){
                
                    return currentRank;
                
                }
            currentRank ++;
            }
        }
        return -1;     //sentinel value ("No valid result yet" or "stop condition" or "not found.")
    }

    public void testGetRank(){
    
    int rank = getRank(2012,"Emma","F");
    System.out.println(rank);
    }
    
    public String getName(int year, int rank, String gender){
    
        String yearInInteger = String.valueOf(year);
        FileResource fr = new FileResource("us_babynames_small/testing/yob"+yearInInteger+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        int currentRank = 1;

        for(CSVRecord currentRecord: parser){
            
            if(currentRecord.get(1).equals(gender)){
            
                if(currentRank == rank){
                
                    return currentRecord.get(0);
                
                }    
                currentRank ++;
            
            }
    }
    return "NO NAME";
}
    
    public void testGetName(){
    
        String name = getName(2012,3,"F");
        System.out.println(name);
        
        name = getName(2012,2,"M");
        System.out.println(name);
        
        name = getName(2012,20,"F");
        System.out.println(name);  //NO NAME
    
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        
        int bornYearRank = getRank(year,name,gender);
        String nameOfNewYear = null;
        
        if(bornYearRank == -1){
        
            System.out.println("No Name Found");
            return;
        }
        
        nameOfNewYear = getName(newYear, bornYearRank, gender);
        
        if(gender.equals("M") && !nameOfNewYear.equals("NO NAME")){
           
            System.out.println(name+ " born in " + year +" would be "+ nameOfNewYear +" if he was born in "+ newYear);
        
        }
        else if(gender.equals("F") && !nameOfNewYear.equals("NO NAME")){
        
            System.out.println(name+ " born in " + year +" would be "+ nameOfNewYear +" if she was born in "+ newYear);
        
        }
        
    }
    
    public void testWhatIsNameInYear(){
    
        whatIsNameInYear("Olivia",2012,2014,"F");
        whatIsNameInYear("Ethan",2012,2014,"M");
        whatIsNameInYear("Noah",2012,2014,"M");
        whatIsNameInYear("abcd",2012,2014,"M");
    
    }
    
    public int yearOfHighestRank(String name, String gender){
    
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        int rank = 0;
        int currentYear = -1;                          //This is a common programming technique called a sentinel value.
                                                       //sentinel value = "No valid result yet" or "stop condition" or "not found."
        for(File f : dr.selectedFiles()){
        
            fileName = f.getName();
            int indexYear = fileName.indexOf("20");
            int year = Integer.parseInt(fileName.substring(indexYear,indexYear+4));
            int currentRank = getRank(year, name, gender);
            
            if(currentRank == -1){
            
                continue;
            
            }
            
            if(rank == 0){
                
                rank = currentRank;
                currentYear = year;
            }
            else if(currentRank < rank){
            
                rank = currentRank;
                currentYear = year;
            }    
        }
        return currentYear;
    }
    
    public void testYearOfHighestRank(){
    
        int year = yearOfHighestRank("Mason","M");
        System.out.println(year); //2012
        
        year = yearOfHighestRank("Emma","F");
        System.out.println(year);
    }
    
    public double getAverageRank(String name, String gender){
    
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        double count = 0;
        double totalRankSoFar = 0;
        double averageRank = -1;
        
        for(File f : dr.selectedFiles()){
        
            fileName = f.getName();    
            int indexYear = fileName.indexOf("20");
            int year = Integer.parseInt(fileName.substring(indexYear,indexYear+4));
            int rank = getRank(year, name, gender);
            
            if(rank == -1){
            
                continue;
            }
            else{
            
                totalRankSoFar += rank;
                count ++;
                
            } 
        }
        if(count != 0){
        
            averageRank = totalRankSoFar/count;
        }
        return averageRank;
    }
    
    public void testGetAverageRank(){
    
        System.out.println(getAverageRank("Mason","M")); //3.0
        System.out.printf("%.2f",getAverageRank("Jacob","M")); //2.66
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        
        FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        int birthsSoFar = 0;
        
        for(CSVRecord currentRecord: parser){
        
            if(currentRecord.get(1).equals(gender)){
                
                if(!currentRecord.get(0).equals(name)){
            
                birthsSoFar += Integer.parseInt(currentRecord.get(2));
                
                }
                else{
                
                    return birthsSoFar;
                
                }
            }
        }
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher(){
    
    System.out.println(getTotalBirthsRankedHigher(2012,"Ethan","M"));
    
    
    }
}
    