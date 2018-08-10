package org.powerhackers;

import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        Solution solution = new Solution();
        solution.solve();
    }

    public static class Solution {
        private static final int M = 10000;
        private static final int N = 1000005;
        private final int seqN[] = new int[N];
        private final int seqM[] = new int[M];
        private int m = 0;
        private int n = 0;

        //next
        private final int next[] = new int[M];

        private void init() {
            for(int i = 0; i < m; i++) {
                next[i] = 0;
            }
        }

        public void solve() {
            Scanner scanner = new Scanner(System.in);
            int tcase = scanner.nextInt();
            while(tcase-- > 0) {
                //init
                init();

                n = scanner.nextInt();
                m = scanner.nextInt();
                for(int i = 0; i < n; i++) {
                    seqN[i] = scanner.nextInt();
                }

                for(int i = 0; i < m; i++) {
                    seqM[i]  = scanner.nextInt();
                }

                int result = kmp();
                System.out.println(result);
            }
        }

        private int kmp() {
            next();
            //debug();
            for(int i = 0, j = 0; i < n; i++) {
                if(seqN[i] == seqM[j]) {
                    j++;
                    if(j >= m) {
                        return i - j + 2;
                    }
                    continue;
                }

                while(j != 0 && seqM[j] != seqN[i]) {
                    j = next[j -1];
                }

                if(j != 0 && seqM[j] == seqN[i]) {
                    j++;
                }
            }
            return -1;
        }

        private void next() {
            next[0] = 0;
            for(int i = 1; i < m; i++) {
                int k = next[i-1];
                while(k != 0 && seqM[i] != seqM[k]) k = next[k - 1];
                if(seqM[i] == seqM[k]) {
                    next[i] = k + 1;
                } else {
                    next[i] = 0;
                }
            }
        }

        private void debug() {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++) {
                sb.append(next[i]);
            }
            System.out.println(sb.toString());
        }

    }
}