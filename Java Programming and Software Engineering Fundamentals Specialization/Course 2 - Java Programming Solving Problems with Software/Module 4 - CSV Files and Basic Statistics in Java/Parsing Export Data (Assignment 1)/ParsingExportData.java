
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Parsing Export Data)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    
    //Point 1
    public void tester(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("----------Printing Point 2----------");
        String result = countryInfo(parser,"Nauru");
        System.out.println(result);
        parser = fr.getCSVParser();
        
        System.out.println("----------Printing Point 3----------");
        listExportersTwoProducts(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        
        System.out.println("----------Printing Point 4----------");
        int noOfCountries = numberOfExporters(parser,"gold");
        System.out.println(noOfCountries);
        parser = fr.getCSVParser();
        
        System.out.println("----------Printing Point 5----------");
        bigExporters(parser,"$999,999,999,999");
    
}
    
    //Point 2
    public String countryInfo(CSVParser parser, String country){
    
        for(CSVRecord record: parser){
        
        String requiredCountry = record.get("Country");
        
        if(requiredCountry.contains(country)){
            
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            
            return country + ": " + exports + ": " + value;
        
        }
        //System.out.println(data);    
        }
        return "NOT FOUND";
    }
    
    //Point 3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
    
        for(CSVRecord record: parser){
        
            String export1 = record.get("Exports");
            
            if(export1.contains(exportItem1) && export1.contains(exportItem2)){
            
                String country = record.get("Country");
                System.out.println(country);
            
            }
        }
    }
    
    //Point 4
    public int numberOfExporters(CSVParser parser, String exportItem){
    
        int countCountryWithExport = 0;
        
        for(CSVRecord record: parser){
            
            String exports = record.get("Exports");    
            if(exports.contains(exportItem)){
            
                countCountryWithExport += 1;
            
            }
        }
    return countCountryWithExport;

    }
    
    //Point 5
    public void bigExporters(CSVParser parser, String amount){
    
    for(CSVRecord record: parser){
    
        String value = record.get("Value (dollars)");
        if(value.length() > amount.length()){
        
            String country = record.get("Country");
            System.out.println(country+" "+value);
        
        }
    
    }
    
    
    }

}
