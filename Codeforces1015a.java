package org.powerhackers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main( String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n, m;
        n = scan.nextInt();
        m = scan.nextInt();

        int markl[] = new int[m+1];
        int markr[] = new int[m+1];
        for(int i = 0; i < m+1; i++) {
            markl[i] = 0;
            markr[i] = 0;
        }

        for(int i = 0; i < n; i++) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            markl[l]++;
            markr[r]++;
        }

        int count = 0;
        int result = 0;
        List<Integer> lists = new ArrayList<Integer>();
        for(int i = 1; i <= m; i++){
            if(count == 0 && markl[i] == 0 && markr[i] == 0) {
                result++;
                lists.add(i);
                continue;
            }

            if(markl[i] > 0) {
                count += markl[i];
            }

            if(markr[i] > 0) {
                count -= markr[i];
            }
        }

        System.out.println(result);
        if(result > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(lists.get(0));
            for(int i = 1; i < result; i++){
                sb.append(" ").append(lists.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
