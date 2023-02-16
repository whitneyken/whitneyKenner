public class Neighbor {
    Router router;
    int cost;

    public Neighbor(Router router, int cost) {
        this.router = router;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "to " + router + " cost: " + cost;
    }
}