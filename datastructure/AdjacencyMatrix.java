package datastructure;

/**
 * AdjacencyMatrix
 */
public class AdjacencyMatrix {
    int vertices; // Number of vertex 
    int edges; // Number of edges 
    int[][] matrix; // Adjacency matrix 

    public AdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }

    public void addEdge(int start, int end, int weight) {
        if(start < 0 || start > vertices || end < 0 || end > vertices)
            throw new IndexOutOfBoundsException();
        
        this.matrix[start][end] = weight;
        this.matrix[end][start] = weight;

        this.edges = this.edges + 1;
    }

    public int[] dijikstra(int start) {
        int[] distance = new int[this.vertices];
        boolean[] visited = new boolean[this.vertices];

        for(int i = 0; i < distance.length; ++i) 
            distance[i] = Integer.MAX_VALUE;

        distance[start] = 0;

        for(int i = 0; i < this.vertices - 1; ++i) {
            int u = closestVertex(distance, visited); 
            visited[u] = true;

            for(int v = 0; v < this.vertices; ++v) 
                if(!visited[v] && this.matrix[u][v] != 0 &&
                    distance[u] != Integer.MAX_VALUE &&
                    distance[u] + this.matrix[u][v] < distance[v])
                distance[v] = distance[u] + this.matrix[u][v];
        }

        return distance;
    }

    private int closestVertex(int[] distance, boolean[] visited) {
        int min_index = 0; int min_value = Integer.MAX_VALUE;

        for(int i = 0; i < distance.length; ++i) 
            if(!visited[i] && distance[i] < min_value)
                { min_value = distance[i]; min_index = i; }

        return min_index;
    }
}

class AdjacencyMatrixDriverTest {

    public static void main(String[] args) {
        // write test form here
        AdjacencyMatrix abc = new AdjacencyMatrix(9);

        abc.addEdge(0, 1, 4); 
        abc.addEdge(0, 7, 8); 
        abc.addEdge(1, 2, 8); 
        abc.addEdge(1, 7, 11); 
        abc.addEdge(2, 3, 7); 
        abc.addEdge(2, 8, 2); 
        abc.addEdge(2, 5, 4); 
        abc.addEdge(3, 4, 9); 
        abc.addEdge(3, 5, 14); 
        abc.addEdge(4, 5, 10); 
        abc.addEdge(5, 6, 2); 
        abc.addEdge(6, 7, 1); 
        abc.addEdge(6, 8, 6); 
        abc.addEdge(7, 8, 7); 

        int[] array = abc.dijikstra(0);

        for(int ele: array) System.out.println(ele);
    }
}
