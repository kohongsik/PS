package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PROB1655 {
    static int N;
    static int[] arr = new int[100001];
    static int[] sortedArr = new int[100001];
    static int[] seg = new int[100001 * 4 + 1];
    static int[] order = new int[20002];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(bf.readLine());
            arr[i] = val;
            sortedArr[i] = val;
        }
        Arrays.sort(sortedArr, 1, N+ 1);
        for (int i = 1; i<= N; i++) {
            int val = sortedArr[i] + 10000;
            order[val] = i;
        }
        for (int i = 1; i<= N; i++) {
            int mid = i % 2 == 0 ? i / 2 : i / 2 + 1;
            int value = arr[i] + 10000;
            int ord = order[value];
            update(1, ord, 1, N);
            int curMidIdx = find(1, mid, 1, N);
            sb.append(sortedArr[curMidIdx] + "\n");
        }
        System.out.println(sb.toString());
    }
    public static void update(int node, int idx, int start, int end) {
        if (idx < start || idx > end) return;
        if (start == end) {
            seg[node] += 1;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, idx, start, mid);
        update(node * 2 + 1, idx, mid + 1, end);
        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }
    public static int find(int node, int value, int start, int end) {
        if (start == end) return start;
        int mid = (start + end) / 2;
        if (value <= seg[node * 2]) {
            return find(node * 2, value, start, mid);
        }
        return find(node * 2 + 1, value - seg[node * 2], mid + 1, end);
    }
}
