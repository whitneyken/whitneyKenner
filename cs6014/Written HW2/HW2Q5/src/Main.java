

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int totalMessageCount = 0;
        int networkSize = 500;
        int iterations = 100;
        //Network.makeSimpleNetwork(); //use this for testing/debugging
        for (int i = 0; i < iterations; i++) {
            Network.makeProbablisticNetwork(networkSize); //use this for the plotting part\

            Network.startup();
            Network.runBellmanFord();
            totalMessageCount += Network.getMessageCount();
        }
        System.out.println("The average message count over 100 iterations is: " + totalMessageCount/100);


        System.out.println("done building tables!");
        for(Router r : Network.getRouters()){
            r.dumpDistanceTable();
        }
        //System.out.println("total messages: " + Network.getMessageCount());


    }
}