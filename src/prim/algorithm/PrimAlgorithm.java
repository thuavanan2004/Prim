/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prim.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Admin
 */

class Edge implements Comparable<Edge> {
    int target;
    int weight;

    Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    // So sánh cạnh dựa trên trọng số
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class PrimAlgorithm {

    /**
     * @param args the command line arguments
     */
     public static List<Edge> prim(int[][] graph) {
        int n = graph.length;  // Số đỉnh
        List<Edge> mst = new ArrayList<>();  // Danh sách để lưu cây khung nhỏ nhất
        boolean[] visited = new boolean[n];  // Đánh dấu các đỉnh đã được thêm vào cây khung
        PriorityQueue<Edge> pq = new PriorityQueue<>();  // Hàng đợi ưu tiên để lưu các cạnh có trọng số nhỏ nhất

        // Bắt đầu từ đỉnh 0
        visited[0] = true;
        for (int i = 1; i < n; i++) {
            if (graph[0][i] != 0) {
                pq.add(new Edge(i, graph[0][i]));  // Thêm các cạnh nối với đỉnh 0
            }
        }

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();  // Lấy cạnh có trọng số nhỏ nhất từ hàng đợi ưu tiên
            if (!visited[minEdge.target]) {
                visited[minEdge.target] = true;  // Đánh dấu đỉnh này đã được thêm vào cây khung
                mst.add(minEdge);  // Thêm cạnh này vào cây khung

                // Thêm tất cả các cạnh kết nối từ đỉnh mới thêm vào hàng đợi ưu tiên
                for (int i = 0; i < n; i++) {
                    if (graph[minEdge.target][i] != 0 && !visited[i]) {
                        pq.add(new Edge(i, graph[minEdge.target][i]));
                    }
                }
            }
        }

        return mst;  // Trả về cây khung nhỏ nhất
    }

    public static void main(String[] args) {
        // Ma trận trọng số biểu diễn đồ thị (0 là không có cạnh)
            int[][] graph = {
        {0, 100, 0, 200},
        {100, 0, 50, 0},
        {0, 50, 0, 300},
        {200, 0, 300, 0}
    };

        List<Edge> mst = prim(graph);

        // In ra cây khung nhỏ nhất
        System.out.println("Cay khung nho nhat:");
        for (Edge edge : mst) {
            System.out.println("Canh: " + edge.target + " voi trong so: " + edge.weight);
        }
    }
    
}
