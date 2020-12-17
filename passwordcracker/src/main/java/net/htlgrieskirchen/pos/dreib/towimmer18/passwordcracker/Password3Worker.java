package net.htlgrieskirchen.pos.dreib.towimmer18.passwordcracker;

import java.util.Random;

public class Password3Worker implements Runnable {
    @Override
    public void run() {
        // Butzemann
        Random r = new Random();
        while (App.password == null) {
            String guess = App.creatures.get(r.nextInt(App.creatures.size()));
            if (StringUtil.applySha256(guess).equals(App.passwordEncrypted)) {
                App.password = guess;
            }
        }   
    }
}
