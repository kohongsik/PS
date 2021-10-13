package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PROB2146 {
    static int N;
    static int[][] arr = new int[101][101];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int groupingNum;
    static int dx[] = new int[] {-1, 0, 0, 1};
    static int dy[] = new int[] {0, -1, 1, 0};
    static boolean[][] visited = new boolean[101][101];
    static Queue<int[]> q = new LinkedList<>();
    static int[][] distanceTable = new int[101][101];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 1; i<= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        groupingNum = 1;
        for (int i = 1; i <= N; i++) {
            for(int j = 1; j<= N; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == 0) continue;
                visited[i][j] = true;
                grouping(i, j);
                groupingNum++;
            }
        }
//        for (int i = 1; i <= N; i++) {
//            for(int j = 1; j<= N; j++) {
//                System.out.print(arr[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for (int i = 1; i <= N; i++) {
//            for(int j = 1; j<= N; j++) {
//                System.out.print(distanceTable[i][j] + " ");
//            }
//            System.out.println();
//        }
        int ans = 100000000;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 1; i <= size; i++) {
                int[] cur = q.poll();
                int y = cur[0];
                int x = cur[1];
                int curGroup = arr[y][x];
                int curDistance = distanceTable[y][x];
                // 주위 4방향에 다른 그룹이 있는지 확인.
                for (int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];
                    if (ny >= 1 && ny <= N && nx >= 1 && nx <= N) {
                        if (visited[ny][nx]) {
                            // 다른 그룹인지 확인.
                            if (arr[ny][nx] != curGroup) {
                                ans = Math.min(ans, curDistance + distanceTable[ny][nx]);
                            }
                            continue;
                        }
                        visited[ny][nx] = true;
                        distanceTable[ny][nx] = curDistance + 1;
                        arr[ny][nx] = curGroup;
                        q.add(new int[] {ny, nx});

                    }
                }
            }
        }
        System.out.println(ans);
    }
    public static void grouping(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            arr[cur[0]][cur[1]] = groupingNum;
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny >= 1 && ny <= N && nx >= 1 && nx <= N) {
                    if (visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (arr[ny][nx] != 0) {
                        // groupingNum 추가
                        queue.add(new int[] {ny, nx});
                    } else {
                        // q에 추가
                        q.add(new int[] {ny, nx});
                        distanceTable[ny][nx] = 1;
                        arr[ny][nx] = groupingNum;
                    }
                }
            }
        }
    }
}
