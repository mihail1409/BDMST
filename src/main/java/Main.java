import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;


public class Main {
     public Map<Integer, Pair<Integer,Integer>> coordsByVertex = new HashMap<>();
     public Map<Integer, Pair<Integer,Integer>> usedCoordsByVertex = new HashMap<>();
     public Map<Integer, Pair<Integer, Integer>> notUsedCoordsByVertex = new HashMap<>();
    public HashMap<Pair<Integer, Integer>, Integer> weightByEdge = new HashMap<>();
    public LinkedList<Pair<Integer, Integer>> edges = new LinkedList<>();
    public Map<Integer, Integer> totalWeightByVertex = new HashMap<>();
     public long totalWeight = 0;
     public int longestEdge = 0;
     public static int TOTAL_COUNT_OF_VERTEX = 64;
     public static int DIAMETER = 64/16;
     public boolean FOR_TEST = false;

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public Map<Integer, Integer> getTotalWeightByVertex() {
        return totalWeightByVertex;
    }

    public Map<Integer, Pair<Integer, Integer>> getNotUsedCoordsByVertex() {
        return notUsedCoordsByVertex;
    }

    public Map<Integer, Pair<Integer, Integer>> getUsedCoordsByVertex() {
        return usedCoordsByVertex;
    }

    public Map<Pair<Integer, Integer>, Integer> getWeightByEdge() {
        return weightByEdge;
    }

