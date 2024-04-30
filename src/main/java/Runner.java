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
//        FileWriter writer = new FileWriter("Aleksandrov_512_3.txt");
//        writer.write("c Вес дерева = " + mainOgj.getTotalWeight() + " диаметр = " + System.getProperty("line.separator"));
//        writer.write("p edge = " + mainOgj.getUsedCoordsByVertex().size() + " " + mainOgj.getEdges().size() + System.getProperty("line.separator") );
//
//        for(var edge : edges) {
//            String left = edge.getLeft().toString();
//            String right = edge.getRight().toString();
//            writer.write("e " + left + " " + right + System.getProperty("line.separator"));
//        }
//        writer.close();
    }
}
