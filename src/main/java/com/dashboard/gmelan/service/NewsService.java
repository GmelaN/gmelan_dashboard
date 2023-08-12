package com.dashboard.gmelan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dashboard.gmelan.dataStructure.NewsList;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

import java.io.StringReader;


@Service
public class NewsService {
    public List<NewsList> getGoogleNews() {
        List<NewsList> newsList = new ArrayList<>();

        try {
            // URL 주소
            String feedUrl = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko";

            // RestTemplate으로 RSS 피드 불러오기
            RestTemplate restTemplate = new RestTemplate();
            String rssXml = restTemplate.getForObject(feedUrl, String.class);

            // RSS feed 파싱
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new StringReader(rssXml));

            for (SyndEntry entry : feed.getEntries()) {
                String[] titleData = entry.getTitle().split(" - ");
                String title = titleData[0];
                String content = entry.getDescription().getValue();
                String url = entry.getLink();
                String publicationTime = entry.getPublishedDate().toString();

                String newspaperName = "Google News";
                if(titleData.length > 1) {
                    newspaperName = titleData[1];
                }


                // 뉴스 데이터를 객체로 생성하여 리스트에 추가
                newsList.add(new NewsList(title, content, url, publicationTime, newspaperName));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