    public void readFromFile() throws IOException {
//         int totalVertex = 64;
//         int diametr = totalVertex/16;
         List<String> list = new ArrayList<>();
         BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Taxicab_2048.txt"));
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
        //System.out.println("asd " +coordsByVertex);
         for (var entry : coordsByVertex.entrySet()) {
             totalWeightByVertex.put(entry.getKey(), 0);
//             if ((entry.getKey() & 1) == 0) {
//                 notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());
//             } else {
//                 usedCoordsByVertex.put(entry.getKey(), entry.getValue());
//             }
             if (entry.getKey() <= coordsByVertex.size()/2) {
                 notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());

             } else {
                 usedCoordsByVertex.put(entry.getKey(), entry.getValue());
             }
//             if (entry.getKey() <= coordsByVertex.size()/2) {
//                 notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());
//
//             } else {
//                 usedCoordsByVertex.put(entry.getKey(), entry.getValue());
//             }
            // notUsedCoordsByVertex.put(entry.getKey(), entry.getValue());
         }
//        var test = notUsedCoordsByVertex.get(33);
//        notUsedCoordsByVertex.remove(33);
//        usedCoordsByVertex.put(33, test);
       //  notUsedCoordsByVertex = coordsByVertex;
     }

    public void getTree() throws IOException {
         readFromFile();
//         int del = 1;
//         usedCoordsByVertex.put(del, coordsByVertex.get(del));
//         notUsedCoordsByVertex.remove(del);
        //int point = 1;
        for (var entry : notUsedCoordsByVertex.entrySet()) {
            createBiclickGraph(entry.getKey(), entry.getValue());
        }
        for (int i =0; i<33; i++) {
            balanceGraph();
        }
        //balanceGraph();

//        var sortedMapTotalWeightByVertex =
//                totalWeightByVertex.entrySet().stream()
//                        .sorted(Map.Entry.comparingByValue())
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                                (e1, e2) -> e1, LinkedHashMap::new));
//        List<Integer> notUsedVertexes = new ArrayList<>();
//        List<Integer> usedVertexes = new ArrayList<>();
//
//        for (var entry : sortedMapTotalWeightByVertex.entrySet()) {
//            if (usedCoordsByVertex.containsKey(entry.getKey())) {
//                if (notUsedVertexes.size() < 5) {
//                    notUsedVertexes.add(entry.getKey());
//                }
//            } else {
//                if (usedVertexes.size() < 5) {
//                    usedVertexes.add(entry.getKey());
//                }
//            }
//        }
//        System.out.println("notUsed" + notUsedVertexes);
//        System.out.println("used" + usedVertexes);
//        for (var vertex : notUsedVertexes) {
//            usedCoordsByVertex.remove(vertex);
//            notUsedCoordsByVertex.put(vertex, coordsByVertex.get(vertex));
//        }
//
//        for (var vertex : usedVertexes) {
//            notUsedCoordsByVertex.remove(vertex);
//            usedCoordsByVertex.put(vertex, coordsByVertex.get(vertex));
//        }
//        totalWeight = 0;
//        edges= new LinkedList<>();
//        for (var entry : notUsedCoordsByVertex.entrySet()) {
//            createBiclickGraph(entry.getKey(), entry.getValue());
//        }
//        for (var entry : coordsByVertex.entrySet()) {
//            createCompleteGraph(entry.getKey(), entry.getValue());
//        }
//        completeGraphToDiamondFreeGraph();
//       endAlgorithm(point, coordsByVertex.get(point));
//         for (var entry : coordsByVertex.entrySet()) {
//          //  if (!usedCoordsByVertex.containsKey(entry.getKey())) {
//                //int vertex = entry.getKey();
//                endAlgorithm(entry.getKey(), entry.getValue());
//           // }
//        }
    }
    private void balanceGraph() {
        var sortedMapTotalWeightByVertex =
                totalWeightByVertex.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        List<Integer> notUsedVertexes = new ArrayList<>();
        List<Integer> usedVertexes = new ArrayList<>();

        for (var entry : sortedMapTotalWeightByVertex.entrySet()) {
            if (usedCoordsByVertex.containsKey(entry.getKey())) {
                if (notUsedVertexes.size() < 450) {
                    notUsedVertexes.add(entry.getKey());
                }
            } else {
                if (usedVertexes.size() < 450) {
                    usedVertexes.add(entry.getKey());
                }
            }
        }
//        System.out.println("notUsed" + notUsedVertexes);
//        System.out.println("used" + usedVertexes);
        for (var vertex : notUsedVertexes) {
            usedCoordsByVertex.remove(vertex);
            notUsedCoordsByVertex.put(vertex, coordsByVertex.get(vertex));
        }

        for (var vertex : usedVertexes) {
            notUsedCoordsByVertex.remove(vertex);
            usedCoordsByVertex.put(vertex, coordsByVertex.get(vertex));
        }
        totalWeight = 0;
        edges= new LinkedList<>();
        for (var entry : notUsedCoordsByVertex.entrySet()) {
            createBiclickGraph(entry.getKey(), entry.getValue());
        }
    }

    private void createBiclickGraph(int vertex, Pair<Integer, Integer> coordsVertex) {
        if (usedCoordsByVertex.containsKey(vertex)) {
            return;
        }
        //totalWeightByVertex.put(vertex, 0);
        for (var entry : usedCoordsByVertex.entrySet()) {
            var tempWeight = totalWeightByVertex.get(vertex);
            var tempWeightForEntryKey = totalWeightByVertex.get(entry.getKey());

            var notUsedVertex = entry.getValue();
            var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                    + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
            totalWeightByVertex.replace(vertex, tempWeight+res);
            totalWeightByVertex.replace(entry.getKey(), tempWeightForEntryKey+res);
            totalWeight += res;
            edges.add(Pair.of(vertex, entry.getKey()));
        }

    }



    private void createCompleteGraph(int vertex, Pair<Integer, Integer> coordsVertex) {
        if (usedCoordsByVertex.containsKey(vertex)) {
            return;
        }
        usedCoordsByVertex.put(vertex, coordsVertex);
        notUsedCoordsByVertex.remove(vertex);
        for (var entry : notUsedCoordsByVertex.entrySet()) {
            var notUsedVertex = entry.getValue();
            var res = Math.abs(coordsVertex.getLeft() - notUsedVertex.getLeft())
                    + Math.abs(coordsVertex.getRight() - notUsedVertex.getRight());
            totalWeight += res;
            edges.add(Pair.of(vertex, entry.getKey()));
            weightByEdge.put(Pair.of(vertex,entry.getKey()), res);
        }
    }

    private void completeGraphToDiamondFreeGraph() {
        int totalCount = TOTAL_COUNT_OF_VERTEX;
        // итерация по всем вершинам
        for (int i = 1; i < totalCount+1; i++) {
            System.out.println(i + "iteration");
            //int vertex1 = vertices.get(i);

            // итерация по всем вершинам, следующим за первой вершиной
            for (int j = i + 1; j < totalCount+1; j++) {
                //int vertex2 = vertices.get(j);

                // итерация по всем вершинам, следующим за второй вершиной
                for (int k = j + 1; k < totalCount+1; k++) {
                    //int vertex3 = vertices.get(k);

                    // итерация по всем вершинам, следующим за третьей вершиной
                    for (int l = k + 1; l < totalCount+1; l++) {
                        checkVertexDegreeForBlock(i,j,k,l);
                        // i j k l

                        //int vertex4 = vertices.get(l);


                        // Добавление четверки вершин в список
//                        List<Integer> quartet = new ArrayList<>();
//                        quartet.add(vertex1);
//                        quartet.add(vertex2);
//                        quartet.add(vertex3);
//                        quartet.add(vertex4);
//                        quartets.add(quartet);
                    }
                }
            }
        }
    }
