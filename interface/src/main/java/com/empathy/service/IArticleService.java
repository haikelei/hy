package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.account.Account;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.account.bo.ProveListAddBo;
import com.empathy.domain.account.bo.ProveListBo;
import com.empathy.domain.account.bo.UpdPasswordBo;
import com.empathy.domain.article.Article;
import com.empathy.domain.article.bo.ArticleAddBo;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.bo.ArticleUpdBo;
import com.empathy.domain.live.bo.ProveAccountUpdBo;
import com.empathy.domain.live.bo.ProveFreezeBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Created by MI on 2018/5/9.
 */
public interface IArticleService extends BaseService<Article, Long, PageBo> {

    RspResult addArticle(ArticleAddBo bo);

    RspResult updArticle(ArticleUpdBo bo);

    RspResult findArticle(ArticleFindBo bo);

    String findArticleCount(ArticleFindBo bo);

    RspResult findAllArticle(ArticleFindBo bo);

    RspResult findArticleById(Long id);

    RspResult addPoint(Long id,Long userId);

    RspResult addArticleByAlbum(ArticleAddBo bo);
}
