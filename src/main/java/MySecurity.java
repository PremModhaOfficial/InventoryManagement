package main.java;

public class MySecurity {
    public static String encrypt(String plainText, int shift) {
        char[] encryptedText = new char[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encryptedText[i] = (char) (((c - base + shift) % 26) + base);
            } else {
                encryptedText[i] = c;
            }
        }
        return new String(encryptedText);
    }

    public static String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, 26 - shift); // Decryption is just shifting in the opposite direction
    }

    public static void main(String[] args) {
        String originalText = "Hello, World!";
        int shift = 3;

        String encryptedText = encrypt(originalText, shift);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}