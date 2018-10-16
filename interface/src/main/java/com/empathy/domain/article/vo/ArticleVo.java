package com.empathy.domain.article.vo;

import com.empathy.domain.article.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by MI on 2018/6/7.
 */
@Getter@Setter
public class ArticleVo extends Article{

    private String username;

    private String url;

    private List<String> articleUrl;

    private Long recordId;

    private Integer status;//0平台 1主播

    private Double time;

    private Integer pointStatus;

    private Integer commentCount;

    private Long albumId;
    private String recordingName;
}
