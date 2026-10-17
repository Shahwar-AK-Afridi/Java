
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/

import edu.duke.*;
public class TestCaesarCipherTwo {
    
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
    
    public String halfOfString(String message, int start){
    
        StringBuilder newString = new StringBuilder();
        for(int i = start; i<message.length(); i = i + 2){
            
            newString.append(message.charAt(i));
        }
    return newString.toString();
    }
    
    public int getKey(String s){  //Remember that getKey() estimates the encryption key by assuming "e" was the most frequent letter in the original text
    
        int[] frequencies = countLetters(s);
        int maxLetterIndex = maxIndex(frequencies);
        
        int key = (maxLetterIndex - 4+ 26) % 26;
        return key; 
    } 
        
    public String breakCaesarCipher(String input){
        
        String first = halfOfString(input,0);
        int firstKey = getKey(first);
        
        String second = halfOfString(input,1);
        int secondKey = getKey(second);
                
        System.out.println("First Key: "+firstKey);
        System.out.println("Second Key: "+secondKey);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(firstKey,secondKey);
        String decryptedText = cc.decrypt(input);
        
        return decryptedText;
    }
    
    public void simpleTests(){
    
        FileResource fr = new FileResource();
        String words = fr.asString();
        
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
       
        String encryptedText = cc.encrypt(words);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = cc.decrypt(encryptedText);
        System.out.println("Decrypted: " + decryptedText);

        String decryptedText2 = breakCaesarCipher(encryptedText);
        System.out.println("Automatically Decrypted: " + decryptedText2);
    }
}
