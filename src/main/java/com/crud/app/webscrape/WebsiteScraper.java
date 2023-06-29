package com.crud.app.webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteScraper {
    public record OptionData(String optionText, String optionValue, int nbspCount) {
    }

    public Document getDocumentFromURL(URL resourceUrl) throws IOException {
        return Jsoup.connect(resourceUrl.toString()).get();
    }

    public List<OptionData> extractOptionData(Document document) {
        List<OptionData> optionDataList = new ArrayList<>();
        Elements optionElements = document.select("select option");

        
            for (Element optionElement : optionElements) {
                if (optionElement != null) {
                    String optionText = optionElement.text().replace("\u00A0", "").trim();
                    int nbspCount = countNbspOccurrences(String.valueOf(optionElement));
                    String optionValue = optionElement.val();

                    OptionData optionData = new OptionData(optionText, optionValue, nbspCount);
                    optionDataList.add(optionData);
                }
            }

        return optionDataList;
    }

    private int countNbspOccurrences(String text) {
        Pattern pattern = Pattern.compile("&nbsp;");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count / 4;
    }

}

