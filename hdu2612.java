
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            Cap base = new Cap();
            int a = base.c[0] = scanner.nextInt();
            int b = base.c[1] = scanner.nextInt();
            int c = base.c[2] = scanner.nextInt();

            if(a == 0 && b == 0 && c == 0) {
                break;
            }

            int [][][]dp = new int[a+1][b+1][c+1];
            int [][][]vis = new int[a+1][b+1][c+1];
            init(dp, a, b, c);
            Cap cur = new Cap();
            cur.c[0] = a;
            dfs(base, cur, dp, vis);
            int depth = dp[a][0][0];
            if(depth >= 0xffff) {
                System.out.println("NO");
            } else {
                System.out.println(depth - 1);
            }
        }
    }

    public static void init(int dp[][][], int a, int b, int c) {
       for(int i = 0; i <=a; i++) {
           for(int j = 0; j <= b; j++) {
               for(int k = 0; k <= c; k++) {
                   dp[i][j][k] = 0xffff;
               }
           }
       }
    }

    public static class Cap {
        int c[];
        public Cap() {
            c = new int[3];
        }

        public void set(int nc[]) {
            c[0] = nc[0];
            c[1] = nc[1];
            c[2] = nc[2];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(c[0]).append(",");
            sb.append(c[1]).append(",");
            sb.append(c[2]);
            return sb.toString();
        }
    }

    public static void dfs(Cap base, Cap cur, int dp[][][], int vis[][][]) {
        if(isTarget(cur)) {
            dp[cur.c[0]][cur.c[1]][cur.c[2]] = 1;
            return;
        }

        if(dp[cur.c[0]][cur.c[1]][cur.c[2]] < 0xffff) {
            return;
        }

        if(checkVis(cur, vis)) {
            return;
        }

        vis[cur.c[0]][cur.c[1]][cur.c[2]] = 1;
        List<Cap> caps = new ArrayList<>();
        index(base, cur, caps);
        //System.out.println(cur + ";" + caps);
        int res = 0xffff;
        for(Cap cap : caps) {
            dfs(base, cap, dp, vis);
            int tmp = dp[cap.c[0]][cap.c[1]][cap.c[2]];
            res = Math.min(tmp + 1 , res);
        }
        dp[cur.c[0]][cur.c[1]][cur.c[2]] = res;
        vis[cur.c[0]][cur.c[1]][cur.c[2]] = 0;
    }

    public static boolean isTarget(Cap cur) {
        if(cur.c[0] == 0 && cur.c[1] == cur.c[2]) {
            return true;
        }

        if(cur.c[1] == 0 && cur.c[0] == cur.c[2]) {
            return true;
        }

        if(cur.c[2] == 0 && cur.c[0] == cur.c[1]) {
            return true;
        }
        return false;
    }

    public static boolean checkVis(Cap cap, int dp[][][]) {
        if(dp[cap.c[0]][cap.c[1]][cap.c[2]] == 0) {
            return false;
        }
        return true;
    }

    public static void index(Cap base, Cap cur, List<Cap> caps) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == j) continue;
                int nc[] = new int[3];
                nc[0] = cur.c[0];
                nc[1] = cur.c[1];
                nc[2] = cur.c[2];

                if(nc[i] + nc[j] <= base.c[j]){
                    nc[j] = nc[i] + nc[j];
                    nc[i] = 0;
                } else {
                    nc[i] = nc[i] + nc[j] - base.c[j];
                    nc[j] = base.c[j];
                }
                Cap ncap = new Cap();
                ncap.set(nc);
                caps.add(ncap);
            }
        }
    }

}
