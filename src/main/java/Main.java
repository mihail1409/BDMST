import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.apache.commons.lang3.tuple.Pair;


public class Main {
     public Map<Integer, Pair<Integer,Integer>> coordsByVertex = new HashMap<>();
     public Map<Integer, Pair<Integer,Integer>> usedCoordsByVertex = new HashMap<>();
     public Map<Integer, Pair<Integer, Integer>> notUsedCoordsByVertex = new HashMap<>();
     public List<Pair<Integer, Integer>> edges = new ArrayList<>();
     public int totalWeight = 0;
     public static int DIAMETER = 128/16;

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public Map<Integer, Pair<Integer, Integer>> getNotUsedCoordsByVertex() {
        return notUsedCoordsByVertex;
    }

    public Map<Integer, Pair<Integer, Integer>> getUsedCoordsByVertex() {
        return usedCoordsByVertex;
    }

    public void readFromFile() throws IOException {
//         int totalVertex = 64;
//         int diametr = totalVertex/16;
         List<String> list = new ArrayList<>();
         BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Taxicab_128.txt"));
         String line;
         while ((line = reader.readLine()) != null) {
             list.add(line);
         }
         reader.close();
         list.remove(0);

         // Map<Integer, Pair<Integer,Integer>> coordsByVertex = new HashMap<>();
         int i = 1;
         for (var el : list) {
             var splitEl = el.split("\t");
             System.out.println(splitEl[0]);
             coordsByVertex.put(i, Pair.of(Integer.parseInt(splitEl[0]), Integer.parseInt(splitEl[1])));
             i++;
         }
         for (var entry : coordsByVertex.entrySet()) {
             notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());
         }
       //  notUsedCoordsByVertex = coordsByVertex;
     }

    public void getTree() throws IOException {
         readFromFile();
         int del = 11;
         usedCoordsByVertex.put(del, coordsByVertex.get(del));
         notUsedCoordsByVertex.remove(del);
         for (var entry : coordsByVertex.entrySet()) {
            if (!usedCoordsByVertex.containsKey(entry.getKey())) {
                //int vertex = entry.getKey();
                getVertexWithMinWeight(entry.getKey(), entry.getValue());
            }
        }
    }

    private void getVertexWithMinWeight(int vertex, Pair<Integer, Integer> coordsVertex) {
         int dist = 99999;
         int resVertex = vertex;
         Pair<Integer, Integer> resCoords = coordsVertex;
        for (var entry : usedCoordsByVertex.entrySet()) {
            var notUsedVertex = entry.getValue();
           // var notUsedVertex =  notUsedCoordsByVertex.get(entry.getKey());
            var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                    + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
           // int diam = calculateDiameter(entry.getKey(), vertex);
            //System.out.println(diam);
            if (res < dist && isEdgeDiametr(entry.getKey(), vertex, DIAMETER-1)) {
                dist = res;
                resVertex = entry.getKey();
                resCoords = entry.getValue();
            }
        }
        totalWeight += dist;
        notUsedCoordsByVertex.remove(vertex);
        usedCoordsByVertex.put(vertex, coordsVertex);
//        notUsedCoordsByVertex.remove(resVertex);
//        usedCoordsByVertex.put(resVertex, resCoords);
        edges.add(Pair.of(resVertex, vertex));
    }

    private boolean isEdgeDiametr(int sourceVertex, int destinationVertex, int neededDiametr) {
        // int diametr = 4;
        int currDiametr = 0;
        boolean flag = true;
//        List<Pair<Integer, Integer>> resEdges = new ArrayList<>(edges);
//        resEdges.add(Pair.of(sourceVertex, destinationVertex));
        // resEdges.stream().anyMatch(pair -> pair.getRight() == d)

        if (edges.isEmpty() || sourceVertex == destinationVertex) { //|| neededDiametr == 0) {
            return true;
        } else {
            for (var pair : edges) {
                if ((pair.getRight() == sourceVertex || pair.getLeft() == sourceVertex)
                && (pair.getRight() != destinationVertex && pair.getLeft() != destinationVertex)) {
                    if (neededDiametr == 0) {
                        return false;
                    }
                    if (pair.getRight() == sourceVertex) {
                         flag = isEdgeDiametr(pair.getLeft(), sourceVertex, neededDiametr-1);
                    }
                    if (pair.getLeft() == sourceVertex) {
                        flag = isEdgeDiametr(pair.getRight(), sourceVertex, neededDiametr-1);
                    }
                    if (!flag){
                        return false;
                    }
                } else {
                    if (neededDiametr > 0 ) {
                        flag = true;
                    }
                }
            }
           // resEdges.get(0).getLeft()  resEdges.get(0).getRight()
        }
        return flag;

    }
}

