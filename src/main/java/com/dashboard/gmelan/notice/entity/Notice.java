package com.dashboard.gmelan.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private long id;
    private String title;
    private Timestamp publishedAt;
    private String content;
    private String url;
    private String agency;
    private Timestamp fetchedAt;
}
