import java.util.*;
public class FileExportTest {
    public static void main(String[] args) {
        ArrayList<Force> forces = new ArrayList<Force>();
        Bridge bridge = new Bridge(100,new double[]{-800,-400,800,400},forces,new ArrayList<Node>());
        FileProcess.exportFile("TESTEXPORT", bridge);
    }
}