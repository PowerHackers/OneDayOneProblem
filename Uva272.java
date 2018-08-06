package org.powerhackers;


import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String line = null;
        boolean parentness = true;
        while(scan.hasNextLine()) {
           StringBuilder str = new StringBuilder();
           line = scan.nextLine();
           //System.out.println(line);
           for(int i = 0, len = line.length(); i < len; i++) {
               char a = line.charAt(i);
               if('"' == a && parentness) {
                   str.append("``");
                   parentness = !parentness;
               } else if('"' == a && !parentness) {
                   str.append("''");
                   parentness = !parentness;
               } else {
                   str.append(a);
               }
           }
           System.out.println(str.toString());
        }
        scan.close();
    }
}
