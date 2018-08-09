package org.powerhackers;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
   public static void main(String []args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      scanner.nextLine();
      String stra = scanner.nextLine();
      String strb = scanner.nextLine();

      char lista[] = strb.toCharArray();
      char listb[] = stra.toCharArray();

      List<Integer> res = new ArrayList<Integer>();
      int size = 0;
      for(int i = 0; i < lista.length; i++){
         int j = listb.length - 1;
         //System.out.println(lista[i]);
         boolean flag = false;
         for(; j >=i; j--) {
             if(lista[i] == listb[j]) {
                 flag = true;
                 break;
             }
         }

         if(!flag) {
             size = -1;
             break;
         } else {
             for(;j > i; j--) {
                 res.add(j);
                 char a = listb[j];
                 listb[j] = listb[j-1];
                 listb[j-1] = a;
             }
         }
      }

      if(size != -1) {
          size = res.size();
      }
      System.out.println(size);
      StringBuilder sb = new StringBuilder();
      if(size > 0) {
         sb.append(res.get(0));
      }

      if(size > 1) {
         for(int i = 1; i < size; i++) {
             sb.append(" ").append(res.get(i));
         }
      }

      if(size > 0) {
          System.out.println(sb.toString());
      }
   }
}
