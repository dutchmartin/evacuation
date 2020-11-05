package nl.martijngroeneveldt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read line 1.
        int cities = scanner.nextInt();
        Road[] roads = new Road[scanner.nextInt()];
        // Read line 2.
        int[] endangered = new int[scanner.nextInt()];
        int[] designated = new int[scanner.nextInt()];
        for (int i = 0; i < endangered.length; i++) {
            endangered[i] = scanner.nextInt();
        }
        for (int i = 0; i < designated.length; i++) {
            designated[i] = scanner.nextInt();
        }
        for(int i = 0; i < roads.length; i++) {
            Road road = new Road();
            road.from = scanner.nextInt();
            road.to = scanner.nextInt();
            road.capacity = scanner.nextInt();
        }
    }
}
