//package com.dashboard.gmelan.news.service;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import com.dashboard.gmelan.news.entity.NewsEntity;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.jsoup.Jsoup;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.rometools.rome.feed.synd.SyndEntry;
//import com.rometools.rome.feed.synd.SyndFeed;
//import com.rometools.rome.io.SyndFeedInput;
//
//import java.io.StringReader;
//
//
///**
// * Service class for fetching news from <a href="https://news.google.com/home?hl=ko&gl=KR&ceid=KR:ko">Google News RSS feed.</a>
// * @author gmelan
// * @since  1.0.0-SNAPSHOT
// * @see <a href="https://velog.io/gmelan">author's blog</a>
// */
//
//@Service
//public class NewsService {
//    /**
//     * <p>fetch news from <a href="https://news.google.com/home?hl=ko&gl=KR&ceid=KR:ko">Google News RSS feed.</a></p>
//     * <p>if there's an error while fetching news, prints exception's content.</p>
//     * <p>if there's no contents on fetched feed, returns an empty list.</p>
//     *
//     * @return ArrayList&lt;News&gt; newsList
//     * @see NewsEntity <p>News DataStructure class</p>
//     */
//    public ArrayList<NewsEntity> getGoogleNews() {
//        // TODO
//        // - change library to JSoup
//
//        // URL 주소
//        final String FEED_URL = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko";
//
//        // 반환할 뉴스 리스트
//        ArrayList<NewsEntity> newsEntityList = new ArrayList<>();
//
//        try {
//            // RestTemplate으로 RSS 피드 불러오기
//            RestTemplate restTemplate = new RestTemplate();
//            String rssXml = restTemplate.getForObject(FEED_URL, String.class);
//
//            // 불러온 피드의 내용이 없는 경우 빈 뉴스 리스트 반환
//            if (rssXml == null) {
//                return newsEntityList;
//            }
//
//            // RSS feed 파싱
//            SyndFeedInput input = new SyndFeedInput();
//            SyndFeed feed = input.build(new StringReader(rssXml));
//
//            for (SyndEntry entry : feed.getEntries()) {
//                NewsEntity article = new NewsEntity();
//
//                // 대부분의 제목 행은 "제목-언론사" 형태임
//                String[] titleData = entry.getTitle().split(" - ");
//                String title = titleData[0];
//                article.setTitle(title);
//
//                // contents 파싱
//                String rawContent = entry.getDescription().getValue();
//
//                // 첫 번째 요소는 제목 요소와 같고, 마지막 요소는 'Google 뉴스에서 보기'임
//                Elements subTitles = Jsoup.parse(rawContent).select("a");
//                for(int i = 1; i < subTitles.size() - 1; i++) {
//                    Element link = subTitles.get(i);
//
//                    // 각 기사의 링크 파싱
//                }
//                article.setCompany(rawContent);
//
//                String url = entry.getLink();
//                article.setUrl(url);
//                String publicationTime = entry.getPublishedDate().toString();
//                article.setPublished_at(new Timestamp(Timestamp.parse(publicationTime)));
//
//                article.setFetched_at(new Timestamp(Timestamp.parse(publicationTime))); // 임시 코드임
//                article.setCategory("기본 카테고리");
//
//                // 개재 신문사가 제목 문자열에서 파악되지 않은 경우 "Google News"
//                String newspaperName = titleData.length > 1 ? titleData[1] : "Google News";
//                article.setCompany(newspaperName);
//
//                // 뉴스 데이터를 객체로 생성하여 리스트에 추가
//                newsEntityList.add(article);
//            }
//        }
//
//        catch (Exception e) {
//            System.err.printf("getGoogleNews: there was an error while fetching news: %s", e);
//        }
//
//        return newsEntityList;
//    }
//}
