package com.dashboard.gmelan.dataStructure;


public class NewsList {
    private String title;
    private String content;
    private String url;
    private String publicationDate;
    private String newspaperName;

    // 생성자
    public NewsList(String title, String content, String url, String publicationDate, String newspaperName) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.publicationDate = publicationDate;
        this.newspaperName = newspaperName;
    }

    // Getter 메서드들
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getNewspaperName() {
        return newspaperName;
    }
}
