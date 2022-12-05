package assignment06;

public class BadHashFunctor implements HashFunctor{
    @Override
    public int hash(String item) {
        return item.charAt(0);
    }
}
