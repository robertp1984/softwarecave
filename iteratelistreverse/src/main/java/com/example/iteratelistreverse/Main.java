package com.example.iteratelistreverse;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections.iterators.ReverseListIterator;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");

        iterateIndexedAccess(list);
        iterateListIterator(list);
        iterateReverseListIterator(list);
        iterateListsReverse(list);
    }

    private static void iterateIndexedAccess(List<String> list) {
        System.out.print("\nFor loop with random access:\n");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    private static void iterateListIterator(List<String> list) {
        System.out.print("\nList iterator:\n");
        ListIterator<String> it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    private static void iterateReverseListIterator(List<String> list) {
        System.out.print("\nApache ReverseListIterator:\n");
        Iterator<String> it = new ReverseListIterator(list);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private static void iterateListsReverse(List<String> list) {
        System.out.print("\nGuava List.reverse:\n");
        for (String v : Lists.reverse(list)) {
            System.out.println(v);
        }
    }

}
