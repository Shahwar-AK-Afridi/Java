
/**
 * Write a description of CSVMin here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Parsing Weather Data)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class CSVMin {
    
    //Point 1
    public CSVRecord coldestHourInFile(CSVParser parser){
    
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRecord: parser){
        
            if(lowestSoFar == null){
        
                lowestSoFar = currentRecord;
            
            }         
            else{    
                    
                double lowestTemperature = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                
                if(currentTemp == -9999){
                
                    continue;
                
                }
            
                if(currentTemp < lowestTemperature){
            
                    lowestSoFar = currentRecord;
            
            }
        }
    }
    return lowestSoFar;
    }
    
    //Point 1
    public void testColdestHourInFile(){
    
        FileResource fr = new FileResource();
        CSVRecord lowestSoFar = coldestHourInFile(fr.getCSVParser());
        System.out.println("The Lowest Temperature is "+ lowestSoFar.get("TemperatureF") +" and the date is "+ lowestSoFar.get("DateUTC"));
    
    }
    
    //Point 2
    public File fileWithColdestTemperature(){
    
        File fileWithColdestTemp = null;
        CSVRecord lowestSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            
            //System.out.println(f.getAbsolutePath());
            FileResource fr = new FileResource(f);
            
            CSVParser parser = fr.getCSVParser();
             
            CSVRecord currentRecord = coldestHourInFile(parser);
            
            if(lowestSoFar == null){
            
                lowestSoFar = currentRecord;
                fileWithColdestTemp = f;
            
            }
            else{
            
                double lowestTemperature = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            
                if(currentTemp < lowestTemperature){
                
                    lowestSoFar = currentRecord;
                    fileWithColdestTemp = f;
                
                }
            
            }
                
        
            } 
    
    return fileWithColdestTemp;
}
    //Point 2
    public void testFileWithColdestTemperature(){
        
        File coldestFile = fileWithColdestTemperature();
        
        System.out.println("Coldest day was in file "+ coldestFile.getName());
        
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldestHour = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest temperature on that day was "+ coldestHour.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        
        for(CSVRecord rec: fr.getCSVParser()){
        
        
            System.out.println(rec.get("DateUTC")+": "+rec.get("TemperatureF"));
        
        
        }
        
    }
    
    //Point 3
    public CSVRecord lowestHumidityInFile(CSVParser parser){
    
        CSVRecord lowestSoFar = null;
        int lowestHumidity;
        int currentHumidity;
        
        for(CSVRecord currentRecord: parser){
            
            String humidity = currentRecord.get("Humidity");
            
            if(humidity.equals("N/A")){
            
                continue;
            }
        
            if(lowestSoFar == null){ 
            
                lowestSoFar = currentRecord;
            
            }
            else{
                                    
                lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                currentHumidity = Integer.parseInt(currentRecord.get("Humidity"));
                    
                if(currentHumidity < lowestHumidity){
                
                    lowestSoFar = currentRecord;
                
                }
            
            }
        
        
        }
    
        return lowestSoFar;
    }
    
    //Point 3
    public void testLowestHumidityInFile(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+ csv.get("Humidity") +" at " + csv.get("DateUTC"));
    
    
    }
    
     //Point 4
     public CSVRecord lowestHumidityInManyFiles(){
        
        CSVRecord lowestSoFar = null; 
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()){
        
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
            
            if(currentRecord == null){  //If file contains all "N/A" then we will get a "null" record
                
                continue;
            }
            
            if(lowestSoFar == null){
            
                lowestSoFar = currentRecord;
            
            }
            else{
            
                int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                int currentHumidity = Integer.parseInt(currentRecord.get("Humidity"));
                 
                if(currentHumidity < lowestHumidity){
                
                    lowestSoFar = currentRecord;
                
                }
            
            }
            
        
        }
        
        return lowestSoFar;
    }
    
     //Point 4
     public void testLowestHumidityInManyFiles(){
        
        CSVRecord lowestHumidityInAllFiles = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+ lowestHumidityInAllFiles.get("Humidity")+" at "+ lowestHumidityInAllFiles.get("DateUTC"));
        
        
    }
    
    //Point 5
    public double averageTemperatureInFile(CSVParser parser){
    
        double averageTemperature = 0.0;
        double tempSoFar = 0.0;
        int count = 0;
        CSVRecord averageTempSoFar = null;
         
        for(CSVRecord currentRecord : parser){
        
            tempSoFar += Double.parseDouble(currentRecord.get("TemperatureF"));
            count += 1;
        }
         
        averageTemperature = tempSoFar / count;
        return averageTemperature;
    }
    
    //Point 5
    public void testAverageTemperatureInFile(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+ averageTemperature);
    
    }
    
    //Point 6
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
    
        double tempSoFar = 0.0;
        double averageTempWithHighHumidity = 0.0;
        int count = 0;
        
        for(CSVRecord currentRecord : parser){
            
            String humidityCheck = currentRecord.get("Humidity");
            
            if(humidityCheck.equals("N/A")){
            
                continue;
            }
            int humidity = Integer.parseInt(currentRecord.get("Humidity"));
            
            if(humidity >= value){
            
               tempSoFar += Double.parseDouble(currentRecord.get("TemperatureF")); 
               count += 1;
            
            }
        
        }

        if(tempSoFar == 0.0){
        
            return 0.0;
            
        }else{
            
            averageTempWithHighHumidity = tempSoFar/count;
        }
        return averageTempWithHighHumidity;
    }
    
     //Point 6
    public void testAverageTemperatureWithHighHumidityInFile(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if(averageTempWithHighHumidity == 0.0){
        
        System.out.println("No temperatures with that humidity");
        
        }
        else{
         
            System.out.println("Average Temp when high Humidity is "+averageTempWithHighHumidity);
        }        
    
    }
}
