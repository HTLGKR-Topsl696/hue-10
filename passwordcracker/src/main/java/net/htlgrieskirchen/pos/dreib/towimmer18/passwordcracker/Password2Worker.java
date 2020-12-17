package net.htlgrieskirchen.pos.dreib.towimmer18.passwordcracker;

import java.util.Random;

public class Password2Worker implements Runnable {
    @Override
    public void run() {
        // l1nuX
        Random r = new Random();
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        while (App.password == null) {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < 5; ++i) {
                guess.append(letters[r.nextInt(letters.length)]);
            }
            if (StringUtil.applySha256(guess.toString()).equals(App.passwordEncrypted)) {
                App.password = guess.toString();
            }
        }
    }
}
