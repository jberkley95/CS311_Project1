import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main.java
 * @author John Berkley
 * CPP Class: CS 311
 * Date Created: Oct 15, 2017
 *
 * Program was written in Intellij IDEA using JDK 9
 * Modify the File Reader to point to wherever 'input.txt' is located
 */
public class Main {
    public static void main(String[] args)  throws IOException{
        DFSA automaton;
        ArrayList<String> transitions;
        BufferedReader input = new BufferedReader(new FileReader("/Users/johnberkley/IdeaProjects/CS311_Project1/src/input.txt"));
        int fsaNumber = 1;

        //read in data until end of file
        while (input.ready()) {
            String temp = input.readLine();
            if (!temp.equals(".END")) {
                //set up DFSA
                automaton = new DFSA(Integer.parseInt(temp));
                automaton.setFinal(input.readLine());
                automaton.setAlphabet(input.readLine());

                transitions = new ArrayList<>();
                temp = input.readLine();
                while (!temp.equals("") && temp.charAt(0) == '(') {
                    transitions.add(temp.substring(1, temp.length() - 1));
                    temp = input.readLine();
                }
                automaton.setTransitions(transitions);

                //Display processed data
                System.out.println("Finite State Automaton #" + fsaNumber);
                System.out.println("(1) Number of states: " + automaton.getNumStates());
                System.out.print("(2) Final states: ");
                automaton.displayFinalStates();
                System.out.print("(3) Alphabet: ");
                automaton.displayAlphabet();
                System.out.println("(4) Transitions: ");
                automaton.displayTransitions();
                System.out.println("(5) Strings: ");
                while (!temp.equals(".END")) {
                    automaton.checkString(temp);
                    temp = input.readLine();
                }
            }
            fsaNumber++;
            System.out.println();

        }
    }
}
