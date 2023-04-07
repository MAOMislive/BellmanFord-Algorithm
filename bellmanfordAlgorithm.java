import java.util.*;

public class bellmanfordAlgorithm {
    public static int[] bellmanfordAlgorithm(int V, int E, int[][] edges, int source) {
        int[] distances = new int[V];

        for(int i = 0 ; i < V; i++){  // Initializing all distances to Infinity
            distances[i] = Integer.MAX_VALUE;
        }

        distances[source] = 0;  // Distance of the source vertex to itself is 0

        for (int i = 1; i < V; i++) {  // Loop through all vertices (V-1) times
            for (int j = 0; j < E; j++) {  // Loop through all edges and update the distances if a shorter path is found
                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];
                if (distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
                    distances[v] = distances[u] + w;
                }
            }
        }


        for (int j = 0; j < E; j++) {  // Checking for negative weight cycles
            int u = edges[j][0];
            int v = edges[j][1];
            int w = edges[j][2];
            if (distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
                System.out.println("Graph contains negative weight cycle");
                return new int[V];
            }
        }

        return distances; // Returning the distances
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the no. of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter the no. of edges:");
        int E = sc.nextInt();


        int[][] edges = new int[E][3]; // A 2D array to store the edges and their weights

        System.out.println("Enter the edges and their weights:\n");


        for (int i = 0; i < E; i++) {  //Scanning the edges and their weights
            System.out.println("Starting vertex of the edge:");
            edges[i][0] = sc.nextInt(); // Starting vertex of edge
            System.out.println("Ending vertex of the edge:");
            edges[i][1] = sc.nextInt(); // Ending vertex of edge
            System.out.println("Weight of the edge:");
            edges[i][2] = sc.nextInt(); // Weight of edge
        }

        int[] distances = bellmanfordAlgorithm(V, E, edges, 0);

        System.out.println("Shortest distances from vertex 0:");
        for (int i = 0; i < V; i++) {
            if(distances[i] == Integer.MAX_VALUE){
                System.out.println(i + " : Infinity / Not visitable"); continue;
            }
            System.out.println(i + " : " + distances[i]);
        }
    }
}
