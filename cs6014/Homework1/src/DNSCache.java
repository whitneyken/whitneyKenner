import java.util.HashMap;

public class DNSCache {
    static private HashMap<DNSQuestion, DNSRecord> cachedData = new HashMap<>();


    //this method checks if the cahce contains a specific question/record combo and returns a boolean
    public static boolean hasRecord(DNSQuestion question) {
        if (DNSCache.cachedData.containsKey(question)) {
            if (DNSCache.cachedData.get(question).isExpired()) {
                DNSCache.cachedData.remove(question);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //this method takes in a question and returns the corresponding DNSRecord from cache
    public static DNSRecord getRecord(DNSQuestion question) {
        if (hasRecord(question)) {
            return DNSCache.cachedData.get(question);
        } else {
            return null;
        }
    }

    //this inserts a new record into the cache
    public static void insert(DNSQuestion question, DNSRecord record) {
        cachedData.put(question, record);
    }

}
