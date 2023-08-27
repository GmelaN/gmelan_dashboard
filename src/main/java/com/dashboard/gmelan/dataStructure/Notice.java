package com.dashboard.gmelan.dataStructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice {
    private String title;
    private String link;
    private Date date;
    private String content;
    private String author;

    public Notice(String title, String link, Date date, String content, String author) {
        this.title = title;
        this.link = link;
        this.date = date;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
