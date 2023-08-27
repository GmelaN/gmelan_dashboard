package com.dashboard.gmelan.dataStructure;
import java.util.List;


public class News {
    private String newsTitle;
    private List<Article> relatedArticles;
    private String newsUrl;
    private String publicationTime;
    private String newspaperName;

    // 생성자
    public News(String newsTitle, List<Article> relatedArticles, String newsUrl, String publicationTime, String newspaperName) {
        this.newsTitle = newsTitle;
        this.relatedArticles = relatedArticles;
        this.newsUrl = newsUrl;
        this.publicationTime = publicationTime;
        this.newspaperName = newspaperName;
    }

    // Getter 메서드들
    public String getNewsTitle() {
        return newsTitle;
    }

    public List<Article> getRelatedArticles() {
        return relatedArticles;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    // 내부 Article 클래스
    public static class Article {
        private String articleUrl;
        private String articleTitle;

        public Article(String articleUrl, String articleTitle) {
            this.articleUrl = articleUrl;
            this.articleTitle = articleTitle;
        }

        public String getArticleUrl() {
            return articleUrl;
        }

        public String getArticleTitle() {
            return articleTitle;
        }
    }
}
