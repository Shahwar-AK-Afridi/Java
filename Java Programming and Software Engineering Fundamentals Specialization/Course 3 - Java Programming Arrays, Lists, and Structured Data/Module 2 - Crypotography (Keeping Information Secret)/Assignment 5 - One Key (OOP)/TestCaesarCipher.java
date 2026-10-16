
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class TestCaesarCipher {
        
    public int[] countLetters(String word){
            
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] letterFrequency = new int[26];
            
            for(int i = 0; i< word.length(); i++){
                
                char letter = Character.toLowerCase(word.charAt(i));
                //System.out.println(letter);
                int indexOfLetter = alphabets.indexOf(letter);
                //System.out.println(indexOfLetter);
                if(indexOfLetter != -1 && Character.isLetter(letter)){
                
                      letterFrequency[indexOfLetter] += 1;  
                }
                
         }
        return letterFrequency;
        
    }
        
    public int maxIndex(int[] letterFrequency){
        
        int largestSoFar = 0;
        for(int i = 0; i < letterFrequency.length;i++){
            
         if(letterFrequency[i] > letterFrequency[largestSoFar]){
                    
              largestSoFar = i; 
                
             }
                
           }
        return largestSoFar;
    }
        
    public String breakCaesarCipher(String input){
    
        int[] frequencies = countLetters(input);
        int maxDex = maxIndex(frequencies);
        int dKey = maxDex - 4;               //"4" because we take "e" as most used letter in english
        if(maxDex < 4){
        
            dKey = (26 - (4-maxDex)) % 26;
        }
        CaesarCipher cc = new CaesarCipher(dKey);
        
        String decryptedText = cc.decrypt(input);
        
        return decryptedText;
    } 
    
    public void simpleTests(){
        
        FileResource fr = new FileResource();
        String contents = fr.asString();
        
        CaesarCipher cc = new CaesarCipher(18);
        
        String encryptedText = cc.encrypt(contents);
        System.out.println(encryptedText);
        
        String decryptText = cc.decrypt(encryptedText);
        System.out.println("Normal Decryption:"+decryptText);
        
        String decryptedText2 = breakCaesarCipher(encryptedText);
        System.out.println("Frequency Based Decryption:"+decryptedText2);
    }
}

