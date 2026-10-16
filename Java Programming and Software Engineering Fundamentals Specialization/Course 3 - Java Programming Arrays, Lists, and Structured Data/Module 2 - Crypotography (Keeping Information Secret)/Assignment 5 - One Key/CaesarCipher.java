
/**
 * Write a description of CaesarCipher here.
 * In this assignment, you will put together the CaesarCipher class from the lesson and add a decrypt method to decrypt with the same key. In addition you will create a second class, TestCaesarCipher to test examples that use the CaesarCipher class, including writing a method that will automatically decrypt an encrypted file by determining the key and then decrypting with that key.
 * @author (Shahwar Afridi) 
 * @version (16/10/2026)
 */
public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
    
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        this.mainKey = key;
    }
    
    public String encrypt(String input){
    
        StringBuilder encryptedText = new StringBuilder(input);
        String lowerAlphabets = this.alphabet.toLowerCase();
        String lowerShiftedAlphabets = this.shiftedAlphabet.toLowerCase();
        
        for(int i = 0; i< encryptedText.length(); i++){
             
            char currentChar = encryptedText.charAt(i);
            
            if(Character.isLowerCase(currentChar)){
                
                int indexOfChar = lowerAlphabets.indexOf(currentChar);
                
                if(indexOfChar != -1){
                
                char newChar = lowerShiftedAlphabets.charAt(indexOfChar);
                encryptedText.setCharAt(i,newChar);
                
                }

            }else if(Character.isUpperCase(currentChar)){
            
                int indexOfChar = alphabet.indexOf(currentChar);
                
                 if(indexOfChar != -1){
                
                char newChar = shiftedAlphabet.charAt(indexOfChar);
                encryptedText.setCharAt(i,newChar);
                
                }
                
            }
            
    }
        
        return encryptedText.toString();    
    }
    
    public String decrypt(String input){
    
        CaesarCipher cc = new CaesarCipher(26-this.mainKey);
        
        String decrypted = cc.encrypt(input);
        
        return decrypted;
    }

}



