package com.crud.app.webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WebsiteScraper  {
    public Document getDocumentFromURL(URL resourceUrl) throws IOException {
        return  Jsoup.connect(resourceUrl.toString()).get();
    }
    public Map<String, Integer> extractOptionTextsData(Document document) {
        Map<String, Integer> optionTextsWithData = new LinkedHashMap<>(); // Use LinkedHashMap to preserve order
        Elements optionElements = document.select("select option");

        for (Element optionElement : optionElements) {
            String optionText = optionElement.text().replace("\u00A0", "").trim();
            int nbspCount = countNbspOccurrences(String.valueOf(optionElement));
            optionTextsWithData.put(optionText, nbspCount);

        }

        return optionTextsWithData;
    }

    private int countNbspOccurrences(String text) {
        Pattern pattern = Pattern.compile("&nbsp;");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

}

