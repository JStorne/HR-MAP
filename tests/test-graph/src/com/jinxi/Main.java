package com.jinxi;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        UndirectedGraph<String, DefaultEdge> graph = createStringGraph();
        DijkstraShortestPath path = new DijkstraShortestPath<>(graph, "v1", "v3");
        System.out.println(graph.toString());
        System.out.println(path.getPath().toString());
    }

    /***
     ** Create a toy graph based on String objects.
     **
     ** @return a graph based on String objects.
     */
    private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
                new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }
}
