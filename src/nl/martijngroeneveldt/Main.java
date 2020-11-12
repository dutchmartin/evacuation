package nl.martijngroeneveldt;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read line 1.
        int cities = scanner.nextInt();
        int roads = scanner.nextInt();
        // Read line 2.
        int[] endangered = new int[scanner.nextInt()];
        int[] designated = new int[scanner.nextInt()];
        for (int i = 0; i < endangered.length; i++) {
            endangered[i] = scanner.nextInt();
        }
        for (int i = 0; i < designated.length; i++) {
            designated[i] = scanner.nextInt();
        }
        // Read and build a adjacency matrix
        var matrix = new int[cities+2][cities+2];
        for(int i = 0; i < roads; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int capacity = scanner.nextInt();
            matrix[from][to] += capacity;
        }
        // Add a source and sink to the graph.
        for(int i: endangered){
            matrix[cities][i] = Integer.MAX_VALUE;
        }
        for(int i: designated){
            matrix[i][cities+1] = Integer.MAX_VALUE;
        }

        // Print the answer.
        System.out.println(maxFlow(matrix, cities, cities+1));
        //printMatrix(matrix);
    }

    /**
     * Prints a 2d integer matrix to the console, for debugging purposes.
     * @param matrix the matrix to print.
     */
    static void printMatrix(int[][] matrix) {
        for (int[] matrixRow : matrix) {
            for (int matrixColumn : matrixRow) {
                System.out.print(matrixColumn + " ");
                if (matrixColumn == 0) {
                    System.out.print("  ");
                }
            }
            System.out.print('\n');
        }
    }

    /**
     * Breadth first search to find augmented path
     */
    private static boolean BFS(int[][] adjacentMatrix, Map<Integer, Integer> parent,
                        int source, int sink) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        // See if we can find augmented path from source to sink.
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < adjacentMatrix.length; v++) {
                // Explore the vertex only if it is not visited and its residual capacity is
                // greater than 0.
                if (!visited.contains(v) && adjacentMatrix[u][v] > 0) {
                    // Add in parent map saying v got explored by u.
                    parent.put(v, u);
                    // Add v to visited.
                    visited.add(v);
                    // Add v to queue for BFS.
                    queue.add(v);
                    // If the sink is found then augmented path is found.
                    if (v == sink) {
                        return true;
                    }
                }
            }
        }
        // Return false, since we did not find an augmented path.
        return false;
    }

    /**
     * Ford-fulkerson algorithm.
     * Time complexity is O(VE^2)
     *
     * References:
     * http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
     * https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm
     */
    public static int maxFlow(int[][] capacity, int source, int sink) {

        // Initialize residual capacity as total available capacity initially.
        int[][] residualCapacity = new int[capacity.length][capacity[0].length];
        for (int i = 0; i < capacity.length; i++) {
            System.arraycopy(capacity[i], 0, residualCapacity[i], 0, capacity[0].length);
        }

        // This map is used for storing BFS parent.
        Map<Integer, Integer> parent = new HashMap<>();

        int maxFlow = 0;

        // See if augmented path can be found from source to sink.
        while (BFS(residualCapacity, parent, source, sink)) {
            List<Integer> augmentedPath = new ArrayList<>();
            int flow = Integer.MAX_VALUE;
            // Find minimum residual capacity in augmented path.
            // Also add vertices to augmented path list.
            int v = sink;
            while (v != source) {
                augmentedPath.add(v);
                int u = parent.get(v);
                if (flow > residualCapacity[u][v]) {
                    flow = residualCapacity[u][v];
                }
                v = u;
            }
            augmentedPath.add(source);
            Collections.reverse(augmentedPath);

            // Add min capacity to max flow
            maxFlow += flow;

            // Decrease residual capacity by min capacity from u to v in augmented path.
            // and increase residual capacity by min capacity from v to u.
            v = sink;
            while (v != source) {
                int u = parent.get(v);
                residualCapacity[u][v] -= flow;
                residualCapacity[v][u] += flow;
                v = u;
            }
        }
        return maxFlow;
    }
}
