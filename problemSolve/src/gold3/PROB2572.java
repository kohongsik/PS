package gold3;

import java.io.*;
import java.util.*;
public class PROB2572 {
    static int N, M, K;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<int[]> list[] = new ArrayList[501];
    static int[] cards = new int[1001];
    static int[][] dp = new int[1001][501];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            String RGB = st.nextToken();
            cards[i] = parseIntByRGB(RGB);
        }
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= M; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i<= K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            String RGB = st.nextToken();
            int code = parseIntByRGB(RGB);
            list[a].add(new int[] {b, code});
            list[b].add(new int[] {a, code});
        }
        // dp initializing
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][1] = 0;
        for (int[] next : list[1]) {
            dp[1][next[0]] = next[1] == cards[1] ? 10 : 0;
        }
        for (int i = 1; i <= M; i++) {
            if (dp[1][i] < 0) dp[1][i] = -2;
        }
        int ans = 0;
        for (int i = 1; i <= M; i++) {
            int s = solve(N, i);
            ans = Math.max(ans, s);
        }
        System.out.println(ans);
    }
    public static int parseIntByRGB(String RGB) {
        int ret = 3;
        if ("R".equals(RGB)) {
            ret = 1;
        } else if ("G".equals(RGB)) {
            ret = 2;
        }
        return ret;
    }
    public static int solve(int idx, int node) {
       // idx 까지 사용한 상태에서 node 지점에 도착했을때의 최댓값.
       if (idx == 1) {
           return dp[idx][node];
       }
       if (dp[idx][node] != -1) {
           return dp[idx][node];
       }
       int ret = 0;
       int rightCnt = 0;
       for (int[] next : list[node]) {
           int value = next[1] == cards[idx] ? 10 : 0;
           int sub = solve(idx - 1, next[0]);
           if (sub < 0) continue;
           ret = Math.max(ret, sub + value);
           rightCnt++;
       }
       if (rightCnt == 0) {
           return dp[idx][node] = -2;
       }
       return dp[idx][node] = ret;
    }
}
