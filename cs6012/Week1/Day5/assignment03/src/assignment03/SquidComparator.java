package assignment03;

import java.util.Comparator;

public class SquidComparator implements Comparator<Squid> {



    @Override
    public int compare(Squid o1, Squid o2) {
        return ((o1.IQ_ + o1.numTentacles_) - (o2.numTentacles_ + o2.IQ_));
    }
}
