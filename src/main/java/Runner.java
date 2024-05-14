import java.io.FileWriter;
import java.io.IOException;

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
        System.out.println(mainOgj.longestEdge);
//
//        FileWriter writer = new FileWriter("Aleksandrov_64_1.txt");
//        writer.write("c Вес кубического подграфа = " + mainOgj.getTotalWeight() + " самое длинное ребро = " + mainOgj.longestEdge + "," + System.getProperty("line.separator"));
//        writer.write("p edge = " + mainOgj.getUsedCoordsByVertex().size() + " " + mainOgj.getEdges().size() + System.getProperty("line.separator") );
//
//        for(var edge : edges) {
//            String left = edge.getLeft().toString();
//            String right = edge.getRight().toString();
//            writer.write("e " + left + " " + right + System.getProperty("line.separator"));
//        }
//        writer.close();
//
//        int bestRes = 0;
//        int total = 0;
//        for (var pair : mainOgj.getEdges()) {
//            var firstVertex = mainOgj.coordsByVertex.get(pair.getLeft());
//            var secondVertex = mainOgj.coordsByVertex.get(pair.getRight());
//            var res = Math.abs(firstVertex.getLeft() - secondVertex.getLeft())
//                    + Math.abs(firstVertex.getRight() - secondVertex.getRight());
//            total +=res;
//            if (res > bestRes) {
//                bestRes = res;
//            }
//        }
//        System.out.println(mainOgj.longestEdge + " самое длинное ребро " + bestRes + "total weight" +total);
    }
}
