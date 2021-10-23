package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROB2266 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int K;
    static int dp[][] = new int[501][501]; // dp[i][j] : 1 ~ i 층까지 j개의 금고가 있을때 확인할 수 있는 최소한의 수.
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(solve(N, K));
    }
    public static int solve(int n, int k) {
        if (k == 1) {
            return dp[n][k] = n;
        }
        if (n == 0) return dp[n][k] = 0;
        if (n == 1) return dp[n][k] = 1;
        if (dp[n][k] != 0) return dp[n][k];
        int val = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int newVal = Math.max(solve(n - i, k) + 1, solve(i - 1, k - 1) + 1);
            val = Math.min(val,newVal);
        }
        return dp[n][k] = val;
    }
}
