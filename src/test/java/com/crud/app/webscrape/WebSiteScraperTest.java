package com.crud.app.webscrape;

import com.crud.app.webscrape.WebsiteScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.*;




import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WebSiteScraperTest {

    //Verify that the Options are correcty extracted
    @Test
    public void testExtractOptionData() {

        // Sample HTML string
        String html = """
                <select multiple="" size="5">
                    <option value="1">Manufacturing</option>
                    <option value="19">&nbsp;&nbsp;&nbsp;&nbsp;Construction materials</option>
                </select>
                """;

        // Parse HTML string to create a Document object
        Document document = Jsoup.parse(html);

        // Create an instance of the WebsiteScraper class
        WebsiteScraper scraper = new WebsiteScraper();

        // Call the extractOptionData() method
        List<WebsiteScraper.OptionData> optionDataList = scraper.extractOptionData(document);

        // Verify the expected results
        assertEquals(2, optionDataList.size());

        WebsiteScraper.OptionData optionData1 = optionDataList.get(0);
        assertEquals("Manufacturing", optionData1.optionText());
        assertEquals("1", optionData1.optionValue());

        WebsiteScraper.OptionData optionData2 = optionDataList.get(1);
        assertEquals("Construction materials", optionData2.optionText());
        assertEquals("19", optionData2.optionValue());
    }
}