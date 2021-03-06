

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            // quick way to do input from the keyboard, now deprecated...
            //
            Scanner scan = new Scanner(System.in);
            System.out.print("Input your secret password : ");
            String pass = scan.next();
            String hash = byteArrayToHexString(main.computeHash(pass));
            System.out.println("the computed hash (hex string) : " + hash);
            boolean ok = true;
            String inputHash = "";
            while (ok) {
                System.out.print("Now try to enter a password : " );
                String reppass = scan.next();
                inputHash = byteArrayToHexString(main.computeHash(reppass));
                if (hash.equals(inputHash)){
                    System.out.println("You got it!");
                    ok = false;
                }
                else
                    System.out.println("Wrong, try again...!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] computeHash(String x)
            throws Exception
    {
        java.security.MessageDigest d =null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return  d.digest();
    }

    public static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}
