package net.htlgrieskirchen.pos.dreib.towimmer18.passwordcracker;

import java.util.Random;

public class Password0Worker implements Runnable {
    @Override
    public void run() {
        // maus
        Random r = new Random();
        char[] lowerCaseletters = "acdefghijklmnopqrstuvwxyz".toCharArray();
        while (App.password == null) {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < 4; ++i) {
                guess.append(lowerCaseletters[r.nextInt(lowerCaseletters.length)]);
            }
            if (StringUtil.applySha256(guess.toString()).equals(App.passwordEncrypted)) {
                App.password = guess.toString();
            }
        }
    }
}
