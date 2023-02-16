import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Router {

    private HashMap<Router, Integer> distances;
    private String name;

    public Router(String name) {
        this.distances = new HashMap<>();
        this.name = name;
    }

    public void onInit() throws InterruptedException {
        //TODO: IMPLEMENT ME
        //As soon as the network is online,
        //fill in your initial distance table and broadcast it to your neighbors
        HashSet<Neighbor> neighbors = Network.getNeighbors(this);
        for (Neighbor neighbor : neighbors) {
            distances.put(neighbor.router, neighbor.cost);
        }
        for (Router neighbor : distances.keySet()) {
            Message messageToNeighbor = new Message(this, neighbor, distances);
            Network.sendDistanceMessage(messageToNeighbor);
        }
    }

    public void onDistanceMessage(Message message) throws InterruptedException {
        //update your distance table and broadcast it to your neighbors if it changed
        boolean isChanged = false;
        for (Router neighbor : message.distances.keySet()) {
            if (!neighbor.equals(this)) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, (message.distances.get(neighbor) + distances.get(message.sender)));
                    isChanged = true;
                } else if (distances.get(neighbor) > (distances.get(message.sender) + message.distances.get(neighbor))) {
                    distances.put(neighbor, (distances.get(message.sender) + message.distances.get(neighbor)));
                    isChanged = true;
                }
            }
        }
        if (isChanged) {
            HashSet<Neighbor> neighbors = Network.getNeighbors(this);
            for (Neighbor neighbor : neighbors) {
                Message updatedMessage = new Message(this, neighbor.router, distances);
                Network.sendDistanceMessage(updatedMessage);

            }
        }
    }


    public void dumpDistanceTable() {
        //System.out.println("router: " + this);
        for (Router r : distances.keySet()) {
            //System.out.println("\t" + r + "\t" + distances.get(r));
        }
    }

    @Override
    public String toString() {
        return "Router: " + name;
    }
}