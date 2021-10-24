package gold1;


import java.io.*;
import java.util.*;


public class PROB1486 {
    static int N, M, T, D;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = new int[] {-1, 0, 0, 1};
    static int[] dy = new int[] {0, 1, -1, 0};
    static int[][] arr = new int[26][26];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        for (int i =1; i <= N; i++) {
            String row = bf.readLine();
            char[] chs = row.toCharArray();
            for (int j = 0; j < M; j++) {
                char c = chs[j];
                arr[i][j + 1] = 'a' <= c && c <= 'z'
                        ? c - 'a' + 26
                        : c - 'A';
            }
        }
        int ans = arr[1][1];
        for (int i = 1; i<= N; i++) {
            for(int j = 1; j<=M; j++) {
                if (arr[1][1] < arr[i][j]) {
                    int cost = solve(i, j);
                    if (cost != -1 && cost <= D) {
                        ans = Math.max(ans, arr[i][j]);
                    }
                }
            }
        }
        System.out.println(ans);
    }
    public static int solve(int i, int j) {
        int[] ret = new int[2];
        int height = arr[i][j];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.add(new int[] {1, 1, 0});
        boolean visited[][] = new boolean[N + 1][M + 1];
        int[][] minTable = new int[N+1][M+1];
        for (int k = 1; k <= N; k++) {
            Arrays.fill(minTable[k], 2000001);
        }
        minTable[1][1] = 0;
        visited[1][1] = true;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];
            visited[y][x] = true;
            if (y == i && x == j) {
                ret[0] = cost;
                break;
            }
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny >= 1 && ny <= N && nx >= 1 && nx <= M) {
                    if (Math.abs(arr[ny][nx] - arr[y][x]) > T) continue;
                    if (arr[ny][nx] > height && ny != i && nx != j) continue;
                    if (visited[ny][nx]) continue;
                    int nextCost = arr[ny][nx] > arr[y][x]
                            ? (arr[ny][nx] - arr[y][x]) * (arr[ny][nx] - arr[y][x])
                            : 1;
                    if (nextCost + cost <= D && minTable[ny][nx] > nextCost + cost) {
                        minTable[ny][nx] = nextCost + cost;
                        pq.add(new int []{ny, nx, nextCost + cost});
                    }
                }
            }
        }
        // 해당지점에서 원점으로 돌아가기..
        pq.clear();
        pq.add(new int [] {i, j, 0});
        for (int k =1 ; k <= N; k++) {
            Arrays.fill(visited[k], false);
        }
        for (int k = 1; k <= N; k++) {
            Arrays.fill(minTable[k], 2000001);
        }
        minTable[i][j] = 0;
        visited[i][j] = true;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];
            visited[y][x] = true;
            if (y == 1 && x == 1) {
                ret[1] = cost;
                break;
            }
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny >= 1 && ny <= N && nx >= 1 && nx <= M) {
                    if (Math.abs(arr[ny][nx] - arr[y][x]) > T) continue;
                    if (arr[ny][nx] > height) continue;
                    if (visited[ny][nx]) continue;
                    int nextCost = arr[ny][nx] > arr[y][x]
                            ? (arr[ny][nx] - arr[y][x]) * (arr[ny][nx] - arr[y][x])
                            : 1;
                    if (nextCost + cost <= D && minTable[ny][nx] > nextCost + cost) {
                        minTable[ny][nx] = nextCost + cost;
                        pq.add(new int []{ny, nx, nextCost + cost});
                    }
                }
            }
        }
        if (ret[0] == 0 || ret[1] == 0) {
            return -1;
        }
        return ret[0] + ret[1];
    }
}

