package com.dashboard.gmelan.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dashboard.gmelan.dataStructure.Notice;


/**
 * Service class for fetching notices from <a href="https://gnu.ac.kr">GNU</a>, <a href="https://young.jinju.go.kr/young/">Jinju Youth Platform.</a>
 * @author gmelan
 * @since  1.0.0-SNAPSHOT
 * @see <a href="https://velog.io/gmelan">author's blog</a>
 *
 */
@Service
public class NoticeService {
    // user agent string
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " + 
        "AppleWebKit/537.36 (KHTML, like Gecko) " +
        "Chrome/80.0.3987.163 Safari/537.36";

    /**
     * Retrieves a list of notices from the GNU, Jinju Youth Platform.
     *
     * This method fetches notices from two sources, the Jinju notices and the GNU notices.
     * The fetched notices are then combined into a single ArrayList and sorted by date in ascending order.
     * @author gmelan
     * @since  1.0.0-SNAPSHOT
     * @see <a href="https://velog.io/gmelan">author's blog</a>
     * @see <a href="https://young.jinju.go.kr/young/">Jinju Youth Platform</a>
     * @see <a href="https://gnu.ac.kr">Gyeongsang National University</a>
     * @return ArrayList&lt;Notice&gt;: A list of Notice objects containing information about notices.
     */
    public ArrayList<Notice> getNotice() {
        ArrayList<Notice> noticeList = new ArrayList<>();

        noticeList.addAll(getJinjuNotices());
        noticeList.addAll(getGNUNotices());

        noticeList.sort(new Comparator<Notice>() {
            @Override
            public int compare(Notice notice1, Notice notice2) {
                // multify -1 for descending order
                return -1 * notice1.getDate().compareTo(notice2.getDate());
            }
        });

        return noticeList;
    }

    
    /**
     * <p>Get a list of notices from Jinju Youth Platform.</p>
     * <p>If parsing date string from fetched content fails, date will overridden default date: 1970-01-01.</p>
     * @author gmelan
     * @since  1.0.0-SNAPSHOT
     * @return ArrayList&lt;Notice&gt;: A list of Notice objects containing information of Jinju Youth Platform notices.
     * @see <a href="https://young.jinju.go.kr/young/">Jinju Youth Platform</a>
     */
    private ArrayList<Notice> getJinjuNotices() {
        String url = "https://young.jinju.go.kr/young/board/notice/list/0";
        ArrayList<Notice> jinjuNoticeList = new ArrayList<>();

        // get duration for fetching
        Date fetchEndDate = new Date();

        // get date of 30 days ago
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fetchEndDate);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date fetchStartDate = calendar.getTime();

        try {
            // Connect to the URL and fetch the HTML content
            Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();

            // remove table's head row
            Element headElement = doc.selectFirst(".bb-head");
            if(headElement != null) {
                headElement.remove();
            }
            
            // Select the elements containing the notice list
            Elements noticeElements = doc.select(".bb-item");

            // Iterate through the notice elements
            for (Element noticeElement : noticeElements) {
                // 빈 element인 경우 다음으로 넘어감
                if(noticeElement == null) {
                    continue;
                }

                // Extract relevant information from each notice element
                Element titleElement = noticeElement.selectFirst(".bb-tit");
                Element dateElement = noticeElement.selectFirst(".bb-date");

                // 빈 element인 경우 다음으로 넘어감
                if(titleElement == null || dateElement == null) {
                    continue;
                }

                // title, link text
                String title = titleElement.text();
                String link = noticeElement.select("a").attr("abs:href");

                // format date string to Date object
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                String rawDateString = dateElement.text();
                Date date;

                try {
                    date = dateFormatter.parse(rawDateString);
                } catch (ParseException e) {
                    System.err.println("getJinjuNotices: Parsing " + rawDateString + "failed. failing back to 1970-01-01.");
                    date = new Date(0);
                }

                // only fetch contents uploaded in target duration
                // contents without date information also included
                if(date.after(fetchStartDate) || date.equals(new Date(0))) {
                    Notice notice = new Notice(title, link, date, title, "진주청년플랫폼");
                    jinjuNoticeList.add(notice);
                }
            }

        } catch (Exception e) {
            System.err.printf("getJinjuNotices: there was an error while fetching notices: %s", e.toString());
        }

        return jinjuNoticeList;
    }


    /**
     * <p>Get a list of notices from GNU.</p>
     * <p>If parsing date string from fetched content fails, date will overridden default date: 1970-01-01.</p>
     * @author gmelan
     * @since  1.0.0-SNAPSHOT
     * @return ArrayList&lt;Notice&gt;: A list of Notice objects containing information of GNU notices.
     * @see <a href="https://gnu.ac.kr">Gyeongsang National University</a>
     * @see <a href="https://cs.gnu.ac.kr">GNU Computer Science dept.</a>
     */
    private ArrayList<Notice> getGNUNotices() {
        ArrayList<Notice> GNUNoticeList = new ArrayList<>();

        // get target duration
        Date fetchEndDate = new Date();

        // get 30 days ago
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fetchEndDate);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date fetchStartDate = calendar.getTime();

        // URL
        final String[] URL = {
            "GNU-CS https://www.gnu.ac.kr/cs/na/ntt/selectRssFeed.do?mi=6694&bbsId=2351",  // GNU-CS
            "GNU-행사 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1291&bbsId=1047", // GNU-행사
            "GNU-기관 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1126&bbsId=1028", // GNU-기관
            "GNU-학사 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1127&bbsId=1029", // GNU-학사
            "GNU-장학 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1376&bbsId=1075", // GNU-장학
            "GNU-채용 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1129&bbsId=1030", // GNU-채용
            "GNU-입법예고 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=11505&bbsId=3559",// GNU-입법예고
            "GNU-학술/행사 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1131&bbsId=1032", // GNU-학술/행사
            "GNU-외부기관 http://www.gnu.ac.kr/main/na/ntt/selectRssFeed.do?mi=1132&bbsId=1033", // GNU-외부기관
        };

        // will store RSS original document
        Document doc = Document.createShell("");

        // Date formatter
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        // iterate URL array
        for(String urlString : URL) {
            String[] urlStringSplit = urlString.split(" ");
            String author = urlStringSplit[0];
            String url = urlStringSplit[1];

            try {
                // Connect to the URL and fetch the HTML content
                doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
            } catch (Exception e) {
                System.err.printf("getGNUNotices: there was an error while fetching notices: %s", e.toString());
                continue;
            }
            // Select the elements containing the notice list
            Elements noticeElements = doc.select("item");

            // Iterate through the notice elements
            for (Element noticeElement : noticeElements) {
                if(noticeElement == null) { continue; }

                // Extract relevant information from each notice element
                String title = noticeElement.select("title").text();
                String link = noticeElement.select("link").text();
                String rawDateString = noticeElement.selectFirst("pubDate").text();

                Date date;

                try {
                    date = dateFormatter.parse(rawDateString);
                } catch (Exception e) {
                    System.err.printf("getGNUNotices: there was an error while fetching notices: %s", e.toString());
                    date = new Date(0);
                }

                // only fetch contents within target duration
                // contents without date information also included
                if(date.after(fetchStartDate) || date.equals(new Date(0))) {
                    Notice notice = new Notice(title, link, date, title, author);
                    GNUNoticeList.add(notice);
                }
            }
        }
        return GNUNoticeList;
    }
}
