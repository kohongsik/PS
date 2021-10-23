package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PROB1766 {
    static int N, M;
    static int[] indegree = new int[100001];
    static List<Integer> list[] = new ArrayList[100001];
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            indegree[i] = 0;
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            indegree[b]++;
        }
        for(int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");
            for(int next : list[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        System.out.println(sb.toString());

    }
}
