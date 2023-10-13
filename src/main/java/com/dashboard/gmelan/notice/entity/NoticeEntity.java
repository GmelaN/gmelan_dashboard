package com.dashboard.gmelan.notice.entity;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class NoticeEntity {
    private final String title;
    private final String link;
    private final Date date;
    private final String content;
    private final String author;

    public NoticeEntity(String title, String link, Date date, String content, String author) {
        this.title = title;
        this.link = link;
        this.date = date;
        this.content = content;
        this.author = author;
    }

    public String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
