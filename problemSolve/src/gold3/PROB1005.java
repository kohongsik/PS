package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// ACM craft
public class PROB1005 {
    static int T, N, K, W;
    static int arr[] = new int[1001];

    static List<Integer> list[] = new ArrayList[1001];
    static long dp[] = new long[1001];
    static int[] indegree = new int[1001];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> q = new LinkedList<>();
    static long ans = 0;
    public static void main(String args[]) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            q.clear();
            ans = 0;
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dp[i] = 0;
                indegree[i] = 0;
                list[i] = new ArrayList<>();
            }
            for (int i = 1; i<= K; i++) {
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                indegree[end]++;
                list[start].add(end);
            }
            W = Integer.parseInt(bf.readLine());

            for (int i = 1; i<= N; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                    dp[i] = arr[i];
                }
            }
            if (dp[W] != 0) {
                System.out.println(dp[W]);
                continue;
            }
            while(!q.isEmpty() && ans == 0) {
                int cur = q.poll();
                for (int next : list[cur]) {
                    indegree[next]--;
                    dp[next] = Math.max(dp[next], dp[cur]);
                    if (indegree[next] == 0) {
                        q.add(next);
                        dp[next] += arr[next];
                        if (W == next) {
                            ans = dp[next];
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
