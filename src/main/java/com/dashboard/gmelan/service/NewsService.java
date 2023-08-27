package com.dashboard.gmelan.service;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dashboard.gmelan.dataStructure.News;
import com.dashboard.gmelan.dataStructure.News.Article;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

import java.io.StringReader;


@Service
public class NewsService {
    public ArrayList<News> getGoogleNews() {
        ArrayList<News> newsList = new ArrayList<>();

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

                // contents 파싱
                String rawContent = entry.getDescription().getValue();

                ArrayList<Article> contents = new ArrayList<>();

                // 첫 번째 요소는 제목 요소와 같고, 마지막 요소는 'Google 뉴스에서 보기'임
                Elements subTitles = Jsoup.parse(rawContent).select("a");
                for(int i = 1; i < subTitles.size() - 1; i++) {
                    Element link = subTitles.get(i);

                    // 각 기사의 링크 파싱
                    contents.add(new Article(link.attributes().get("href"), link.text()));
                }


                String url = entry.getLink();
                String publicationTime = entry.getPublishedDate().toString();

                // 개재 신문사가 제목 문자열에서 파악되지 않은 경우 "Google News"
                String newspaperName = "Google News";
                if(titleData.length > 1) {
                    newspaperName = titleData[1];
                }

                // 뉴스 데이터를 객체로 생성하여 리스트에 추가
                newsList.add(new News(title, contents, url, publicationTime, newspaperName));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
