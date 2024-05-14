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
     public int longestEdge = 0;
     public static int DIAMETER = 64/16;

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
         BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Taxicab_4096.txt"));
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
        System.out.println("asd " +coordsByVertex);
         for (var entry : coordsByVertex.entrySet()) {
             notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());
         }
       //  notUsedCoordsByVertex = coordsByVertex;
     }

    public void getTree() throws IOException {
         readFromFile();
//         int del = 1;
//         usedCoordsByVertex.put(del, coordsByVertex.get(del));
//         notUsedCoordsByVertex.remove(del);
        int point = 1;
       endAlgorithm(point, coordsByVertex.get(point));
         for (var entry : coordsByVertex.entrySet()) {
          //  if (!usedCoordsByVertex.containsKey(entry.getKey())) {
                //int vertex = entry.getKey();
                endAlgorithm(entry.getKey(), entry.getValue());
           // }
        }
    }

    private void  test(int vertex, Pair<Integer, Integer> coordsVertex) {
        ArrayList<Integer> potentialBestVertexs = new ArrayList<>();
        potentialBestVertexs.add(5);
        potentialBestVertexs.add(3);
        potentialBestVertexs.add(10);
        System.out.println("AA" + potentialBestVertexs);
        potentialBestVertexs.remove(Collections.max(potentialBestVertexs));
        System.out.println("KO"+potentialBestVertexs);
    }


    private void endAlgorithm(int vertex, Pair<Integer, Integer> coordsVertex) {
        if (usedCoordsByVertex.containsKey(vertex)) {
            return;
        }
        ArrayList<Integer> potentialBestVertexs = new ArrayList<>();
        Map<Integer, Integer> resByPotentialBestVertex = new HashMap<>();
        // System.out.println("AA" + Collections.min(potentialBestVertexs) + "ad" + Collections.max(potentialBestVertexs));
        int dist = 99999;
        int resVertex = vertex;
        Pair<Integer, Integer> resCoords = coordsVertex;
        notUsedCoordsByVertex.remove(vertex);
        for (var entry : notUsedCoordsByVertex.entrySet()) {
            int neededDegree = getVertexDegree(entry.getKey());
//            if (vertex == 56) {
//                System.out.println("teleport");
//            }
//            if (entry.getKey() == 57) {
//                System.out.println(vertex);
//                System.out.println("rly" +neededDegree);
//            }
            int sourceDegree = getVertexDegree(vertex);
            //System.out.println(neededDegree);
            var notUsedVertex = entry.getValue();
            var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                    + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
           // System.out.println("res for " + vertex + " and " + entry.getKey() + " is a " + res);
            //System.out.println("ad " + potentialBestVertexs + " sda " + neededDegree);
            if (neededDegree != 0 && resByPotentialBestVertex.size() < sourceDegree) {
//            if (resByPotentialBestVertex.size() < neededDegree && resByPotentialBestVertex.size() < sourceDegree) {
                resByPotentialBestVertex.put(entry.getKey(), res);
               // potentialBestVertexs.add(entry.getKey());
                //System.out.println("size " +potentialBestVertexs.size());
                //System.out.println();
            } else if (!resByPotentialBestVertex.isEmpty() && Collections.max(resByPotentialBestVertex.values()) > res && neededDegree != 0 && sourceDegree !=0) { //Collections.max(potentialBestVertexs) > res) {
                //System.out.println("size " +resByPotentialBestVertex.size());
              //  if (isNeededVertexDegree(entry.getKey())) {
                //System.out.println("key"+entry.getKey());
                for (var resByVertex : resByPotentialBestVertex.entrySet()) {
                    if (resByVertex.getValue().equals(Collections.max(resByPotentialBestVertex.values()))) {
                        resByPotentialBestVertex.remove(resByVertex.getKey());
                        break;
                    }
                }
                resByPotentialBestVertex.put(entry.getKey(), res);
               // potentialBestVertexs.remove(Collections.max(potentialBestVertexs));
               // potentialBestVertexs.add(entry.getKey());
               // System.out.println("aaa " + potentialBestVertexs);
             //   }

            }
        }
       //System.out.println("kva");
        //System.out.println("pot best" + potentialBestVertexs);
        if (!resByPotentialBestVertex.isEmpty()) {
            if (resByPotentialBestVertex.containsKey(60)) {
                System.out.println("haha" +resByPotentialBestVertex);
            }
            for (var resByVertex : resByPotentialBestVertex.entrySet()) {
               // System.out.println("add");
                var notUsedVertex = notUsedCoordsByVertex.get(resByVertex.getKey());
              //  System.out.println("vert" + notUsedVertex +"asd" + vert);
                var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                        + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
                totalWeight += res;
                edges.add(Pair.of(vertex, resByVertex.getKey()));
                if (longestEdge < res) {
                    longestEdge = res;
                }
            }
        }
        usedCoordsByVertex.put(vertex, coordsVertex);
    }

    private void NEWgetVertexWithMinWeight(int vertex, Pair<Integer, Integer> coordsVertex) {
        if (usedCoordsByVertex.containsKey(vertex)) {
            return;
        }
        ArrayList<Integer> potentialBestVertexs = new ArrayList<>();
        // System.out.println("AA" + Collections.min(potentialBestVertexs) + "ad" + Collections.max(potentialBestVertexs));
        int dist = 99999;
        int resVertex = vertex;
        Pair<Integer, Integer> resCoords = coordsVertex;
        notUsedCoordsByVertex.remove(vertex);
        for (var entry : notUsedCoordsByVertex.entrySet()) {
            var notUsedVertex = entry.getValue();
            var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                    + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
            if (potentialBestVertexs.size() != 3) {
                potentialBestVertexs.add(res);
            } else if (Collections.max(potentialBestVertexs) > res) {
                if (isNeededVertexDegree(entry.getKey())) {
                    potentialBestVertexs.remove(Collections.max(potentialBestVertexs));
                    potentialBestVertexs.add(entry.getKey());
                }

            }

        }

    }

    private int getVertexDegree(int checkVertex) {
        int count = 3;
        if (edges.isEmpty()) {
            return count;
        }
        for (var pair : edges) {
            if (pair.getLeft() == checkVertex || pair.getRight() == checkVertex) {
                count--;
                if (count == 0) {
                    return count;
                }
            }
        }
        return count;
    }

    private boolean isNeededVertexDegree(int checkVertex) {
        if (edges.isEmpty()) {
            return true;
        }
        int count = 0;
        for (var pair : edges) {
            if (pair.getLeft() == checkVertex || pair.getRight() == checkVertex) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }
        else {
            return false;
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

