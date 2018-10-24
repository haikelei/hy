package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.banner.bo.BannerFindBo;
import com.empathy.domain.log.bo.LogBo;

import java.util.List;

//import com.sun.xml.internal.rngom.parse.host.Base;

/**
 * Created by MI on 2018/5/9.
 */
public interface BannerDao  extends BaseDao<BaseBanner, Long, LogBo> {

    List<BaseBanner> list(BannerFindBo bo);

    int count(BannerFindBo bo);
}
