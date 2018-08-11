package org.powerhackers;

import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        Solution solution = new Solution();
        solution.solve();
    }

    public static class Solution {
        private static final int N = 10010;

        private String seqN = null;
        private String seqM = null;
        private final int next[] = new int[N];
        private int m, n;

        public void solve() {
            Scanner scanner = new Scanner(System.in);
            int tcase = scanner.nextInt();
            scanner.nextLine();
            while(tcase-- > 0) {
                seqM = scanner.nextLine();
                seqN = scanner.nextLine();
                n = seqN.length();
                m = seqM.length();
                int result = kmp();
                System.out.println(result);
            }
        }

        private int kmp() {
           next();
           //debug();
           int result = 0;
           for(int i = 0, j = 0; i < n; i++) {
              if(seqN.charAt(i) == seqM.charAt(j)) {
                  j++;
                  if(j >= m) {
                      result++;
                      j = next[j-1];
                  }
                  continue;
              }

              while(j != 0 && seqN.charAt(i) != seqM.charAt(j)) {
                  j = next[j-1];
              }
              if(seqN.charAt(i) == seqM.charAt(j)) {
                  j++;
              }
           }
           return result;
        }

        private void next() {
           next[0] = 0;
           for(int i = 1; i < m; i++) {
              int j = next[i-1];
              while(j != 0 && seqM.charAt(i) != seqM.charAt(j)) {
                  j = next[j-1];
              }

              if(seqM.charAt(i) == seqM.charAt(j)) {
                  next[i] = j + 1;
              } else {
                  next[i] = 0;
              }
           }
        }

        private void debug() {
            StringBuilder sb = new StringBuilder();
            sb.append(seqN).append(";").append(seqM).append("\n");
            for(int i = 0; i < m; i++){
               sb.append(next[i]).append(",");
            }
            System.out.println(sb.toString());
        }
    }
}

