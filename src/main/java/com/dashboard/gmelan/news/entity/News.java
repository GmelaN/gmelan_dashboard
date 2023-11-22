package com.dashboard.gmelan.news.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private long id;
    private String title;
    private Timestamp publishedAt;
    private String content;
    private String url;
    private Timestamp fetchedAt;
    private String category;
    private String company;
    private String reporter;
}
