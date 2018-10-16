package com.empathy.service.impl;

import com.empathy.common.PageBo;
import com.empathy.common.RspResult;
import com.empathy.dao.BaseMemberDao;
import com.empathy.dao.UserMoneyDao;
import com.empathy.domain.account.bo.AccoutLoginBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserMoney;
import com.empathy.domain.user.Userinfo;
import com.empathy.domain.user.bo.FreezeUserBo;
import com.empathy.domain.user.vo.MemberVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IMessageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MI on 2018/4/13.
 */
@Service
public class BaseMemberService extends AbstractBaseService implements IBaseMemberService {
    @Autowired
    private BaseMemberDao baseMemberDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    //充值加钱
    @Override
    public void addMoney(double money,long id) {

        UserMoney byUserId =
                userMoneyDao.findByUserId(id);
        byUserId.setMoney(new BigDecimal(byUserId.getMoney().doubleValue()+money*10));
        byUserId.setLastRevampTime(System.currentTimeMillis());
        userMoneyDao.update(byUserId);
    }

    /**
     * 冻结
     *
     * @param bo
     * @return
     */
    @Override
    public RspResult freezeUser(FreezeUserBo bo) {
        BaseMember baseMember = baseMemberDao.findById(bo.getId());
        if (bo.getType() == 0) {
            //冻结
            baseMember.setDelFlag(1);

        } else if (bo.getType() == 1) {
            //解冻
            baseMember.setDelFlag(0);
        } else {
            return error(1, "无此操作参数");
        }
        baseMember.setLastRevampTime(System.currentTimeMillis());
        baseMemberDao.update(baseMember);
        return success();
    }


    @Override
    public RspResult findAllUser(PageBo bo) {
        List<MemberVo> list = baseMemberDao.findAllUser(bo);
        return success(list);
    }

    @Override
    public String findAllUserCount() {
        int count = baseMemberDao.findAllUserCount();

        return count + "";
    }

    @Override
    public RspResult save(BaseMember entity) {
        return null;
    }

    @Override
    public BaseMember findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseMember entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
