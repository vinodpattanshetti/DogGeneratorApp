package com.example.vinod.doggeneratorapp.base.utils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LRUCache12 {

    // store keys of cache
    static Deque<String> dq;
    // store references of key in cache
    static HashSet<String> map;
    // maximum capacity of cache
    static int csize;

    LRUCache12(int n) {
        dq = new LinkedList<>();
        map = new HashSet<>();
        csize = n;
    }

    /* Refers key x with in the LRU cache */
    public void refer(String x) {
        if (!map.contains(x)) {
            if (dq.size() == csize) {
                String last = dq.removeLast();
                map.remove(last);
            }
        } else {
            /* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            int index = 0, i = 0;
            Iterator<String> itr = dq.iterator();
            while (itr.hasNext()) {
                if (itr.next().equals(x)) {
                    index = i;
                    break;
                }
                i++;
            }
            dq.remove(i);
        }
        dq.push(x);
        map.add(x);
    }

    // display contents of cache
    public List<String> display() {
        List<String> list = new ArrayList<>();
        Iterator<String> itr = dq.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
            list.add(itr.next());
        }
        return list;
    }

}
