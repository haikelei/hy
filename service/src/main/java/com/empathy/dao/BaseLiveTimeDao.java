package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.live.BaseLiveTime;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.BaseMember;

/**
 * Created by MI on 2018/4/24.
 */
public interface BaseLiveTimeDao extends BaseDao<BaseLiveTime, Long, LogBo> {
    BaseLiveTime findByLiveIdForClose(Long liveId);
}
