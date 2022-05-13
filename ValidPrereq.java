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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
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
        StdIn.setFile(args[1]);
        String course1 = StdIn.readString();
        String course2 = StdIn.readString();
        boolean validA = false;
        boolean validB = false;
        boolean valid = false;
        for(ArrayList<String> li : adjList){
            if(li.get(0).contains(course1)){
                validA = true;
            }
            if(li.get(0).contains(course2)){
                validB = true;
            }
        }
        if(validA&&validB){
            valid = true;
        }
        valid = valid(course2, course1, adjList, valid);
        StdOut.setFile(args[2]);
        if(valid){
            StdOut.println("YES");
        }
        else{
            StdOut.println("NO");
        }
    }

    //Checks to see if course1 is in any of the prereqs, direct or indirect, of course2
    private static boolean valid(String c, String c1, ArrayList<ArrayList<String>> adjList, boolean v){
    Stack <String> s = new Stack<String>();
    s.push(c);
    while(!s.isEmpty()){
        for(ArrayList<String> li : adjList){
            if(li.get(0).equals(s.peek())){
                s.pop();
                if(li.contains(c1)){
                    v = false;
                    return v;
                }
                else{
                    for(int i = 1; i<li.size(); i++){
                        s.push(li.get(i));
                    }
                }
                break;
            }
        }
    }
    return v;
    }
}
