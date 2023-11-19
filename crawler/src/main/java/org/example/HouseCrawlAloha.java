package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HouseCrawlAloha {
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    private static final String URL = "https://alonhadat.com.vn";

    public static void crawler() throws IOException, InterruptedException {
        for (int i = 1; i < 5; i++) {
            Thread.sleep(2000);
            Document doc = null;
            Document detail = null;
            try {
                doc = Jsoup.connect(URL + "/can-ban-nha/trang-" + i + ".htm").get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Elements links = doc.select(".content-item .ct_title a");
            for (Element link : links) {
                try {
                    detail = Jsoup.connect(URL + link.attr("href")).get();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Element diachi = detail.select(".address .value").first();
                Element gia = detail.select(".price .value").first();
                Element dientich = detail.select(".square .value").first();

                System.out.println(diachi.text() + " " + gia.text() + " " + dientich.text());
            }
//        Document doc = Jsoup.connect("https://alonhadat.com.vn/nha-kinh-doanh-oto-tranh-45m-8-ty-mt-5m-my-dinh-cho-thue-full-noi-that-13685269.html").get();
//        Element diachi = doc.select(".address .value").first();
//        System.out.println(diachi.text());
        }
    }
}
