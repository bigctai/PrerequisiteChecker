package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {
        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        //Step 1
        StdIn.setFile(args[0]);
        String [] arr = StdIn.readAllStrings();
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[a+1]);
        ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>();
        //Adding all courses to adjList
        for(int i= 1; i<a+1; i++){
            ArrayList<String> adj = new ArrayList<String>();
            adj.add(arr[i]);
            adjList.add(adj);
        }
        //Appending all prereqs to each ArrayList in adjList
        for(int i = a+2; i<arr.length; i+=2){
            for(ArrayList<String> li : adjList){
                if(arr[i].equals(li.get(0))){
                    li.add(arr[i+1]);
                }
            }
        }
        //Step 2
        StdOut.setFile(args[1]);
        for(ArrayList<String> li : adjList){
            for(String str : li){
                StdOut.print(str + " ");
            }
            StdOut.println("");
        }
    }
}
