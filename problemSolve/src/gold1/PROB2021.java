package gold1;
// 최소 환승 경로

import java.util.*;
import java.io.*;
public class PROB2021 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L;
    static List<int[]>[] list;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(bf.readLine());
            int before = -1;
            while(st.hasMoreTokens()) {
                int value = Integer.parseInt(st.nextToken());
                if (value == -1) break;
                if (before != -1) {
                    list[before].add(new int[] {value, i});
                    list[value].add(new int[] {before, i});
                }
                before = value;
            }
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end =  Integer.parseInt(st.nextToken());
        System.out.println(solve(start, end));

    }
    public static int solve(int start, int end) {
        if (start == end) return 0;
        int[] D = new int[N+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[2] - b[2]
        ); // 환승횟수가 작은 것부터 우선 탐색
        pq.add(new int[] {start, -1, -1});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curLineNo = cur[1];
            int curTranferCnt = cur[2];
            if (D[curNode] < curTranferCnt) continue;
            if (curNode == end) break;
            for (int[] next : list[curNode]) {
                int newTransferCnt = curLineNo == next[1]
                        ? curTranferCnt
                        : curTranferCnt + 1;
                if (D[next[0]] > newTransferCnt) {
                    int[] searchObj = new int[] {
                            next[0], next[1], newTransferCnt
                    };
                    pq.add(searchObj);
                    D[next[0]] = newTransferCnt;
                }
            }
        }
        return D[end] == Integer.MAX_VALUE ? -1 : D[end];
    }
}