//                    var filterEdgesForSecondVert = edges.stream()
//                .filter(edge -> (edge.getLeft() == firstVert || edge.getRight() == firstVert) &&
//                        ((edge.getRight() == secondVert || edge.getRight() == thirdVert || edge.getRight() == fourthVert) ||
//                                (edge.getLeft() == secondVert || edge.getLeft() == thirdVert || edge.getLeft() == fourthVert) ))
//                .collect(Collectors.toList());
    private void checkVertexDegreeForBlock(int firstVert, int secondVert, int thirdVert, int fourthVert) {
        List<Pair<Integer, Integer>> filterEdgesForFirstVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForSecondVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForThirdVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForFourthVert = new ArrayList<>();
        for (var edge : edges) {
            if (checkVertexes(edge,firstVert,secondVert,thirdVert,fourthVert)) {
                filterEdgesForFirstVert.add(edge);
            }
            if (checkVertexes(edge,secondVert,firstVert,thirdVert,fourthVert)) {
                filterEdgesForSecondVert.add(edge);
            }
            if (checkVertexes(edge,thirdVert,secondVert,firstVert,fourthVert)) {
                filterEdgesForThirdVert.add(edge);
            }
            if (checkVertexes(edge,fourthVert,secondVert,thirdVert,firstVert)) {
                filterEdgesForFourthVert.add(edge);
            }
        }
        int counterOfDegreeVerts = 0;
        counterOfDegreeVerts += checkVertexForDegree(filterEdgesForFirstVert);
        counterOfDegreeVerts += checkVertexForDegree(filterEdgesForSecondVert);
        counterOfDegreeVerts += checkVertexForDegree(filterEdgesForThirdVert);
        counterOfDegreeVerts += checkVertexForDegree(filterEdgesForFourthVert);
        if (counterOfDegreeVerts >= 3) {
            return;
        }
        if (counterOfDegreeVerts == 2 || counterOfDegreeVerts == 1) {
            int minWeight = 999999;
            Pair<Integer, Integer> edgeForRemove = null;
            for (var edge : filterEdgesForFirstVert) {
                var weightEdge = weightByEdge.get(edge);
                if (minWeight > weightEdge) {
                    minWeight = weightEdge;
                    edgeForRemove = edge;
                }
            }

            for (var edge : filterEdgesForSecondVert) {
                var weightEdge = weightByEdge.get(edge);
                if (minWeight > weightEdge) {
                    minWeight = weightEdge;
                    edgeForRemove = edge;
                }
            }
            for (var edge : filterEdgesForThirdVert) {
                var weightEdge = weightByEdge.get(edge);
                if (minWeight > weightEdge) {
                    minWeight = weightEdge;
                    edgeForRemove = edge;
                }
            }
            totalWeight -= minWeight;
            edges.remove(edgeForRemove);
            return;
        }

        // если полный граф
        if (counterOfDegreeVerts == 0) {
            ArrayList<Integer> minWeights = new ArrayList<>();
            minWeights.add(999999);
            minWeights.add(999998);
            //int minWeight = 999999;
            Pair<Integer, Integer> edgeForRemove = null;
            Pair<Integer, Integer> firstEdgeForRemove = null;
            Pair<Integer, Integer> secondEdgeForRemove = null;

            for (var edge : filterEdgesForFirstVert) {
                var weightEdge = weightByEdge.get(edge);
                if (Collections.max(minWeights) == 999999) {
                    firstEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                if (Collections.max(minWeights) == 999998) {
                    secondEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                int minWeight = Collections.max(minWeights);
                if (minWeight > weightEdge) {
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    if (weightByEdge.containsKey(firstEdgeForRemove)) {
                        if (weightByEdge.get(firstEdgeForRemove) == minWeight) {
                            firstEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(firstEdgeForRemove)) {
                        firstEdgeForRemove = edge;
                    }
                    else if (weightByEdge.containsKey(secondEdgeForRemove)) {
                        if (weightByEdge.get(secondEdgeForRemove) == minWeight) {
                            secondEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(secondEdgeForRemove)) {
                        secondEdgeForRemove = edge;
                    }
                    //minWeight = weightEdge;
                    //edgeForRemove = edge;
                }
            }
            for (var edge : filterEdgesForSecondVert) {
                var weightEdge = weightByEdge.get(edge);
                if (Collections.max(minWeights) == 999999) {
                    firstEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                if (Collections.max(minWeights) == 999998) {
                    secondEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                int minWeight = Collections.max(minWeights);
                if (minWeight > weightEdge) {
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    if (weightByEdge.containsKey(firstEdgeForRemove)) {
                        if (weightByEdge.get(firstEdgeForRemove) == minWeight) {
                            firstEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(firstEdgeForRemove)) {
                        firstEdgeForRemove = edge;
                    }
                    else if (weightByEdge.containsKey(secondEdgeForRemove)) {
                        if (weightByEdge.get(secondEdgeForRemove) == minWeight) {
                            secondEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(secondEdgeForRemove)) {
                        secondEdgeForRemove = edge;
                    }
                    //minWeight = weightEdge;
                    //edgeForRemove = edge;
                }
            }
            for (var edge : filterEdgesForThirdVert) {
                var weightEdge = weightByEdge.get(edge);
                if (Collections.max(minWeights) == 999999) {
                    firstEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                if (Collections.max(minWeights) == 999998) {
                    secondEdgeForRemove = edge;
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    continue;
                }
                int minWeight = Collections.max(minWeights);
                if (minWeight > weightEdge) {
                    minWeights.remove(Collections.max(minWeights));
                    minWeights.add(weightEdge);
                    if (weightByEdge.containsKey(firstEdgeForRemove)) {
                        if (weightByEdge.get(firstEdgeForRemove) == minWeight) {
                            firstEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(firstEdgeForRemove)) {
                        firstEdgeForRemove = edge;
                    }
                    else if (weightByEdge.containsKey(secondEdgeForRemove)) {
                        if (weightByEdge.get(secondEdgeForRemove) == minWeight) {
                            secondEdgeForRemove = edge;
                        }
                    }
                    else if (!weightByEdge.containsKey(secondEdgeForRemove)) {
                        secondEdgeForRemove = edge;
                    }
                    //minWeight = weightEdge;
                    //edgeForRemove = edge;
                }
            }
            totalWeight -= minWeights.get(0);
            totalWeight -= minWeights.get(1);
            edges.remove(firstEdgeForRemove);
            edges.remove(secondEdgeForRemove);
        }

//        if (filterEdgesForFirstVert.size() != 3 || filterEdgesForSecondVert.size() != 3
//                || filterEdgesForThirdVert.size() != 3 || filterEdgesForFourthVert.size() != 3) {
//            return;
//        }

//        if (filterEdgesForFirstVert.size() == 3 && filterEdgesForSecondVert.size() == 3
//                && filterEdgesForThirdVert.size() == 3 && filterEdgesForFourthVert.size() == 3) {
//            FOR_TEST = true;
//        }
    }

    private boolean checkVertexes(Pair<Integer, Integer> edge, int firstVert,int secondVert, int thirdVert, int fourthVert) {
        return (edge.getLeft() == firstVert || edge.getRight() == firstVert) &&
                ((edge.getRight() == secondVert || edge.getRight() == thirdVert || edge.getRight() == fourthVert) ||
                        (edge.getLeft() == secondVert || edge.getLeft() == thirdVert || edge.getLeft() == fourthVert));
    }

    private int checkVertexForDegree(List<Pair<Integer, Integer>> checkVertexPairs) {
        if (checkVertexPairs.size() != 3) {
            return 1;
        } else {
            return 0;
        }
    }

    public void completeGraphToDiamondFreeGraphTest() {
        int totalCount = 64;
        // итерация по всем вершинам
        for (int i = 1; i < totalCount+1; i++) {
            //int vertex1 = vertices.get(i);

            // итерация по всем вершинам, следующим за первой вершиной
            for (int j = i + 1; j < totalCount+1; j++) {
                //int vertex2 = vertices.get(j);

                // итерация по всем вершинам, следующим за второй вершиной
                for (int k = j + 1; k < totalCount+1; k++) {
                    //int vertex3 = vertices.get(k);

                    // итерация по всем вершинам, следующим за третьей вершиной
                    for (int l = k + 1; l < totalCount+1; l++) {
                        checkVertexDegreeForBlockTest(i,j,k,l);
                        // i j k l

                        //int vertex4 = vertices.get(l);


                        // Добавление четверки вершин в список
//                        List<Integer> quartet = new ArrayList<>();
//                        quartet.add(vertex1);
//                        quartet.add(vertex2);
//                        quartet.add(vertex3);
//                        quartet.add(vertex4);
//                        quartets.add(quartet);
                    }
                }
            }
        }
    }

    private void checkVertexDegreeForBlockTest(int firstVert, int secondVert, int thirdVert, int fourthVert) {
        List<Pair<Integer, Integer>> filterEdgesForFirstVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForSecondVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForThirdVert = new ArrayList<>();
        List<Pair<Integer, Integer>> filterEdgesForFourthVert = new ArrayList<>();
        for (var edge : edges) {
            if (checkVertexes(edge,firstVert,secondVert,thirdVert,fourthVert)) {
                filterEdgesForFirstVert.add(edge);
            }
            if (checkVertexes(edge,secondVert,firstVert,thirdVert,fourthVert)) {
                filterEdgesForSecondVert.add(edge);
            }
            if (checkVertexes(edge,thirdVert,secondVert,firstVert,fourthVert)) {
                filterEdgesForThirdVert.add(edge);
            }
            if (checkVertexes(edge,fourthVert,secondVert,thirdVert,firstVert)) {
                filterEdgesForFourthVert.add(edge);
            }
        }
        if (filterEdgesForFirstVert.size() == 3 && filterEdgesForSecondVert.size() == 3
                && filterEdgesForThirdVert.size() == 3 && filterEdgesForFourthVert.size() == 3) {
            FOR_TEST = true;
        }
        if (filterEdgesForFirstVert.size() != 3 || filterEdgesForSecondVert.size() != 3
                || filterEdgesForThirdVert.size() != 3 || filterEdgesForFourthVert.size() != 3) {
            return;
        }

        int minWeight = 999999;
        Pair<Integer, Integer> edgeForRemove = null;
        for (var edge : filterEdgesForFirstVert) {
            var weightEdge = weightByEdge.get(edge);
            if (minWeight > weightEdge) {
                minWeight = weightEdge;
                edgeForRemove = edge;
            }
        }
        for (var edge : filterEdgesForSecondVert) {
            var weightEdge = weightByEdge.get(edge);
            if (minWeight > weightEdge) {
                minWeight = weightEdge;
                edgeForRemove = edge;
            }
        }
        for (var edge : filterEdgesForThirdVert) {
            var weightEdge = weightByEdge.get(edge);
            if (minWeight > weightEdge) {
                minWeight = weightEdge;
                edgeForRemove = edge;
            }
        }
        totalWeight -= minWeight;
        edges.remove(edgeForRemove);
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

