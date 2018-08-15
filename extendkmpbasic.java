package org.powerhackers;

import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        Solution solution = new Solution();
        solution.solve();
    }

    public static class Solution {
        private static final int N = 105;
        private String S = null;
        private String T = null;
        private int n = 0;
        private int next[] = new int[N];
        private int extend[] = new int[N];
        public void solve() {
            Scanner scanner = new Scanner(System.in);
            int tcase = scanner.nextInt();
            while(tcase-- > 0) {
                n = scanner.nextInt();
                scanner.nextLine();
                String S = scanner.nextLine();
                String T = scanner.nextLine();

                kmp();
            }
        }

        private void next(String str) {
            next[0] = 0;
            int size = str.length();
            for(int i = 0, j = 1; i < size && j< size; i++, j++ ) {
                if(str.charAt(i) != str.charAt(j)) {
                    break;
                }
                next[1]++;
            }
            int ps = 1;

            for(int i = 2; i < size; i++) {
               int len = next[i - ps];
               if(i + len < next[ps] + ps) {
                   next[i] = len;
               } else {
                   int j = ps + next[ps] - i;
                   if(j < 0) j = 0;
                   while(i + j < size && str.charAt(i + j) == str.charAt(j))
                       j ++;
                   next[i] = j;
                   ps = i;
               }
            }
        }

        private void kmp() {
            next(T);
            int tlen = T.length();
            int slen = S.length();
            for(int i = 0, j =0; i < slen && j < tlen; i++, j++) {
               extend[0]++;
            }
            int ps = 0;
            for(int i = 1; i < slen; i++) {
                int len = extend[ps - i];
                if(i + len < ps + extend[ps] ) {
                   extend[i] = len;
                } else {
                    int j = extend[ps] + ps - i;
                    while(i + j < slen && j < tlen && T.charAt(j) == S.charAt(i+j)) {
                        j++;
                    }
                    extend[i] = j;
                    ps = i;
                }
            }
        }
    }
}

