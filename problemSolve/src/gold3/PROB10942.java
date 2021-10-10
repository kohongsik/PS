package gold3;
import java.util.*;
import java.io.*;

public class PROB10942 {
    static int N, M;
    static int[] arr = new int[2001];
    static int dp[][] = new int[2001][2001];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        for (int i = 1; i<= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(solve(s,e) + "\n");
        }
        System.out.println(sb.toString());
    }
    static public int solve(int s, int e) {
        if (s == e) return dp[s][e] = 1;
        if (s + 1 == e) return dp[s][e] = arr[s] == arr[e] ? 1 : 0;
        if (dp[s][e] != -1) return dp[s][e];
        if (arr[s] == arr[e]) return dp[s][e] = solve(s+1, e-1);
        return dp[s][e] = 0;
    }
}
