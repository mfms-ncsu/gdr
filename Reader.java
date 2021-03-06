import java.util.*;
import java.io.*;

public class Reader {
    private Graph graph;
    private FileInputStream fstream;
    private DataInputStream in;
    private BufferedReader br;

    public Reader () {
        graph = new Graph();
    }
    
    public void parseLine(String line) {
        String[] fields = line.split(" ");
        if (fields[0].equals("g")) {
            graph.setSize(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
        } else if (fields[0].equals("n")) {
            graph.addNode(new Node(fields[1], fields[2], fields[3]));
        } else if (fields[0].equals("e")) {
            graph.addEdge(fields[1], fields[2]);
        }
    }

    public Graph ReadFile(String filename) {
        String line;
        try {
            fstream = new FileInputStream(filename);
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                parseLine(line);
            }

            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return graph;
    }

    public Graph ReadStdin() {
        String line;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((line = br.readLine()) != null) {
                parseLine(line);
                if (graph.isFull())
                    return graph;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return graph;
    }

    public Graph getGraph() { return graph; }
}