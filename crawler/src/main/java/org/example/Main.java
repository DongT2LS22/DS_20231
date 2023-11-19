package org.example;


import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class Main {
    public static void main(String[] args) throws IOException {
        String csvFilePath = "src/main/resources/data.csv";
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath, true))) {
            csvWriter.writeNext(new String[]{"floors", "rooms", "width", "length", "size", "area_name", "region_name", "price", "living_size", "toilets", "ward_name","direction","directionNumber","balconydirection","balconydirectionNumber"});
            for (int k = 0; k < 20; k += 10) {
                for (int i = 0; i < 1000; i++) {
                    String URL = "https://gateway.chotot.com/v1/public/ad-listing?cg=" + (1010 + k) + "&o=" + i * 100 + "&st=s,k&limit=100&key_param_included=true";
                    Document doc = Jsoup
                            .connect(URL)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                            .ignoreContentType(true)
                            .get();

                    // Replace the JSON data string with your actual JSON data
                    String jsonData = doc.text();
                    // Parse JSON data
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray adsArray = jsonObject.getJSONArray("ads");

                    // Write data rows
                    for (int j = 0; j < adsArray.length(); j++) {
                        JSONObject ad = adsArray.getJSONObject(j);
                        JSONArray params = ad.getJSONArray("params");
                        String direction = "";
                        String balconydirection = "";
                        for (int t = 0; t < params.length();t++) {
                            JSONObject param = params.getJSONObject(t);
                            if(param.optString("id").equals("direction")){
                                System.out.println(param.get("value"));
                                System.out.println(param.get("id"));
                                direction = (String) param.get("value");
                            }
                            if(param.optString("id").equals("balconydirection")){
                                System.out.println(param.get("value"));
                                System.out.println(param.get("id"));
                                balconydirection = (String) param.get("value");
                            }
                        }

                        csvWriter.writeNext(new String[]{
                                ad.optString("floors", ""),
                                ad.optString("rooms", ""),
                                ad.optString("width", ""),
                                ad.optString("length", ""),
                                ad.optString("size", ""),
                                ad.optString("area_name", ""),
                                ad.optString("region_name", ""),
                                String.valueOf(ad.optLong("price", 0)),
                                String.valueOf(ad.optLong("living_size", 0)),
                                String.valueOf(ad.optInt("toilets", 0)),
                                ad.optString("ward_name", ""),
                                direction,
                                String.valueOf(ad.optLong("direction",0)),
                                balconydirection,
                                String.valueOf(ad.optLong("balconydirection",0))
                        });
                    }

                    System.out.println("CSV data has been written to " + csvFilePath);
                }
            }
            try {
                // Dừng vòng lặp trong 2 giây (2000 mili giây)
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}