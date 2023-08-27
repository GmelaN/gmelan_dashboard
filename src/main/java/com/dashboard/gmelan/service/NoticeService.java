package com.dashboard.gmelan.service;


import java.util.ArrayList;
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

@Service
public class NoticeService {
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " + 
        "AppleWebKit/537.36 (KHTML, like Gecko) " +
        "Chrome/80.0.3987.163 Safari/537.36";

    public ArrayList<Notice> getNotice() {
        ArrayList<Notice> noticeList = new ArrayList<>();

        noticeList.addAll(getJinjuNotices());
        noticeList.addAll(getGNUNotices());

        Collections.sort(noticeList, new Comparator<Notice>() {
            @Override
            public int compare(Notice notice1, Notice notice2) {
                return notice1.getDate().compareTo(notice2.getDate());
            }
        });

        return noticeList;
    }

    private ArrayList<Notice> getJinjuNotices() {
        String url = "https://young.jinju.go.kr/young/board/notice/list/0";
        ArrayList<Notice> jinjuNoticeList = new ArrayList<>();

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
                // Extract relevant information from each notice element
                Element titleElement = noticeElement.selectFirst(".bb-tit");
                String title = titleElement.text();
                String link = noticeElement.select("a").attr("abs:href");

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
                String dateRawString = noticeElement.selectFirst(".bb-date").text();
                Date date = new Date();

                try {
                    date = dateFormatter.parse(dateRawString);
                }
                catch(ParseException e) {
                    e.printStackTrace();
                    // 날짜 정보 파싱에 실패하면 1970-01-01로 표시
                    date = new Date(0);
                }

                Notice notice = new Notice(title, link, date, title, "진주청년플랫폼");
                jinjuNoticeList.add(notice);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jinjuNoticeList;
    }

    private ArrayList<Notice> getGNUNotices() {
        ArrayList<Notice> GNUNoticeList = new ArrayList<>();
        try {
            // URL 주소
            String url = "https://www.gnu.ac.kr/cs/na/ntt/selectRssFeed.do?mi=6694&bbsId=2351";

            // Connect to the URL and fetch the HTML content
            Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();

            // Select the elements containing the notice list
            Elements noticeElements = doc.select("item");

            // Date formatter
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

            // Iterate through the notice elements
            for (Element noticeElement : noticeElements) {
                // Extract relevant information from each notice element
                String title = noticeElement.select("title").text();
                String link = noticeElement.select("link").text();
                String rawDateString = noticeElement.selectFirst("pubDate").text();
                Date date = dateFormat.parse(rawDateString);

                Notice notice = new Notice(title, link, date, title, "GNU");
                GNUNoticeList.add(notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return GNUNoticeList;
    }
}
