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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
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
        for(int i = a+2; i<arr.length; i+=2){
            for(ArrayList<String> li : adjList){
                if(arr[i].equals(li.get(0))){
                    li.add(arr[i+1]);
                }
            }
        }
        //Step 2
        StdIn.setFile(args[1]);
        String courseToTake = StdIn.readString();
        int d = StdIn.readInt();
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
        //Finds the direct prerequisites for the course you want to take
        ArrayList<String> prereqs = new ArrayList<String>();
        for(ArrayList<String> crses : adjList){
            if(crses.get(0).equals(courseToTake)){
                for(String crse : crses){
                    prereqs.add(crse);
                }
            }
        }
        //removes the course you are trying to take from the courses you need to take
        prereqs.remove(0);
        //Finds all of the indirect prerequisites for the course you want to take
        for(int i = 0; i<adjList.size(); i++){
            if(prereqs.contains(adjList.get(i).get(0))){
                for(String courses : adjList.get(i)){
                    if(!prereqs.contains(courses)){
                        prereqs.add(courses);
                        i=0;
                    }
                }
            }
        }
        //Step 3
        StdOut.setFile(args[2]);
        //Compares all of the prerequisites for the course you want to take to all of the prerequisites that you currently have completed
        for(String prereq : prereqs){
            if(!coursesTaken.contains(prereq)){
                StdOut.println(prereq);
            }
        }
    }
}
