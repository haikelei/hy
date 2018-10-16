package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.withdraw.Withdraw;
import com.empathy.domain.withdraw.bo.WithdrawAddBo;
import com.empathy.domain.withdraw.bo.WithdrawFindBo;
import com.empathy.domain.withdraw.bo.WithdrawUpdBo;

/**
 * Created by MI on 2018/4/17.
 */
public interface IWithdrawService extends BaseService<Withdraw, Long, PageBo> {
    RspResult addWithdraw(WithdrawAddBo bo);

    RspResult updWithdraw(WithdrawUpdBo bo);

    RspResult findWithdraw(WithdrawFindBo bo);

    String findWithdrawCount(WithdrawFindBo bo);
}
