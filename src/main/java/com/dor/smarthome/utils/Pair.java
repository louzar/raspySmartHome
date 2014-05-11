package com.dor.smarthome.utils;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 03.05.2014.
 */
public class Pair <K, V> {

    private K k;

    private V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (k != null ? !k.equals(pair.k) : pair.k != null) return false;
        if (v != null ? !v.equals(pair.v) : pair.v != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = k != null ? k.hashCode() : 0;
        result = 31 * result + (v != null ? v.hashCode() : 0);
        return result;
    }
}
