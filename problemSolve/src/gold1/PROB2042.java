package gold1;
import java.io.*;
import java.util.*;

public class PROB2042 {
    static long seg[] = new long[1000001 * 4 + 1];
    static int N, M, K;
    static long[] arr = new long[1000001];
    static StringBuilder sb = new StringBuilder();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i =1; i <= N; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }
        init(1, 1, N);
        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                // change value
                int idx = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                update(1, idx, 1, N, val);
            } else {
                // get sum
                int left = Integer.parseInt(st. nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(1, left, right, 1, N) + "\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static void init (int node, int start, int end) {
        if (start == end) {
            seg[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(node * 2, start, mid);
        init(node * 2 + 1, mid + 1, end);
        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }
    public static void update (int node, int idx, int start, int end, long val) {
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            seg[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, idx, start, mid, val);
        update(node * 2 + 1, idx, mid + 1, end, val);
        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }
    public static long query (int node, int left, int right, int start, int end) {
        if (right < start || left > end) {
            return 0;
        }
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) / 2;
        long leftVal = query(node * 2, left, right, start, mid);
        long rightVal = query(node * 2 + 1, left,right, mid + 1, end);
        return leftVal + rightVal;
    }
}
