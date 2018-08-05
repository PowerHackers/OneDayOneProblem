package org.powerhackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Long> list = new ArrayList<>();
        long c = 0;
        for(int i = 0; i < n; i++) {
           long a = scanner.nextInt();
           long b = scanner.nextInt();
           list.add(a-b);
           c += a;
        }

        Collections.sort(list);
        c -= m;
        int res = 0;
        for(int i = list.size() - 1; i >= 0; i--) {
           long w = list.get(i);
           if(c <= 0)  {
               break;
           }
           c -= w;
           res++;
        }

        if(c <= 0) {
            System.out.print(res);
        } else {
            System.out.print(-1);
        }

    }
}
