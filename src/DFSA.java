import java.util.ArrayList;

/**
 * DFSA.java
 * @author John Berkley
 * CPP Class: CS 311
 * Date Created: Oct 15, 2017
 */
public class DFSA {
    private int numStates;
    private boolean[] finalStates;
    private char[] alphabet;
    private int[][] transitions;

    /**
     * Constructor
     *
     * @param numStates in the dfsa
     */
    public DFSA(int numStates) {
        this.numStates = numStates;
        finalStates = new boolean[numStates + 1];
    }

    /**
     * Gets the number of states in the dfsa
     *
     * @return number of states
     */
    public int getNumStates() {
        return numStates;
    }

    /**
     * Assigns the final dfsa states
     *
     * @param states to be assigned
     */
    public void setFinal(String states) {
        for (String state: states.split(" ")){
            finalStates[Integer.parseInt(state)] = true;
        }
    }

    /**
     * Displays final states
     */
    public void displayFinalStates() {
        for (int i = 0; i < finalStates.length; i++) {
            if (finalStates[i]) System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Assigns the dfsa alphabet
     *
     * @param inputAlphabet to be assigned
     */
    public void setAlphabet(String inputAlphabet) {
        String[] temp = inputAlphabet.split(" ");
        alphabet = new char[temp.length];
        for (int i = 0; i < temp.length; i++) {
            alphabet[i] = temp[i].charAt(0);
        }
    }

    /**
     * Displays the alphabet
     */
    public void displayAlphabet() {
        for (int i = 0; i < alphabet.length - 1; i++) {
            System.out.print(alphabet[i] + ", ");
        }
        System.out.println(alphabet[alphabet.length - 1]);
    }

    /**
     * Defines the list of transition states for the dfsa
     *
     * @param transitionsList to be assigned
     */
    public void setTransitions(ArrayList<String> transitionsList) {
        transitions = new int[numStates + 1][alphabet.length];

        for (int i = 0; i < transitions.length; i++) {
            for (int j = 0; j < transitions[0].length; j++) {
                transitions[i][j] = numStates + 1;
            }
        }

        String[] temp;
        for (int i = 0; i < transitionsList.size(); i++) {
            temp = transitionsList.get(i).split(" ");

            int p = Integer.parseInt(temp[0]);
            int a = getIntVal(temp[1].charAt(0));
            int q = Integer.parseInt(temp[2]);

            transitions[p][a] = q;
        }
    }

    /**
     * Displays the transition states
     */
    public void displayTransitions() {
        for (int i = 0; i < transitions.length; i++) {
            for (int j = 0; j < transitions[0].length; j++) {
                if (transitions[i][j] != numStates + 1) {
                    System.out.println("\t" + i + " " +alphabet[j] + " " + transitions[i][j]);
                }
            }
        }
    }

    /**
     * Checks if str is a valid string for the dfsa
     *
     * @param str to be checked
     */
    public void checkString(String str) {
        int currentState = 0;

        for (int i = 0; i < str.length(); i++) {
            if (!isInAlphabet(str.charAt(i)) || currentState == numStates + 1) {
                if (currentState != numStates + 1 && finalStates[currentState] && i == 0) {
                    System.out.printf("\t%-40s%-20s%n", str, "Accept");
                } else {
                    System.out.printf("\t%-40s%-20s%n", str, "Reject");
                    break;
                }
            } else if (i == str.length() - 1) {
                currentState = transitions[currentState][getIntVal(str.charAt(i))];

                if (currentState != numStates + 1 && finalStates[currentState]) {
                    System.out.printf("\t%-40s%-20s%n", str, "Accept");
                } else {
                    System.out.printf("\t%-40s%-20s%n", str, "Reject");
                    break;
                }
            } else {
                currentState = transitions[currentState][getIntVal(str.charAt(i))];
            }
        }
    }

    /**
     * Checks if the given character ch is in the alphabet
     *
     * @param ch to be checked
     * @return true if in alphabet, false otherwise
     */
    private boolean isInAlphabet(char ch) {
        for (char anAlphabet : alphabet) {
            if (anAlphabet == ch) return true;
        }
        return false;
    }

    /**
     * Converts character to integer for state shifts
     *
     * @param ch to be converted
     * @return integer value for character
     */
    private int getIntVal(char ch) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == ch) return i;
        }

        return numStates + 1;
    }
}
