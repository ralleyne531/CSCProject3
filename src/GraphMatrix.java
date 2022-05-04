import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph{
    private int[][] graph;
    int[] vertices;
    private boolean[] visited;

    public void init(int n) {
        this.graph = new int[n][n]; //Creates Graph Matrix with given number of vertices
        vertices = new int[n]; //Creates array of int representing weight's for each edge
        visited = new boolean[n]; //Creates array of boolean represented if visited or not
    }

    public int nodeCount() {
        return this.vertices.length; //Returns length of vertices array
    }

    public int edgeCount() {
        int count = 0; //Initializes count variable
        for(int i = 0; i < this.graph.length; i++){
            for(int j = 0; j < this.graph.length; j++){ //Nested for loop to traverse matrix
            if(hasEdge(i,j)) { //Uses hasEdge() method to check for edge
                count++; //If an edge is found we add to count
                }
            }
        }
        return count; //Return total count
    }

    public void addEdge(int v, int w, int wgt) {
        this.graph[v][w] = wgt; //Sets the intersect (value of edge) to weight
    }


    public int getWeight(int v, int w) {
        int weight = 0; //Initializes weight to return
        if(hasEdge(v,w)) { //Checks if there is an edge
            for (int i = 0; i < this.graph.length; i++) {
                for (int j = 0; i < this.graph.length; i++) { //Nested for loop to traverse matrix
                    if (v == i && w == j) { //Checks if current traversed edge matches the request edge
                        weight = (this.graph[v][w]); //Assigns the weight of matched edge to weight int
                    }
                }
            }
        }else{
            System.exit(0); //Exits if no edge is found
        }
        return weight; //Returns weight
    }

    public void setWeight(int v, int w, int wgt) {
        if(hasEdge(v,w)){ //Checks if there is an edge between vertex
            this.graph[v][w] = wgt; // If true sets the intersection (Edge weight) to given weight
        }else{
            System.out.println("Sorry, there is no edge between these vertices. Use adddEdge() to add an edge first."); //Returns statement otherwise
        }
    }

    public void removeEdge(int v, int w) {
        if(!hasEdge(v,w)){ //Checks for an edge between given vertices
            System.out.println("Error! There is no edge to remove between these vertices"); //Returns statement
        }else {
            this.graph[v][w] = 0; //Sets Edge weight to "removed" value
        }
    }

    public boolean hasEdge(int v, int w) {
        return this.graph[v][w] != 0; //If statement that returns false if value of intersection of vertices is 0
    }

    public int[] neighbors(int v) {
        int count = 0; //Initializes count variable
        for(int i = 0; i < vertices.length; i++){ //Loop through vertices
            if(hasEdge(v,i)){ //Check if each vertex has an edge with the given vertex to check for neighbors
                count++; //If true add to count
            }
        }
        int[] neighbors = new int[count]; //Initialize int array with size of count
        int newCount = 0; //New counter to check if array is full
        while(newCount < count){ //While the array is not fully
            int i = 0; //Index
            if(hasEdge(v,i)){ //Check again for edge
                neighbors[newCount] = i; //If edge is found then add neighbor to array
                newCount++; //Move to next index of array
            }
        }
        return neighbors; // Return Array
        //Would it have worked in this method if I had created a string of all the neighbors and used .split to parse into int array?
    }

    public void resetVisited() {
        Arrays.fill(visited, false); //Fills array (visited) with value (false)
    }

    public ArrayList<Integer> BFS(int v) {
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> BFSearch = new LinkedList<>(); //Initialize BFS Queue //I had Queue<Integer> originally but I could not return a Queue
        visited[v] = true; //Sets starting vertex to visited
        BFSearch.add(v); //Enqueue starting vertex
        while(!BFSearch.isEmpty()){
            v = BFSearch.remove(); //Dequeue vertex
            bfs.add(v); //Pre-visit
            for(int i = 0; i < visited.length; i++){ //Iterates through neighbors
                if (!visited[i]) { //Checks if neighbor has been visited already
                    BFSearch.add(i); //Add to queue
                    visited[i] = true; //change to visited
                }
            }
        }

        return bfs;
    }

    public boolean hasPath(int v, int w) {
        ArrayList<Integer> path = this.BFS(v);
        for(int i = 0; i < path.size(); i++){
            return path.get(i) == w;
        }
        return false;
    }


    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> order = new ArrayList<>(); //Create arraylist of topological order
        this.resetVisited(); //Resets visited before any BFS calls
        order.addAll(BFS(0)); //Calls BFS from starting index and adds to arraylist
        for(int i = 0; i < nodeCount(); i++ ){ //Iterates through nodes
            if(!visited[i]){ //Check if node has been visited
                order.addAll((BFS(i))); //Call BFS from that node and append to arraylist
                visited[i] = true; //Mark node as visited
            }
        }

        return order;
    }

    public static void main(String[] args){
        GraphMatrix G = new GraphMatrix();
        G.init(4);
        G.addEdge(0,1,4);
        G.addEdge(0,2,2);
        G.addEdge(1,3,47);
        G.addEdge(2,1,6);
        ArrayList<Integer> bf = G.BFS(0);
        ArrayList<Integer> ts = G.topologicalSort();
        System.out.println(bf);
        System.out.println(ts);
    }

}

