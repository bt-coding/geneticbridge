import java.util.*;
import java.io.*;

public class FileProcess {
    public static int exportFile(String fileName, Bridge b) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(new File(fileName + ".gbr")));
            
            ArrayList<Node> nodes = b.getNodes();
            ArrayList<Node> lockednodes = b.getNodesLocked();
            ArrayList<ArrayList<Node>> members = b.getMembers();
            ArrayList<ArrayList<Node>> lockedmembers = b.getMembersLocked();
            ArrayList<Force> forces = b.getForces();
            
            w.write("GNBRIDGE_BFILE_START\n");
            w.write("\n");
            w.write("NLNODE\n");
            for(Node n : nodes) {
                String fline = n.getID() + " " + n.getX() + " " + n.getY();
                w.write(fline + "\n");
            }
            w.write("\n");
            w.write("LNODE\n");
            for(Node n : lockednodes) {
                String fline = n.getID() + " " + n.getX() + " " + n.getY();
                w.write(fline + "\n");
            }
            w.write("\n");
            w.write("NLMEMBER\n");
            for(ArrayList<Node> m : members) {
                String fline = m.get(0).getID() + " " + m.get(1).getID();
                w.write(fline + "\n");
            }
            w.write("\n");
            w.write("LMEMBER\n");
            for(ArrayList<Node> m : lockedmembers) {
                String fline = m.get(0).getID() + " " + m.get(1).getID();
                w.write(fline + "\n");
            }
            w.write("\n");
            w.write("FORCE\n");
            for(Force f : forces) {
                //
            }
            w.write("GNBRIDGE_BFILE_END");
            w.close();
        } catch (Exception e) {
            System.out.println("An error has occured while exporting a file");
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}