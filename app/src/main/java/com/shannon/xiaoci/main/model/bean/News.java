package com.shannon.xiaoci.main.model.bean;

/**
 * 从网络获取到的新闻
 *
 */
public class News {


    //图片的地址
    private String cover_url;
    //新闻的标题
    private String title;
    //新闻的描述
    private String describle;
    //新闻的具体内容
    private String content;

    //新闻的地址
    private String content_url;


    public News() {
    }

    public News(String cover_url, String content_url, String content, String describle, String title) {
        this.cover_url = cover_url;
        this.content_url = content_url;
        this.content = content;
        this.describle = describle;
        this.title = title;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
