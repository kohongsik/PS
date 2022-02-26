package gold1;
import java.io.*;
import java.util.*;
public class PROB12944 {
    static int N, K;
    static int[] arr = new int[21];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= K; i++) {
            ans += N / arr[i];
        }
        for (int i = 2; i <= K; i++) {
            back2(1, 0, i, 0);
            back2(1, 1, i, arr[1]);
        }

        System.out.println(ans);
    }
    public static void back2 (int idx, int acc, int target, int mul) {
        if (acc == target) {
            ans = target % 2 == 0 ? ans - N / mul : ans + N / mul;
            return;
        }
        if (idx != K) {
            long next = mul == 0 ? arr[idx + 1] : (long)mul * arr[idx + 1] / gcd(mul, arr[idx + 1]);
            if (next > 0 && next <= N) {
                back2(idx + 1, acc + 1, target, (int)next);
            }
            back2(idx + 1, acc, target, mul);
        }
    }
    public static int gcd (int i, int j) {
        if (i == 0) return j;
        return gcd(j % i, i);
    }
}
