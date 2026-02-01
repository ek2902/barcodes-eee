package edu.grinnell.csc207.barcodes;

public class Barcodes {
    
    /** The encodings for each digit as represented by the UPC-A barcode format.
     * 
     */
    private static int[][] ENCODINGS = {
        {3, 2, 1, 1},// 0
        {2, 2, 2, 1},// 1
        {2, 1, 2, 2},// 2
        {1, 4, 1, 1},// 3
        {1, 1, 3, 2},// 4
        {1, 2, 3, 1},// 5
        {1, 1, 1, 4},// 6
        {1, 3, 1, 2},// 7
        {1, 2, 1, 3},// 8
        {3, 1, 1, 2} // 9
    };

    /** Converts the given character into a digit from 0-9.
     * 
     * @param ch a character representing a digit from 0-9
     * @return the character value as an integer
     */
    public static int toDigit(char ch){
        Character zero = '0';
        return zero.compareTo(ch) * -1;
    }

    /** Prints a white box character.
     * 
     */
    public static void whiteBox(){
        System.out.print("\033[37m█\033[0m");
    }

    /** Prints a black box character.
     * 
     */
    public static void blackBox(){
        System.out.print("\033[30m█\033[0m");
    }

    /** Prints either a white or black box, based on the boolean color
     * 
     * @param color a boolean where true represents a white box and false represents a black box
     */
    public static void printBox(boolean color){
        if (color) {
            whiteBox();
        }
        else {
            blackBox();
        }
    }

    /** Checks if the given code is a valid UPC-A barcode number.
     *      
     * @param code a string
     * @return true if code is 12 characters and only digits, false otherwise
     */
    public static boolean isValidCode(String code) {
        for (int x = 0; x < code.length(); x++) {
            char ch = code.charAt(x);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        if (code.length() != 12) {
            return false;
        }
        return true;
    }

    /** Given a valid UPC-A code, computes and returns its check digit.
     * 
     * @param code a string of twelve digits, with no spaces or padding, that represents a valid UPC-A code
     * @return a integer check digit that should be equal to the twelfth digit of the given code
     */
    public static int computeCheckDigit(String code) {
        char[] digitsChar = code.toCharArray();
        int[] digits = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int i = 0; i < digitsChar.length; i++){
            digits[i] = toDigit(digitsChar[i]);
        }
        
        int checkDigit = 0;
        for (int j = 0; j < digits.length - 1; j++) {
            if (j % 2 == 0) {
                checkDigit += 3 * digits[j];
            }
            else {
                checkDigit += digits[j];
            }
        }

        checkDigit = checkDigit % 10;
        if (checkDigit == 0) {
            return checkDigit;
        } else {
            return 10 - checkDigit;
        }
    }

    /** Prints 9 white boxes to the terminal.
     * 
     */
    public static void quietZone() {
        for(int x = 0; x < 9; x++){
            whiteBox();
        }
    }
    
    /** Prints a start/end zone barcode pattern (BWB) to the terminal.
     * 
     */
    public static void bookEndZone() {
        boolean color = false;
        for (int x = 0; x < 3; x++){
            printBox(color);
            color = !color;
        }
    }

    /** Prints a middle zone barcode pattern (WBWBW) to the terminal.
     * 
     */
    public static void middleZone() {
        boolean color = true;
        for (int x = 0; x < 5; x++){
            printBox(color);
            color = !color;
        }
    }

    
    /** Prints a pattern of black and white boxes corresponding to the pattern for each digit.
     * 
     * @param digit an integer in the range 0-9 (inclusive) that we wish to represent in our barcode sequence
     * @param color a boolean corresponding to one of two colors
     */
    public static void barcodeDigit(int digit, boolean color) {//white is true, black is false
        for(int i = 0; i < 4; i++) {
            for(int x = 0; x < ENCODINGS[digit][i]; x++){
                printBox(color);
            }
            color = !color;
        }
    }


    /** Prints one row of the barcode indicated by the given code.
     * 
     * @param code a string of twelve digits, with no spaces or padding, that represents a valid UPC-A code
     */
    public static void printBarcodeRow(String code) {
        quietZone();
        bookEndZone();
        for(int i = 0; i<6; i++){
            char ch = code.charAt(i);
            int digit = toDigit(ch);
            boolean color = true;
            barcodeDigit(digit, color);
        }
        middleZone();
        for(int i = 6; i<12; i++){
            char ch = code.charAt(i);
            int digit = toDigit(ch);
            boolean color = false;
            barcodeDigit(digit, color);
        }
        bookEndZone();
        quietZone();
        System.out.println();
    }


    /** Prints the whole barcode by running printBarcodeRow `height` number of times.
     * 
     * @param code a string of 12 chars, each of which is an arabic numeral
     * @param height a positive integer, specifying the desired height of the barcode
     */
    public static void printBarcode(String code, int height){
        for(int x = 0; x < height; x++){
            printBarcodeRow(code);
        }
    }
    
    /** Checks command-line arguments and reports errors. If no errors are found, prints the desired barcode to the terminal.
     * 
     * @param args contains 12 digits from 0-9 (inclusive). Verified.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: barcode <UPC-A code> <height>");
            System.exit(1);
        }
        String code = args[0];
        int height = Integer.parseInt(args[1]);

        if (!isValidCode(code)) {
            System.out.println("Code must be a string of 12 digits.");
            System.exit(1);
        }
        if (height < 0) {
            System.out.println("Height must be a positive integer.");
            System.exit(1);
        }

        char lastCharDigit = code.charAt(11);
        int checkDigit = toDigit(lastCharDigit);
        int computedCheck = computeCheckDigit(code);
        
        if (computedCheck != checkDigit) {
            System.out.println("Expected check digit " + computedCheck + " but found " + checkDigit + ".");
            System.exit(1);
        }

        printBarcode(code, height);
    }
}