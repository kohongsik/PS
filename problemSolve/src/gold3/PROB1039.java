package gold3;

import java.util.*;
import java.io.*;

public class PROB1039 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int ans;
    static int[][] visit = new int[11][1000001];
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String args[]) throws IOException {
        st =  new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        q.add(N);
        int len = 0;
        int tempVal = N;
        while(tempVal != 0) {
            tempVal /= 10;
            len++;
        }
        ans = -1;
        if (len == 1 || (len == 2 && N % 10 == 0)) {
            System.out.println(-1);
            return;
        }
        for (int i = 1; i <= K; i++) {
            int size = q.size();
            for (int idx = 0; idx < size; idx++) {
                int cur = q.poll();
                for (int j = 1; j <= len - 1; j++) {
                    for (int k = j + 1; k <= len; k++) {
                        int newVal = swap(cur, j, k);
                        if (newVal != -1) {
                            if (visit[i][newVal] == 1) continue;
                            q.add(newVal);
                            visit[i][newVal] = 1;
                            if (i == K) {
                                ans = Math.max(ans, newVal);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
    public static int swap(int N, int i, int j) {
        int val = N;
        int[] arr = new int[8];
        int idx = 1;
        while(val != 0) {
            int remainVal = val % 10;
            val = val / 10;
            arr[idx++] = remainVal;
        }
        if (arr[j] == 0 && j == idx) return -1;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return convertToNum(arr, idx);
    }
    public static int convertToNum(int[] arr, int idx) {
        int times = 1;
        int ret = 0;
        for (int i = 1; i < idx; i++) {
            ret += arr[i] * times;
            times *= 10;
        }
        return ret;
    }
}
