package com.practice;

import java.util.PriorityQueue;

public class CheapestPrice {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for(int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0,src,K+1});
        while(!heap.isEmpty()) {
            int[] current = heap.remove();
            int price = current[0];
            int node = current[1];
            int stops = current[2];

            if(node == dst)
                return price;
            if(stops > 0) {
                for(int i = 0;i<n;i++) {
                    if(graph[node][i] > 0)
                        heap.add(new int[]{graph[node][i] + price, i , stops - 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}},0, 2, 1));
        System.out.println(findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}},0, 2, 0));
    }
}
