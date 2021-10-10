package gold3;

import java.util.*;
import java.io.*;

public class PROB1238 {
    static int N, M, X;
    static List<int[]> list[] = new ArrayList[1001];
    static List<int[]> reverse[] = new ArrayList[1001];
    static int[] store = new int[1001];
    static int[] storeReverse = new int[1001];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int i =1 ; i<= N; i++) {
            list[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        for (int i = 1; i<= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new int[] {e, c});
            reverse[e].add(new int[] {s, c});
        }
        int[] arr1 = solve(0);
        int[] arr2 = solve(1);
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, arr1[i] + arr2[i]);
        }
        System.out.println(ans);
    }
    static int[] solve(int flag) {
        List<int[]> ref[] = flag == 1 ? list : reverse;
        int[] ret = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            ret[i] = INF;
        }
        ret[X] = 0;
        PriorityQueue<NODE> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(new NODE(X, X, 0));
        while(!pq.isEmpty()) {
            NODE node = pq.poll();
            int start = node.start;
            int cur = node.cur;
            int cost = node.cost;
            if (ret[cur] < cost) continue;
            ret[cur] = cost;
            for (int[] next : ref[cur]) {
                if (ret[next[0]] < cost + next[1]) continue;
                pq.add(new NODE(start, next[0], cost + next[1]));
            }
        }
        return ret;
    }
    static class NODE {
        int start, cur, cost;
        public NODE (int start, int cur, int cost) {
            this.start = start;
            this.cur = cur;
            this.cost = cost;
        }
    }
}
