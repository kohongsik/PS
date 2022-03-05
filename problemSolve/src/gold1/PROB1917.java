package gold1;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 정육면체 전개도
public class PROB1917 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 0 : 왼쪽, 1 : 아랫쪽, 2: 윗쪽, 3: 오른쪽
    static int[] dy = new int[] {0, 1, -1, 0};
    static int[] dx = new int[] {-1, 0, 0, 1};
    static final int LOOP_CNT = 3;
    static final int ROW_CNT = 6;
    static int[][] arr = new int[7][7];
    static final int FLOOR = 1;
    static final int TOP = 2;
    static final int LEFT = 3;
    static final int RIGHT = 4;
    static final int FRONT = 5;
    static final int REAR = 6;
    static int[][][] getPosition = new int[7][7][4];
    public static void main(String[] args) throws IOException {
        int curLoopCnt = 1;

        while (curLoopCnt <= LOOP_CNT) {
            Point p = new Point();
            for (int i = 1; i <= ROW_CNT; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j <= ROW_CNT; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1 && p.y < 0) {
                        p.x = j;
                        p.y = i;
                    }
                }
            }
            System.out.println("floor point is : " + p.y + ", " + p.x);
            String ans = searchAdjacencyPointAndGetResult(p) ? "yes" : "no";
            System.out.println(ans);
            curLoopCnt++;
        }
    }
    private static class Point {
        int x;
        int y;
        int myPosition;
        int prevPosition;
        public Point() {
            this.y = -1;
            this.x = -1;
            this.myPosition = -1;
            this.prevPosition = 0;
        }
        public Point(int y, int x, int myPosition, int prevPosition) {
            this.y = y;
            this.x = x;
            this.myPosition = myPosition;
            this.prevPosition = prevPosition;
        }
    }
    private static boolean searchAdjacencyPointAndGetResult (Point p) {
        Queue<Point> q = new LinkedList<>();
        p.myPosition = FLOOR;
        p.prevPosition = 0;
        q.add(p);
        boolean[][] visited = new boolean[7][7];
        visited[p.y][p.x] = true;
        int[] planeCnt = new int[7];
        planeCnt[FLOOR]++;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;
                // 해당 방향으로 쭉 탐색
                if (ny >= 1 && ny <= 6 && nx >= 1 && nx <= 6 && arr[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    int position = getPosition[cur.prevPosition][cur.myPosition][k];
                    System.out.println(positionToString(position));
                    planeCnt[position]++;
                    q.add(new Point(ny, nx, position, cur.myPosition));
                }
            }
        }
        for (int i = 1; i < 7; i++) {
            if (planeCnt[i] != 1) return false;
        }
        return true;
    }
    public static String positionToString(int position) {
        return position == 1
                ? "FLOOR"
                : position == 2
                    ? "TOP"
                        : position == 3
                            ? "LEFT"
                            : position == 4
                                ? "RIGHT"
                                : position == 5
                                    ? "FRONT"
                                    : position == 6
                                        ? "REAR"
                                        : "UNKNOWN";
    }
}
