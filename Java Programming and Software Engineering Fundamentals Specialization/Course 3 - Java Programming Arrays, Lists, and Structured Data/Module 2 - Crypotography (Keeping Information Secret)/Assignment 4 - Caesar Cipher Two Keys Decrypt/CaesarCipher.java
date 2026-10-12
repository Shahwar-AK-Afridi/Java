
/**
 * Write a description of CaesarCipher here.
 * You will start with the Caesar Cipher algorithm you learned about in the videos, and you will make some enhancements to it, so that it works with all letters (both uppercase and lowercase) and to make it a little bit harder to decrypt. 
 * @author (Shahwar Afridi) 
 * @version (08/10/2026)
 */

import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key){
    
        StringBuilder encryptedText = new StringBuilder(input);
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabets = alphabets.substring(key) + alphabets.substring(0,key); 
        String lowerAlphabets = alphabets.toLowerCase();
        String lowerShiftedAlphabets = shiftedAlphabets.toLowerCase();
        
        for(int i = 0; i< encryptedText.length(); i++){
             
            char currentChar = encryptedText.charAt(i);
            
            if(Character.isLowerCase(currentChar)){
                
                int indexOfChar = lowerAlphabets.indexOf(currentChar);
                
                if(indexOfChar != -1){
                
                char newChar = lowerShiftedAlphabets.charAt(indexOfChar);
                encryptedText.setCharAt(i,newChar);
                
                }

            }else if(Character.isUpperCase(currentChar)){
            
                int indexOfChar = alphabets.indexOf(currentChar);
                
                 if(indexOfChar != -1){
                
                char newChar = shiftedAlphabets.charAt(indexOfChar);
                encryptedText.setCharAt(i,newChar);
                
                }
                
            }
            
    }
        
        return encryptedText.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
    
        String encryptText1 = encrypt(input,key1);
        String encryptText2 = encrypt(input,key2);
        StringBuilder newEncryption = new StringBuilder();
        
        for(int i = 0; i < input.length(); i++){
        
            if(i % 2 == 0){
                
                newEncryption.append(encryptText1.charAt(i));
                
            
            }else{
            
                newEncryption.append(encryptText2.charAt(i));
                
            }
               
    }
    return newEncryption.toString();
}
    public void testEncryptTwoKeys(){
    
    String answer = encryptTwoKeys("First Legion",23,17);
    System.out.println(answer);
    answer = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21);
    System.out.println(answer);
    
    }
    public void testencrypt(){
    
    String answer = encrypt("First Legion",23);
    System.out.println(answer);
    answer = encrypt("First Legion",17);
    System.out.println(answer);
    answer = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
    System.out.println(answer);
    }
        
    public void testCaesar(){
    
    FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encrypt(message, 23);
    System.out.println(encrypted);
    //System.out.println("key is " + key + "\n" + encrypted);    
    }
}
