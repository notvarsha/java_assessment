import java.util.Scanner;

public class EncoderDecoder {
    private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    private static final int TABLE_LENGTH = 44;
    
    // Method to encode the given string with a custom offset
    public String encode(String plainText, char offsetChar) {
        if (plainText.isEmpty()) {
            return "ERROR: STRING IS EMPTY";
        }

        int offset = REFERENCE_TABLE.indexOf(offsetChar);
        StringBuilder encodedText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            int index = REFERENCE_TABLE.indexOf(c);
            if (index != -1) {
                // Apply offset and ensure index is within bounds
                index = (index - offset + TABLE_LENGTH) % TABLE_LENGTH;
                encodedText.append(REFERENCE_TABLE.charAt(index));
            } else {
                // Character not found in the reference table, keep it as is
                encodedText.append(c);
            }
        }
        return encodedText.toString();
    }
    
    // Method to decode the given encoded string with a custom offset
    public String decode(String encodedText, char offsetChar) {
        int offset = REFERENCE_TABLE.indexOf(offsetChar);
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            int index = REFERENCE_TABLE.indexOf(c);
            if (index != -1) {
                // Apply offset and ensure index is within bounds
                index = (index + offset + TABLE_LENGTH) % TABLE_LENGTH;
                plainText.append(REFERENCE_TABLE.charAt(index));
            } else {
                // Character not found in the reference table, keep it as is
                plainText.append(c);
            }
        }
        return plainText.toString();
    }
    
    public static void main(String[] args) {
        EncoderDecoder encoderDecoder = new EncoderDecoder();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            // Display menu options
            System.out.println("Select an option:");
            System.out.println("1. Run example testcases");
            System.out.println("2. Encode your string with a custom offset");
            System.out.println("3. Decode your string with a custom offset");
            System.out.println("4. Exit.");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    // Example test cases
                    System.out.println();
                    String examplePlainText = "HELLO WORLD";
                    char offsetChar = 'B';
                    char offsetChar2 = 'F';
                    
                    // Example 1
                    System.out.println("Example 1:");
                    System.out.println("Offset: " + offsetChar);
                    System.out.println("String: " + examplePlainText);
                    String encodedText = encoderDecoder.encode(examplePlainText, offsetChar);
                    System.out.println("Encoded Text: " + encodedText);
                    String decodedText = encoderDecoder.decode(encodedText, offsetChar);
                    System.out.println("Decoded Text: " + decodedText);
                    System.out.println();
                    
                    // Example 2
                    System.out.println("Example 2:");
                    System.out.println("Offset: " + offsetChar2);
                    System.out.println("String: " + examplePlainText);
                    String encodedText2 = encoderDecoder.encode(examplePlainText, offsetChar2);
                    System.out.println("Encoded Text: " + encodedText2);
                    String decodedText2 = encoderDecoder.decode(encodedText2, offsetChar2);
                    System.out.println("Decoded Text: " + decodedText2);
                    System.out.println();
                    break;

                case 2:
                    // Encode custom string with custom offset
                    System.out.println();
                    System.out.print("Enter the character for offset: ");
                    char offsetCharEncoder = scanner.nextLine().charAt(0);
                
                    System.out.print("Enter the string to be encoded: ");
                    String customPlainTextEncoder = scanner.nextLine();
                
                    String customEncodedText = encoderDecoder.encode(customPlainTextEncoder, offsetCharEncoder);
                    System.out.println("Encoded Text: " + customEncodedText);

                    // Prompt user to decode the string
                    System.out.println();
                    System.out.println("Do you want to decode the string?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    int option2 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (option2 == 1) {
                        String customDecodedText = encoderDecoder.decode(customEncodedText, offsetCharEncoder);
                        System.out.println("Decoded Text: " + customDecodedText);
                        System.out.println();
                    } else {
                        System.out.println();
                    }
                    break;

                case 3:
                    // Decode custom string with custom offset
                    System.out.println();
                    System.out.print("Enter the character for offset: ");
                    char offsetCharDecoder = scanner.nextLine().charAt(0);
                    
                    System.out.print("Enter the string to be decoded: ");
                    String customEncodedTextDecoder = scanner.nextLine();

                    String customDecodedText2 = encoderDecoder.decode(customEncodedTextDecoder, offsetCharDecoder);
                    System.out.println("Decoded Text: " + customDecodedText2);
                    System.out.println();
                    break;

                case 4:
                    // Exit the program
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        
        scanner.close();
    }
}
