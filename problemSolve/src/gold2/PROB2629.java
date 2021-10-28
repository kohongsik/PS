package gold2;
import java.io.*;
import java.util.*;

public class PROB2629 {
    static int N;
    static int[] arr = new int[32];
    static int M;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] dp = new int[32][40001];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i =1; i<= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        solve(1, 0);
        for (int j = 1; j <= M; j++) {
            int value = Integer.parseInt(st.nextToken());
            int flag = dp[N+1][value];
            String ans = value > 30*500 ? "N" : flag == 1 ? "Y" : "N";
            sb.append(ans + " ");
        }
        System.out.println(sb.toString().trim());

    }
    public static void solve(int i, int j) {
        if (i > N + 1) return;
        if (dp[i][j] != 0) return;
        dp[i][j] = 1;
        solve(i + 1, j);
        solve(i +1, j + arr[i]);
        solve(i + 1, Math.abs(j - arr[i]));
    }
}
