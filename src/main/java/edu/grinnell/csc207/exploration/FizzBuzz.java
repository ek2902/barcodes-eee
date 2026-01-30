 package edu.grinnell.csc207.exploration;

public class FizzBuzz {
    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q "-Dexec.mainClass=edu.grinnell.csc207.exploration.FizzBuzz"
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        
        for (int i = 1; i <= n; i++) {
            boolean div3 = i%3 == 0;
            boolean div5 = i%5 == 0;

            if (div3) {
                System.out.print("fizz");
            }

            if (div5) {
                System.out.print("buzz");
            }

            if(!div3 && !div5){
                System.out.print(i);
            }

            System.out.println("");
        }
    }
}
