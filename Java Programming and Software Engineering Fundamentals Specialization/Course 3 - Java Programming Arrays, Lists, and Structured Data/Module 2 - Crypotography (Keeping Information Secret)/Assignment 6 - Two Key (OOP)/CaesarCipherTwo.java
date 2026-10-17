
/**
 * Write a description of CaesarCipherTwo here.
 * n this assignment, you will put together the CaesarCipherTwo class that encrypts a message with two keys (the same way as the previous lesson: key1 is used to encrypt every other letter, starting with the first, and key2 is used to encrypt every other letter, starting with the second), and also decrypts the same message. In addition you will create a second class, TestCaesarCipherTwo to test examples that use the CaesarCipherTwo class, including writing a method that will automatically decrypt an encrypted file by determining the two keys that were used to encrypt it. 
 * @author (Shahwar Afridi) 
 * @version (17/10/2026)
 */
public class CaesarCipherTwo {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2){
    
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        this.key1 = key1;
        this.key2 = key2;       

    }
    
    public String encrypt(String input){
        
        StringBuilder encryptedText = new StringBuilder(input);
        
        for(int i = 0; i< input.length(); i++){
             
            char currentChar = input.charAt(i);
            char lowerChar = Character.toLowerCase(currentChar);
    
            int indexOfChar = this.alphabet.indexOf(lowerChar);
                
            if(indexOfChar != -1){
                
                char newChar;
                
                 if(i % 2 == 0){
                
                    newChar = shiftedAlphabet1.charAt(indexOfChar);
            
                }else{
            
                    newChar = shiftedAlphabet2.charAt(indexOfChar);
                
                }
                
                if (Character.isUpperCase(currentChar)) {
                    
                    newChar = Character.toUpperCase(newChar);
           
                }

                encryptedText.setCharAt(i, newChar);
                
            }
                
            }
        return encryptedText.toString();
    }
    
    
    public String decrypt(String input){
                        
        CaesarCipherTwo cc = new CaesarCipherTwo((26-this.key1)%26,(26-this.key2)%26);
        String decryptedText = cc.encrypt(input);
        return decryptedText;
        
    }
    
    public void testCipherTwo() {

    CaesarCipherTwo cc = new CaesarCipherTwo(23, 17);

    String original = "First Legion";

    String encrypted = cc.encrypt(original);
    String decrypted = cc.decrypt(encrypted);

    System.out.println("Original: " + original);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);

    System.out.println("Test Passed: " + original.equals(decrypted));
    }
}   

