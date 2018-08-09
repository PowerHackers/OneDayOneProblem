
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            char map[][] = new char[m][n];
            scanner.nextLine();

            point start1 = new point();
            point start2 = new point();
            for(int i = 0; i < m; i++) {
                String line = scanner.nextLine();
                map[i] = line.toCharArray();
                if(line.contains("Y")) {
                    start1.x = i;
                    start1.y = line.indexOf('Y');
                }

                if(line.contains("M")) {
                    start2.x = i;
                    start2.y = line.indexOf('M');
                }
            }

            int dis1[][] = new int[m][n];
            int dis2[][] = new int[m][n];

            bibfs(start1, m, n, map, dis1);
            bibfs(start2, m, n, map, dis2);

            int dis = Integer.MAX_VALUE;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] == '@' && (dis1[i][j] + dis2[i][j]) > 0) {
                        dis = Math.min(dis, dis1[i][j] + dis2[i][j]);
                    }
                }
            }

            System.out.println(dis * 11);
        }
    }

    public static class point {
        int x;
        int y;
    }

    public static void bibfs(point s1, int m, int n, char [][]map, int [][]dis) {
        int dir[][] = {
            {0,1},{1,0},{0,-1},{-1,0}
        };
        Queue<point> queue = new LinkedList<>();
        queue.offer(s1);
        while(!queue.isEmpty()) {
            point f = queue.poll();
            int x = f.x;
            int y = f.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= m || ny >=n) {
                    continue;
                }

                if(dis[nx][ny] != 0 || map[nx][ny] == '#') {
                    continue;
                }
                point np = new point();
                np.x = nx;
                np.y = ny;
                queue.offer(np);
                dis[nx][ny] = dis[x][y] + 1;
            }
        }

    }
}
