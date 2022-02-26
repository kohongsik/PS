package gold1;
// 물약 _ 실패
import java.io.*;
import java.util.*;
public class PROB1050 {
    static int N, M;
    static HashMap<String, Integer> materialsShop = new HashMap<>();
    static HashMap<String, Long> minCost = new HashMap<>();
    static HashMap<String, List<String>> recipeMap = new HashMap<>();
    static StringTokenizer st;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    final static String LOVE = "LOVE";
    final static long INF = 1000000000;
    // final static long MOD = 1000000001;
    static Set<String> visitedMaterials = new HashSet<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            materialsShop.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine(), "=");
            String leftSide = st.nextToken();
            String rightSide = st.nextToken();
            if (!recipeMap.containsKey(leftSide)) recipeMap.put(leftSide, new ArrayList<>());
            recipeMap.get(leftSide).add(rightSide);
        }
        makeRecipe(LOVE);
        System.out.println(minCost.get(LOVE));
    }
    public static long makeRecipe (String str) {
        if (minCost.containsKey(str)) return minCost.get(str);
        if (visitedMaterials.contains(str)) {
            // minCost.put(str, -1L);
            return -1;
        }
        visitedMaterials.add(str);
        long ret = -1;
        if (materialsShop.containsKey(str)) {
            ret = (long)materialsShop.get(str);
        }
        if (recipeMap.containsKey(str)) {
            List<String> recipes = recipeMap.get(str);
            for (String recipe : recipes) {
                StringTokenizer st2 = new StringTokenizer(recipe, "+");
                long costInRecipe = 0;
                while(st2.hasMoreTokens()) {
                    String sub = st2.nextToken();
                    int val = sub.charAt(0) - '0';
                    String material = sub.substring(1);
                    if (str.equals(material)) {
                        costInRecipe = -1;
                         break;
                    }
                    long makeSub = makeRecipe(material);
                    if (makeSub == -1) {
                        costInRecipe = -1;
                         break;
                    }
                    if (costInRecipe == -1) continue;
                    long newSubCost = makeSub * val;
                    if (costInRecipe > INF) continue;
                    if (newSubCost > INF || newSubCost + costInRecipe > INF) {
                        costInRecipe = INF + 1;
                    } else {
                        costInRecipe += newSubCost;
                    }
                }
                if (costInRecipe != -1) {
                    ret = ret == -1 ? costInRecipe : Math.min(ret, costInRecipe);
                }
            }
        }
        minCost.put(str, ret);
        return ret;
    }
}