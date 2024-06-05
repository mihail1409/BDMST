import org.w3c.dom.ls.LSOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String[] args) throws IOException {
        Main mainOgj = new Main();
        mainOgj.getTree();
        System.out.println(mainOgj.getEdges());
        System.out.println(mainOgj.getEdges().size());
        var edges = mainOgj.getEdges();
        //System.out.println(mainOgj.getUsedCoordsByVertex());
       // System.out.println(mainOgj.getNotUsedCoordsByVertex());
        System.out.println(mainOgj.getTotalWeight());
        System.out.println(mainOgj.weightByEdge);
        System.out.println(mainOgj.weightByEdge.size());
        System.out.println(mainOgj.getEdges().size());
        System.out.println(mainOgj.getTotalWeight());
        System.out.println(mainOgj.getTotalWeightByVertex());
                var sortedMap =
                        mainOgj.getTotalWeightByVertex().entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sortedMap);
        System.out.println(mainOgj.getNotUsedCoordsByVertex());
        System.out.println(mainOgj.getUsedCoordsByVertex());
        System.out.println(mainOgj.getTotalWeightByVertex());
        System.out.println(mainOgj.getEdges().size());
//        System.out.println(mainOgj.getNotUsedCoordsByVertex());
//        System.out.println(mainOgj.getUsedCoordsByVertex());
//        System.out.println(mainOgj.getUsedCoordsByVertex().size());
//        System.out.println(mainOgj.getNotUsedCoordsByVertex().size());
//        int sum = mainOgj.weightByEdge.values().stream().mapToInt(a-> a)
//                .sum();
//        System.out.println("sum" + sum);
//        var sortedMap =
//                mainOgj.weightByEdge.entrySet().stream()
//                        .sorted(Map.Entry.comparingByValue())
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                                (e1, e2) -> e1, LinkedHashMap::new));
//        System.out.println(sortedMap);
       // System.out.println(mainOgj.getEdges());
//        System.out.println(mainOgj.FOR_TEST);
//        mainOgj.completeGraphToDiamondFreeGraphTest();
//        System.out.println(mainOgj.FOR_TEST);
//        System.out.println(mainOgj.getEdges().size());
//        System.out.println(mainOgj.getTotalWeight());
//        mainOgj.checkVertexDegreeForBlock(3,2,5,33);
//        System.out.println(mainOgj.longestEdge);

        FileWriter writer = new FileWriter("Aleksandrov_2048_7.txt");
        writer.write("c Вес подграфа = " + mainOgj.getTotalWeight() + System.getProperty("line.separator"));
        writer.write("p edge " + mainOgj.getUsedCoordsByVertex().size() + " " + mainOgj.getEdges().size() + System.getProperty("line.separator") );
        //writer.write("p edge = " + mainOgj.getUsedCoordsByVertex().size() + " " + mainOgj.getEdges().size() + System.getProperty("line.separator") );

        for(var edge : edges) {
            String left = edge.getLeft().toString();
            String right = edge.getRight().toString();
            writer.write("e " + left + " " + right + System.getProperty("line.separator"));
        }
        writer.close();

//        int bestRes = 0;
        int total = 0;
        for (var pair : mainOgj.getEdges()) {
            var firstVertex = mainOgj.coordsByVertex.get(pair.getLeft());
            var secondVertex = mainOgj.coordsByVertex.get(pair.getRight());
            var res = Math.abs(firstVertex.getLeft() - secondVertex.getLeft())
                    + Math.abs(firstVertex.getRight() - secondVertex.getRight());
            total += res;
        }
        System.out.println(total);
//            if (res > bestRes) {
//                bestRes = res;
//            }
//        }
//        System.out.println(mainOgj.longestEdge + " самое длинное ребро " + bestRes + "total weight" +total);
    }
}
