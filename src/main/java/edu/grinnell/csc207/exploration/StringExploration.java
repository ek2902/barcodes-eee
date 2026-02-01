package edu.grinnell.csc207.exploration;
import java.util.Scanner;

public class StringExploration {

    /** Produces a string that is the result of interspersing a comma between each string in the given array.
     * 
     * @param strs an array consisting of some number of strings.
     * @return a string consisting of each string in the given array concatenated with a comma between each string.
     */
    public static String intersperse(String[] strs) {
        String temp = "";
        for (int x = 0; x < strs.length; x++) {
            if (x == strs.length-1) {
                temp = temp + strs[x];
            } else {
                temp = temp + strs[x] + ",";
            }
        }
        return temp;
    }

    /** Given a string in the format of "LastName,FirstName,MiddleName", returns a string in the format of 
     * "FirstName MiddleName LastName".
     * 
     * @param fullName a string in the format of "LastName,FirstName,MiddleName"
     * @return a string in the format of "FirstName MiddleName LastName"
     */
    public static String parseName(String fullName) {
        String[] nameArr = fullName.split(",");
        return nameArr[1] + " " + nameArr[2] + " " + nameArr[0];
    }

    /** Asks a given question to the user, prompting them to answer "yes" or "no". Returns true if the answer is positive
     * and false if the answer is negative. Is 'forgiving': will accept several different variations on "yes" or "no".
     * 
     * @param question a string representing the question being asked to the user.
     * @return a boolean: true if the user answers positively and false if the user answers negatively.
    */
    public static boolean forgivingPrompt(String question) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(question + " [Y/N]");
            String response = scanner.nextLine();
            response.toLowerCase();

            if (response.equals("y")||response.equals("yes")||response.equals("yep")) {
                return true;
            } else if (response.equals("n")||response.equals("no")||response.equals("nope")) {
                return false;
            } else {
                System.out.println("Answer not recognized, please try again");
            }
        }
        
    }

    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q "-Dexec.mainClass=edu.grinnell.csc207.exploration.StringExploration"
    public static void main(String[] args) {
        // Write-up examples for 2.2: Iceberg, Right Ahead!
        String s1 = "hello world!";
        String s2 = "hello world!";
        System.out.println("A");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s1));     // A

        String s3 = s1.substring(0, 5);
        String s4 = s2.substring(0, 5);
        System.out.println("B");
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
        System.out.println(s4.equals(s3));     // B

        String s5 = new String(new char[] { 'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', '!' });
        System.out.println("C");
        System.out.println(s1 == s5);
        System.out.println(s1.equals(s5));
        System.out.println(s5.equals(s1));      // C

        // Problem 1
        String[] test1 = {"ab", "cd", "ef"};
        System.out.println(intersperse(test1));

        // Problem 2
        System.out.println(parseName("Turing,Alan,Mathison"));

        // Problem 3
        System.out.println(forgivingPrompt("Are you a skibidi sigma based rizzler?"));
    }
}