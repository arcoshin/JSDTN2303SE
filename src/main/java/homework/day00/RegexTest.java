package homework.day00;

import java.util.Scanner;

public class RegexTest {
    public static void main(String[] args) {
        String regexPwd = "[\\w]{8,20}";
        String regex0_9 = "[^0-9]+";//無數字
        String regexA_Z = "[^A-Z]+";//無大寫
        String regexa_z = "[^a-z]+";//無小寫
        while (true) {
            System.out.println("ENTER...");
            String pwd = new Scanner(System.in).nextLine();

            System.out.println(pwd.matches(regexPwd)
                    && !pwd.matches(regex0_9) && !pwd.matches(regexA_Z) && !pwd.matches(regexa_z));

            System.out.print("數字檢測:" + !pwd.matches(regex0_9));
            System.out.print(",");
            System.out.print("大寫檢測:" + !pwd.matches(regexA_Z));
            System.out.print(",");
            System.out.print("小寫檢測:" + !pwd.matches(regexa_z));
            System.out.println();
        }
    }
}
