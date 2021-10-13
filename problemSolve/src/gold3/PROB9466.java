package gold3;
import java.util.*;
import java.io.*;
public class PROB9466 {
    static int T, N, M;
    static int arr[] = new int[100001];
    static int indegress[] = new int[100001];
    static Queue<Integer> q=  new LinkedList<>();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int ans;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(bf.readLine());
            ans = 0;
            for (int i = 1; i<= N; i++) {
                indegress[i] = 0;
                arr[i] = 0;
            }
            st = new StringTokenizer(bf.readLine());
            for (int i = 1; i<= N; i++) {
                int val = Integer.parseInt(st.nextToken());
                indegress[val]++;
                arr[i] = val;
            }
            q.clear();
            for (int i = 1; i <= N; i++) {
                if (indegress[i] == 0) q.add(i);
            }
            while(!q.isEmpty()) {
                int cur = q.poll();
                int next = arr[cur];
                indegress[next]--;
                if (indegress[next] == 0) {
                    q.add(next);
                }
            }
            for (int i = 1; i<= N; i++) {
                if (indegress[i] == 0) ans++;
            }
            System.out.println(ans);
        }
    }
}
