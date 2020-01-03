// A Java program for Dijkstra's single source shortest path algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.*;
 
public class ShortestPath {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
   
    int minDistance(int dist[], Boolean sptSet[],int V)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(int dist[], int destination,int V,String cities[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(cities[i] + " \t\t " + dist[i]);
        System.out.println("Shortest Distance from source to destnation is "+dist[destination]+" units");
    }
 
    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src, int destination,int V,String cities[])
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet,V);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // print the constructed distance array
        printSolution(dist, destination,V,cities);
    }
 
    // Driver method
    public static void main(String[] args) throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        String destination="";
        int n,flag=0,find=-1;
        System.out.println("Enter total Number of cities : ");
        n=Integer.parseInt(in.readLine());
        String cities[] = new String[n];
        System.out.println("Enter Source city : ");
        cities[0]=in.readLine();
        for(int i=1;i<n;i++){
            System.out.println("Enter city "+i+" : ");
            cities[i]=in.readLine();
        }
        System.out.println("Enter distances for the mentioned cities : ");
        int graph[][] = new int[n][n];
        for(int i=0;i<n;i++)
        {
              for(int j=0;j<n;j++)

      {         System.out.println("Enter distance from "+cities[i]+" to "+cities[j]+" : ");
                graph[i][j]=Integer.parseInt(in.readLine());
               }
         }
	Scanner scc=new Scanner(System.in);
	String ch="";
	do
	{
        System.out.println("Enter destination city : ");
        destination=in.readLine();
	find=-1;
	flag=0;
        for(int i=0;i<n;i++){
            if(cities[i].equals(destination)){
            flag=1;
            find=i;
            break;
        }
        }
        if(flag==0){
            System.out.println("There is no such city.");
        }
        else{
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0, find, n,cities);
	}
	System.out.println("Press Y if you want to Continue otherwise press N");
	 ch  = in.readLine();
    
    }while( ch.equals("Y")|| ch.equals("y"));
    }
}
