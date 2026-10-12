
/**
 * Write a description of CaesarBreaker here.
 * You should start by writing the decryption method explained in the lesson that decrypts a message that was encrypted with one key, using statistical letter frequencies of English text. Then you will add code to be able to decrypt a message that was encrypted with two keys, using ideas from the single key decryption method and the encryption with two keys method from the program you wrote in the last lesson.
 * @author (Shahwar Afridi) 
 * @version (11/10/2026)
 */

import edu.duke.*;
public class CaesarBreaker {
    
    public int[] countLetters(String word){
        
        String alphabets = "abcdefghijklmonpqrstuvwxyz";
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
        for(int j = 0; j<letterFrequency.length;j++){
        
           System.out.println(letterFrequency[j]);
        
        }
        return letterFrequency;
    }
    
    public void testcountLetters(){
    
    countLetters("Aaa ab,,:bccc;;;;dddzzzzz");
    
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
    
    public void testmaxIndex(){
    
        int[] test = {1,2,3323,4,3};
        System.out.println(maxIndex(test));
    
    }
    //This is frequency-based decryption, so it won't always recover the original text, particularly for short messages.
    public String decrypt(String encrypted){
    
        CaesarCipher cc = new CaesarCipher();
        int[] frequencies = countLetters(encrypted);
        int maxDex = maxIndex(frequencies);
        int dKey = maxDex - 4;               //"4" because we take "e" as most used letter in english
        if(maxDex < 4){
        
            dKey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26 - dKey);
    }
    
    public void testDecrypt(){
    
    String original = "Meet me at the tree";

    CaesarCipher cc = new CaesarCipher();

    String encrypted = cc.encrypt(original, 7);

    String decrypted = decrypt(encrypted);

    System.out.println("Original: " + original);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);

    System.out.println("Test Passed: " + original.equals(decrypted));
    }
    
    public String halfOfString(String message, int start){
    
        StringBuilder newString = new StringBuilder();
        for(int i = start; i<message.length(); i = i + 2){
            
            newString.append(message.charAt(i));
        }
    return newString.toString();
    }
    
    public void testhalfOfString(){
    
        System.out.println("Answer:"+halfOfString("Qbkm Zgis",0)); //“Qk gs”
        System.out.println(halfOfString("Qbkm Zgis",1)); //“bmZi”
    }
    
    public int getKey(String s){  //Remember that getKey() estimates the encryption key by assuming "e" was the most frequent letter in the original text
    
        int[] frequencies = countLetters(s);
        int maxLetterIndex = maxIndex(frequencies);
        
        int key = maxLetterIndex - 4;
        return key; 
    } 
    
    public String decryptTwoKeys(String encrypted){
    
        String first = halfOfString(encrypted,0);
        int firstKey = getKey(first);
        String second = halfOfString(encrypted,1);
        int secondKey = getKey(second);
        
        System.out.println("First Key: "+firstKey);
        System.out.println("Second Key: "+secondKey);
        
        CaesarCipher cc = new CaesarCipher();
        String decryptedText = cc.encryptTwoKeys(encrypted, (26-firstKey)% 26,(26-secondKey)% 26); //The +26 and %26 ensure the calculated key stays between 0 and 25.
    
        return decryptedText;
    }
    
    public void testDecryptTwoKeys() {

    String original = "Here we see the green trees where the bees meet near the creek, and everyone cheers.";

    CaesarCipher cc = new CaesarCipher();

    String encrypted = cc.encryptTwoKeys(original, 23, 17);

    String decrypted = decryptTwoKeys(encrypted);

    System.out.println("Original: " + original);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);

    System.out.println("Test Passed: " + original.equals(decrypted));
}
}
