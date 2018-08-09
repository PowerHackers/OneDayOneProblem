package org.powerhackers;

import java.util.*;

public class App
{
    public static void main( String[] args )
    {
        Solution solution = new Solution();
        solution.read();
        solution.solve(0);
        solution.print();
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class Solution {
        private static final int MAXN = 5;
        private static final int INF = 0xffff;
        private int map[][] = new int[MAXN][MAXN];
        private int vis[][] = new int[MAXN][MAXN];
        private int dis[][] = new int[MAXN][MAXN];
        private Point prepath[][] = new Point[MAXN][MAXN];
        private Point postpath[][] = new Point[MAXN][MAXN];
        private int flag = 0;

        private int dir[][] = {
                {0,1}, {1,0}, {-1,0},{0,-1}
        };

        public Solution() {
            init();
        }

        public void init() {
            for(int i = 0; i < MAXN; i++) {
                for(int j = 0; j < MAXN; j++) {
                    dis[i][j] = INF;
                }
            }
        }

        public void read() {
            Scanner scanner = new Scanner(System.in);
            for(int i = 0; i < MAXN; i++) {
                for(int j = 0; j < MAXN; j++){
                    map[i][j] = scanner.nextInt();
                }
                //scanner.nextLine();
            }
        }

        public void solve(int flag) {
            this.flag = flag;
            if(flag == 0) {
                bfs();
            } else {
                dfs(0,0);
            }
        }

        private void bfs() {
            Queue<Point> queue = new LinkedList<Point>();
            queue.offer(new Point(0,0));

            vis[0][0] = 1;
            prepath[0][0] = null;
            while(!queue.isEmpty()) {
                Point point = queue.poll();
                int x = point.x;
                int y = point.y;

                for(int i = 0; i < 4; i++) {
                    int xx = x + dir[i][0];
                    int yy = y + dir[i][1];
                    if(xx >= 0 && yy >= 0 && xx < MAXN && yy < MAXN && vis[xx][yy] == 0 && map[xx][yy] != 1) {
                        Point np = new Point(xx, yy);
                        prepath[xx][yy] = point;
                        vis[xx][yy] = 1;
                        queue.offer(np);
                    }
                }
            }
        }

        private void dfs(int x, int y) {
            if(x < 0 || y < 0 || x >= MAXN || y >= MAXN) {
                return;
            }

            if(x == MAXN -1 && y == MAXN -1) {
                dis[x][y] = 0;
                postpath[x][y] = null;
                return;
            }

            if(vis[x][y] == 1 || map[x][y] == 1) {
                return;
            }

            vis[x][y] = 1;
            for(int i = 0; i < 4; i++) {
               int xx =  x + dir[i][0];
               int yy =  y + dir[i][1];

               if(xx < 0 || yy < 0 || xx >= MAXN || yy >= MAXN) {
                   continue;
               }

               dfs(xx, yy);
               if(dis[xx][yy] + 1 < dis[x][y]) {
                   dis[x][y] = dis[xx][yy] +1;
                   postpath[x][y] = new Point(xx, yy);
               }
            }
        }

        private void printbfs() {
            Point point = new Point(MAXN - 1, MAXN -1);
            List<Point> list = new ArrayList<Point>();
            StringBuilder sb = new StringBuilder();
            while(point != null) {
                list.add(point);
                int x = point.x;
                int y = point.y;
                point = prepath[x][y];
            }

            for(int i = list.size() - 1; i >= 0; i--) {
                sb.append(list.get(i)).append('\n');
            }
            System.out.print(sb.toString());
        }

        private void printdfs() {
            Point point = new Point(0, 0);
            StringBuffer sb = new StringBuffer();
            while(point != null) {
                sb.append(point).append('\n');
                int x = point.x;
                int y = point.y;
                point = postpath[x][y];
            }
            System.out.print(sb.toString());
        }

        public void print() {
            if(flag == 0) {
                printbfs();
            } else {
                printdfs();
            }
        }
    }
}
