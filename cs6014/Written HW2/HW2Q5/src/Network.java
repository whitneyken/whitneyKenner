import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Network {

    private static LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();
    private static HashMap<Router, HashSet<Neighbor>> links = new HashMap<>();

    private static int messageCount = 0;

    private static void addLink(Router a, Router b, int cost){
        Neighbor ab = new Neighbor(b, cost);
        Neighbor ba = new Neighbor(a, cost);

        if(!links.containsKey(a)){
            links.put(a, new HashSet<>());
        }
        if(!links.containsKey(b)){
            links.put(b, new HashSet<>());
        }
        links.get(a).add(ab);
        links.get(b).add(ba);

    }

    public static void sendDistanceMessage(Message message) throws InterruptedException {
        messageQueue.put(message);

    }



    public static void runBellmanFord() throws InterruptedException {

        while(!messageQueue.isEmpty()){
            //System.out.println("processing message");
            ++messageCount;
            Message message = messageQueue.take();
//            message.dump();
            message.receiver.onDistanceMessage(message);
        }
    }


    public static void startup() throws InterruptedException {
        for(Router r : getRouters()){
            r.onInit();
        }
    }

    public static HashSet<Neighbor> getNeighbors(Router r){
        return links.get(r);
    }

    public static Set<Router> getRouters(){
        return links.keySet();
    }

    private static void reset(){
        messageQueue.clear();
        links.clear();
        messageCount = 0;
    }

    public static int getMessageCount(){
        return messageCount;
    }

    public static void dump(){
        for(Router r : links.keySet()){
            //System.out.println("router " + r);
            for (Neighbor n : links.get(r)){
                System.out.println("\t" + n);
            }
        }
    }


    //simple network where path from b to c is faster through a than direct route
    public static void makeSimpleNetwork(){
        reset();
        Router a = new Router("a");
        Router b = new Router("b");
        Router c = new Router("c");

        addLink(a, b, 1);
        addLink(b, c, 3);
        addLink(a, c, 1);

    }

    //randomly add edges until everything is connected
    public static void makeProbablisticNetwork(int size){
        reset();
        ArrayList<Router> routers = new ArrayList<>(size);
        HashMap<Integer, HashSet<Integer>> connectedComponents = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> linksSoFar = new HashMap<>();
        int nComponents = size;
        for(int i = 0; i < size; ++i){
            routers.add(new Router(Integer.toString(i)));
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            connectedComponents.put(i, set);
            linksSoFar.put(i, new HashSet<>());
        }

        Random r = new Random();
        while(nComponents > 1){
            int a = r.nextInt(size);
            int b = r.nextInt(size);

            if(a == b){ continue; } //link to itself
            if(linksSoFar.get(a).contains(b)){ continue; } //duplicate link

            linksSoFar.get(a).add(b); //record links
            linksSoFar.get(b).add(a);

            Network.addLink(routers.get(a), routers.get(b), r.nextInt(10));
            if(connectedComponents.get(a) != connectedComponents.get(b)){
                HashSet<Integer> oldSet = connectedComponents.get(b);
                HashSet<Integer> newSet = connectedComponents.get(a);
                for(int i : oldSet){
                    newSet.add(i);
                    connectedComponents.put(i, newSet);
                }
                --nComponents;
            }
        }

    }

}