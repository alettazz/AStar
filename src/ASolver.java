import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Created by @alettazz.
 * Date: 3/21/2019
 */
public class ASolver {

    public static void main(String[] args) {
        int isFileInput = 0;
        boolean rand = false;
        boolean solseq = false;
        boolean nvisited = false;
        boolean pcost = false;
        String file = "";
        int[] startStateBoard;
        String hValue = "";
        int argForHeuristic;
        int randomPushes=0;

        if (args.length < 1) {
            printUsage();
        }

        // Check for file argument
        if (args[0].equals("-input")) {
            isFileInput = 1;
            System.out.println("File was passed in: " + args[isFileInput].toLowerCase());
            file = args[isFileInput];
        }

        //–input <FILE>, if missing file, the first thing needs to be the initial state
        if (!file.isEmpty()) {
            startStateBoard = createPuzzleFromFile(file);
            argForHeuristic = 2;
        } else {
            startStateBoard = createPuzzleFromString(args);
            argForHeuristic = 9;
        }

        //after the initial state heuristic needs to be the next


        for (int i = 0; i < args.length; i++) {
           // System.out.println("argsrest  " + args[i]);
            if (args[i].contains("h") || args[i].contains("H")) {
                hValue = args[i + 1];
                System.out.println("argForHeuristic  " + hValue);
            }
            if (args[i].contains("nvisited")) {
                nvisited = true;
            }
            if (args[i].contains("pcost")) {
                pcost = true;
            }
            if (args[i].contains("solseq")) {
                solseq = true;
            }
            if (args[i].contains("rand")) {
                rand = true;
                randomPushes = Integer.parseInt(args[i + 1]);
            }

        }

        System.out.println("randompush  " + randomPushes);

        AStarSearch.search(startStateBoard, rand, randomPushes, solseq, nvisited, pcost, hValue);

    }

    private static int[] createPuzzleFromFile(String file) {
        int[] initState = new int[9];
        int index = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    initState[index++] = Integer.parseInt(s[i]);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception while trying to read '%s'.", file);
            e.printStackTrace();

        }

        for (int i = 0; i < initState.length; i++) {
            System.out.print(initState[i] + " ");
        }
        return initState;
    }

    // Helper method to print the correct usage and end the program
    private static void printUsage() {
        System.out.println("Usage: java ASolver [ -input <file> ] / [initial puzzle] -H <1/2> [-solseq] [-nvisied] [-pcost] [-rand <N M> ]");
        System.exit(-1);
    }

    // Helper method to build our initial 8puzzle state passed in through args
    private static int[] createPuzzleFromString(String[] a) {
        System.out.print("The puzzle created from the commandline values(string) : ");

        for (int i = 0; i < 9; i++) {
            System.out.print(a[i] + " ");
        }
        int[] initState = new int[9];
        for (int i = 0; i < 9; i++) {
            initState[i] = Integer.parseInt(a[i]);
        }
      //  System.out.println("\nInitState: ");
        for (int i = 0; i < initState.length; i++) {
        //    System.out.print(initState[i] + " ");
        }
        return initState;
    }

}
