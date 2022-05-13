package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

        // Step 1
        StdIn.setFile(args[0]);
        String [] arr = StdIn.readAllStrings();
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[a+1]);
        ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>();
        for(int i= 1; i<a+1; i++){
            ArrayList<String> adj = new ArrayList<String>();
            adj.add(arr[i]);
            adjList.add(adj);
        }
        for(int i = a+2; i<arr.length; i+=2){
            for(ArrayList<String> li : adjList){
                if(arr[i].equals(li.get(0))){
                    li.add(arr[i+1]);
                }
            }
        }

        //Step 2
        StdIn.setFile(args[1]);
        int c = StdIn.readInt();
        String [] arr1 = StdIn.readAllStrings();
        ArrayList<String> coursesTaken = new ArrayList<String>();
        for(String course : arr1){
            coursesTaken.add(course);
        }
        //Adds all prereqs
        for(int i = 0; i<adjList.size(); i++){
            if(coursesTaken.contains(adjList.get(i).get(0))){
                for(String courses : adjList.get(i)){
                    if(!coursesTaken.contains(courses)){
                        coursesTaken.add(courses);
                        i=0;
                    }
                }
            }
        }
        //Step 3
        StdOut.setFile(args[2]);
        //Checks if the prereqs for each course exist in all courses taken, and if they do, prints it out
        for(ArrayList<String> li : adjList){
            boolean eligible = true;
            if(coursesTaken.contains(li.get(0))){
                eligible = false;
            }
            else{
                for(int i = 1; i<li.size(); i++){
                    if(!coursesTaken.contains(li.get(i))){
                        eligible = false;
                    }
                }
            }
            if(eligible){
                StdOut.println(li.get(0));
            }
        }
    }
}