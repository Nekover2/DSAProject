package BusSystem;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;          // elements
    private int n;             // number of elements

    // set inititial size of heap to hold size elements
    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) (new Comparable[capacity]);
        n = 0;
    }

    public Key[] getPq() {
        return pq;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key delMax() {
        return pq[--n];
    }

    // có vấn đề về return type. đã xửa từ void sang Key
    // để phù hợp với hàm addPassenger trong bus
    public Key insert(Key key) {
        int i = n - 1;
        while (i >= 0 && less(key, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = key;
        n++;
        return key;
    }


    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
}
