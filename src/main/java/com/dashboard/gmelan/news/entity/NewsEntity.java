package com.dashboard.gmelan.news.entity;
import lombok.Getter;

import java.util.List;


/**
 * @author gmelan
 * @since  1.0.0-SNAPSHOT
 * @see <a href="https://velog.io/gmelan">author's blog</a>
 * <p>DataStructure class for loading News.</p>
 * <p>
 *     Elements:
 *     <ul>
 *         <li>String newsTitle</li>
 *         <li>List&lt;Article&gt; relatedArticles</li>
 *         <li>String newsUrl</li>
 *         <li>String publicationTime</li>
 *         <li>String newspaperName</li>
 *     </ul>
 *     this class has <a href="https://projectlombok.org/">lombok</a> Getter annotation.
 *     <br>for example you can get newsUrl by News.getNewsUrl()
 * </p>
 *
 */
@Getter
public class NewsEntity {
    // Getter
    private final String newsTitle;
    private final List<Article> relatedArticles;
    private final String newsUrl;
    private final String publicationTime;
    private final String newspaperName;

    // 생성자
    /**
     * <p>
     *     constructor of News class.
     * </p>
     * @param newsTitle news title string.
     * @param relatedArticles related articles list&lt;Article&gt;.
     * @param newsUrl url of news.
     * @param newspaperName name of publication media company.
     * @param publicationTime date of publication. not used yet.
     * @see <a href="https://velog.io/gmelan">author's blog</a>
     */
    public NewsEntity(String newsTitle, List<Article> relatedArticles, String newsUrl, String publicationTime, String newspaperName) {
        this.newsTitle = newsTitle;
        this.relatedArticles = relatedArticles;
        this.newsUrl = newsUrl;
        this.publicationTime = publicationTime;
        this.newspaperName = newspaperName;
    }

    // 내부 Article 클래스
    /**
     * @author gmelan
     * @since  1.0.0-SNAPSHOT
     * @see <a href="https://velog.io/gmelan">author's blog</a>
     * <p>DataStructure class for loading Articles.</p>
     * <p>
     *     Elements:
     *     <ul>
     *         <li>String articleUrl</li>
     *         <li>String articleTitle</li>
     *     </ul>
     *     this class has <a href="https://projectlombok.org/">lombok</a> Getter annotation.
     *     <br>for example you can get articleUrl by Article.articleUrl()
     * </p>
     *
     */
    @Getter
    public static class Article {
        private final String articleUrl;
        private final String articleTitle;

        // 생성자
        /**
         * <p>
         *     constructor of News class.
         * </p>
         * @param articleTitle article title string.
         * @param articleUrl url of article.
         * @see <a href="https://velog.io/gmelan">author's blog</a>
         */
        public Article(String articleUrl, String articleTitle) {
            this.articleUrl = articleUrl;
            this.articleTitle = articleTitle;
        }

    }
}
