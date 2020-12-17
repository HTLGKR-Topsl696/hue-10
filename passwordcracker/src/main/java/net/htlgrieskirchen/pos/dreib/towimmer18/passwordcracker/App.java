package net.htlgrieskirchen.pos.dreib.towimmer18.passwordcracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Hello world!
 *
 */
public class App {

    public static String passwordEncrypted;
    public static String password;
    public static List<String> creatures = new ArrayList<String>();

    public static void main(String[] args) {
        new App().run();
    }

    public void loadEncryptedPassword() {
        try {
            passwordEncrypted = Files.lines(Paths.get("password3")).findFirst().get().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCreatures() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://de.wikipedia.org/wiki/Liste_von_Fabelwesen").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Element element : doc.body().select("li")) {
            String text = element.text();
            if (!text.contains("–")) continue;
            creatures.add(text.split("–")[0].trim());
        }
    }

    public void run() {
        loadEncryptedPassword();
        loadCreatures();

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        Future<?> pwFuture = null;
        for (int i = 0; i < cores; ++i) {
            pwFuture = executorService.submit(new Password3Worker());
        }
        try {
            pwFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Password: " + password);
        System.exit(0);
    }
}
