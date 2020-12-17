package net.htlgrieskirchen.pos.dreib.towimmer18.passwordcracker;

import java.util.Random;

public class Password1Worker implements Runnable {
    @Override
    public void run() {
        // PARSER
        Random r = new Random();
        char[] upperCaseletters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        while (App.password == null) {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < 6; ++i) {
                guess.append(upperCaseletters[r.nextInt(upperCaseletters.length)]);
            }
            if (StringUtil.applySha256(guess.toString()).equals(App.passwordEncrypted)) {
                App.password = guess.toString();
            }
        }
    }
}
