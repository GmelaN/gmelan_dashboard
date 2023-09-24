package com.dashboard.gmelan.dataStructure;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Notice {
    private final String title;
    private final String link;
    private final Date date;
    private final String content;
    private final String author;

    public Notice(String title, String link, Date date, String content, String author) {
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
