package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.agreement.Agreement;
import com.empathy.domain.agreement.bo.*;
import com.empathy.domain.article.Article;
import com.empathy.domain.article.bo.ArticleAddBo;
import com.empathy.domain.article.bo.ArticleFindBo;
import com.empathy.domain.article.bo.ArticleUpdBo;
import com.empathy.domain.article.vo.ArticleVo;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.bidding.File;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAgreementService;
import com.empathy.service.IArticleService;
import com.empathy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by MI on 2018/5/9.
 */
@Service
public class ArticleService extends AbstractBaseService implements IArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private FileService fileService;


    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private BaseRecordingDao baseRecordingDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public RspResult addArticleByAlbum(ArticleAddBo bo) {
        Article article = new Article();
        BaseMember byId = baseMemberDao.findById(bo.getUserId());
        if(byId==null){
            return errorMo();
        }
        article.setCode(0);
        article.setUserId(bo.getUserId());
        if(!StringUtil.isNotEmpty(bo.getContent())){
            return error(1,"内容不能为空");
        }
        article.setContent(bo.getContent());
        if(StringUtil.isNotLongEmpty(bo.getRecordId())){

            article.setRecordId(bo.getRecordId());
        }
        articleDao.save(article);
        return success(article);
    }

    @Override
    public RspResult addPoint(Long id,Long userId) {
        try {

            articleDao.addPoint(id);
            articleDao.addPointInfo(id,userId);
            return success();
        }catch (Exception e){
            return errorNo();
        }
    }

    @Override
    public RspResult findArticleById(Long id) {

        ArticleVo articleVo = articleDao.findDetail(id);
        int commentCount = commentDao.findCountByDynamic(articleVo.getId());
        articleVo.setCommentCount(commentCount);
        FileCarBo fileCarBo = new FileCarBo();
        fileCarBo.setType("article");
        fileCarBo.setPurposeId(articleVo.getId());
        List<String> fileByPurposeIdAndTypeList = fileDao.findFileByPurposeIdAndTypeList1(fileCarBo);
        articleVo.setArticleUrl(fileByPurposeIdAndTypeList);
        Long createTime = articleVo.getCreateTime();
        double time = (System.currentTimeMillis() - createTime) / 1000 / 60 / 60;
        articleVo.setTime(time);
        if (StringUtil.isNotLongEmpty(articleVo.getRecordId())) {
            articleVo.setStatus(3);
            return success(articleVo);
        } else if (StringUtil.isNotEmpty(articleVo.getArticleUrl().get(0))) {
            articleVo.setStatus(2);
            return success(articleVo);
        } else {
            articleVo.setStatus(1);
            return success(articleVo);
        }


    }

    @Override
    public RspResult findAllArticle(ArticleFindBo bo) {

        try {

            List<ArticleVo> list = articleDao.list(bo);
            return success(list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public String findArticleCount(ArticleFindBo bo) {

        int count = articleDao.count(bo);

        return count+"";
    }

    @Override
    public RspResult findArticle(ArticleFindBo bo) {
        try {

            int count = articleDao.count(bo);
            List<ArticleVo> list = articleDao.listFor(bo);
         a: for (ArticleVo articleVo : list) {

             int commentCount = commentDao.findCountByDynamic(articleVo.getId());
            articleVo.setCommentCount(commentCount);
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setType("article");
            fileCarBo.setPurposeId(articleVo.getId());
             List<String> fileByPurposeIdAndTypeList = fileDao.findFileByPurposeIdAndTypeList1(fileCarBo);
             articleVo.setArticleUrl(fileByPurposeIdAndTypeList);
             int countPoint = articleDao.findCount(articleVo.getId(),bo.getUserId());
            if(countPoint>0){
                articleVo.setPointStatus(1);
            }else {
                articleVo.setPointStatus(0);
            }
             Long createTime = articleVo.getCreateTime();
             double time = (System.currentTimeMillis() - createTime) / 1000 / 60 / 60;
            articleVo.setTime(time);
             if(StringUtil.isNotLongEmpty(articleVo.getRecordId())){
                 BaseRecording byId = baseRecordingDao.findById(articleVo.getRecordId());
                 articleVo.setAlbumId(byId.getAlbumId());
                 articleVo.setStatus(3);
                 articleVo.setRecordingName(byId.getTitle());
                  continue a;
              }else if (articleVo.getArticleUrl()!=null&&articleVo.getArticleUrl().size()>0&&articleVo.getUserId()!=1){
                  articleVo.setStatus(2);
                  continue a;
              }else {
                  articleVo.setStatus(1);
                  continue a;
              }
            }
            return success(count,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RspResult updArticle(ArticleUpdBo bo) {
        Article article =  articleDao.findById(bo.getId());
        if(article==null){
            return errorMo();
        }
        if(StringUtil.isNotEmpty(bo.getContent())){
            article.setContent(bo.getContent());
        }
        if(StringUtil.isNotEmpty(bo.getFirstContent())){
            article.setFirstContent(bo.getFirstContent());
        }
        if(StringUtil.isNotEmpty(bo.getTitle())){
            article.setTitle(bo.getTitle());
        }
        if(StringUtil.isNotIntegerEmpty(bo.getCode())){
            article.setCode(bo.getCode());
        }

        if(StringUtil.isNotIntegerEmpty(bo.getRedPoint())){
            article.setRedPoint(bo.getRedPoint());
        }
        article.setLastRevampTime(System.currentTimeMillis());
        if(StringUtil.isNotEmpty(bo.getUrl())){
            FileCarBo fileCarBo = new FileCarBo();
            fileCarBo.setPurposeId(bo.getId());
            fileCarBo.setType("article");
            File file = fileService.findFile(fileCarBo);
            file.setLocation(bo.getUrl());
            fileDao.updateByPrimaryKey(file);
        }
        try {
            articleDao.update(article);
            return success();
        }catch (Exception e){
            return errorNo();
        }

    }

    @Override
    public RspResult addArticle(ArticleAddBo bo) {
        if(!StringUtil.isNotIntegerEmpty(bo.getCode())){
            return error(1,"排序值不能为空！");
        }
        if(!StringUtil.isNotEmpty(bo.getContent())){
            return error(1,"内容不能为空！");
        }
        if(!StringUtil.isNotEmpty(bo.getFirstContent())){
            return error(1,"第一层的内容不能为空！");
        }
        if(!StringUtil.isNotEmpty(bo.getTitle())){
            return error(1,"标题不能为空！");
        }
        if(!StringUtil.isNotLongEmpty(bo.getUserId())){
            return error(1,"用户id不能为空！");
        }
        Article article = new Article();
        article.setCode(bo.getCode());
        article.setContent(bo.getContent());
        article.setFirstContent(bo.getFirstContent());
        article.setRedPoint(0);
        article.setUserId(bo.getUserId());
        article.setTitle(bo.getTitle());

        try{
            articleDao.save(article);
            fileService.insertFile("article",bo.getUrl(),article.getId());
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }



    }




    @Override
    public RspResult save(Article entity) {
        return null;
    }

    @Override
    public Article findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Article entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
