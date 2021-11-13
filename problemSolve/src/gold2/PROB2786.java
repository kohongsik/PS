package gold2;
import java.io.*;
import java.util.*;
public class PROB2786 {
    static int N;
    static List<Integer> list = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();
    static int[][] arr;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int curIdx = 0;
    static long[] ans;
    static StringBuilder sb = new StringBuilder();
    static boolean visited[];
    static int idx;
    static int idx2;
    static int prevFirstIdx;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[2][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            list.add(i);
            list2.add(i);
            arr[0][i] = first;
            arr[1][i] = second;
        }
        ans = new long[N];
        Collections.sort(list, (a,b) -> arr[0][a] - arr[0][b] == 0 ? arr[1][a] - arr[1][b] : arr[0][a] - arr[0][b]); // first asc,
        Collections.sort(list2, (a,b) -> arr[1][a] - arr[1][b] == 0 ? arr[0][a] - arr[0][b] : arr[1][a] - arr[1][b]); // first asc,
        ans[0] = arr[0][list.get(0)];
        idx = 1;
        idx2 = 0;
        prevFirstIdx = list.get(0);
        visited[list.get(0)] = true;
        curIdx = 1;
        greedy();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i] + "\n");
        }
        System.out.println(sb.toString());
    }
    public static void greedy () {
        // 첫번쪠 사용하는 인덱스를 변경해야하는경우.
        if (curIdx == N) return;
        // idx 갱신
        while(visited[list.get(idx)] && idx < N) {
            idx++;
        }
        // idx2 갱신
        while(visited[list2.get(idx2)] && idx2 < N) {
            idx2++;
        }
        int listIdx = list.get(idx);
        int list2Idx = list2.get(idx2);
        // 처음 뽑은메뉴를 바꿔야 하는 경우
        long changedValue = ans[curIdx - 1] + arr[0][listIdx] - arr[0][prevFirstIdx] + arr[1][prevFirstIdx];
        // 처음 뽑은메뉴를 유지하는 경우
        long notChangedValue = ans[curIdx -1] + arr[1][list2Idx];
        // System.out.println(changedValue + ", " + notChangedValue);
        // 최솟값 갱신 및 visited 처리
        if (changedValue < notChangedValue) {
            visited[listIdx] = true;
            prevFirstIdx = listIdx;
            ans[curIdx] = changedValue;
        } else {
            visited[list2Idx] = true;
            ans[curIdx] = notChangedValue;
        }
        curIdx++;
        greedy();
    }

}
