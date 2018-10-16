package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.baseCode.BaseCode;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.user.UserMoney;
import com.empathy.domain.withdraw.Withdraw;
import com.empathy.domain.withdraw.bo.WithdrawAddBo;
import com.empathy.domain.withdraw.bo.WithdrawFindBo;
import com.empathy.domain.withdraw.bo.WithdrawUpdBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseMemberService;
import com.empathy.service.IWithdrawService;
import com.empathy.utils.CodeUtils;
import com.empathy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class WithdrawService extends AbstractBaseService implements IWithdrawService {

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private ConfigurationDao configurationDao;

    @Autowired
    private WithdrawDao withdrawDao;

    @Autowired
    private BaseCodeDao baseCodeDao;

    @Autowired
    private BaseDealDao baseDealDao;


    @Override
    public String findWithdrawCount(WithdrawFindBo bo) {
        int count = withdrawDao.countByBo(bo);

        return count + "";
    }

    @Override
    public RspResult findWithdraw(WithdrawFindBo bo) {
        List<Withdraw> list = withdrawDao.list(bo);
        int count = withdrawDao.count();

        return success(count, list);
    }

    //审核提现
    @Override
    public RspResult updWithdraw(WithdrawUpdBo bo) {
        if (bo.getType() == 0) {
            //提现成功
            Withdraw withdraw = withdrawDao.findById(bo.getId());
            withdraw.setWithdrawStatus(200);
            withdraw.setLastRevampTime(System.currentTimeMillis());
            withdrawDao.update(withdraw);
        } else if (bo.getType() == 1) {
            //提现失败
            Withdraw withdraw = withdrawDao.findById(bo.getId());
            withdraw.setWithdrawStatus(300);
            withdraw.setLastRevampTime(System.currentTimeMillis());
            withdrawDao.update(withdraw);
            UserMoney userMoney = userMoneyDao.findByUserId(withdraw.getUserId());
            userMoney.setMoney(BigDecimal.valueOf(userMoney.getMoney().doubleValue() + withdraw.getLastMoney().doubleValue()));
            userMoney.setLastRevampTime(System.currentTimeMillis());
            userMoneyDao.update(userMoney);

            BaseDeal baseDeal = new BaseDeal();
            baseDeal.setUserId(withdraw.getUserId());
            baseDeal.setMoney(withdraw.getLastMoney());
            baseDeal.setCode(withdraw.getCode());
            baseDeal.setType(4);
            baseDealDao.save(baseDeal);


        }


        return new RspResult();
    }


    //申请提现
    @Override
    public RspResult addWithdraw(WithdrawAddBo bo) {
        UserMoney userMoney = userMoneyDao.findByUserId(bo.getUserId());
        if (!userMoney.getPassword().equals(MD5Utils.encrypt(bo.getPassword()))) {
            return new RspResult("密码错误", 1);
        }
        Configuration configuration = configurationDao.findByType("withdraw");

        if ((userMoney.getMoney().doubleValue() * configuration.getConversion()) < bo.getMoney().doubleValue()) {
            return new RspResult("金额不足", 1);
        }
        userMoney.setMoney(BigDecimal.valueOf(userMoney.getMoney().doubleValue() - bo.getMoney().doubleValue() / configuration.getConversion()));
        userMoney.setLastRevampTime(System.currentTimeMillis());

        Withdraw withdraw = new Withdraw();
        String code = CodeUtils.getCode();
        withdraw.setCode("-" + code);
        withdraw.setMoney(bo.getMoney());
        withdraw.setWithdrawType(bo.getWithdrawType());
        withdraw.setLastMoney(BigDecimal.valueOf(bo.getMoney().doubleValue() / configuration.getConversion()));
        withdraw.setUserId(bo.getUserId());
        withdraw.setName(bo.getName());
        withdraw.setWithdrawStatus(100);
        withdraw.setCreateTime(System.currentTimeMillis());
        withdraw.setLastRevampTime(System.currentTimeMillis());

        if (bo.getWithdrawType() == 0) {
            withdraw.setAlipayAccount(bo.getAlipayAccount());
        } else if (bo.getWithdrawType() == 1) {
            withdraw.setBank(bo.getBank());
            withdraw.setCard(bo.getCard());
        } else {
            return new RspResult("无此提现方式", 1);
        }
        userMoneyDao.update(userMoney);
        withdrawDao.save(withdraw);
        BaseCode baseCode = new BaseCode();
        baseCode.setCodeType("withdraw");
        baseCode.setCode("+" + code);
        baseCode.setCreateTime(System.currentTimeMillis());
        baseCode.setLastRevampTime(System.currentTimeMillis());
        baseCodeDao.save(baseCode);

        BaseDeal baseDeal = new BaseDeal();
        baseDeal.setCode(code);
        baseDeal.setMoney(BigDecimal.valueOf(bo.getMoney().doubleValue() / configuration.getConversion()));
        baseDeal.setType(1);
        baseDeal.setUserId(bo.getUserId());
        baseDeal.setCreateTime(System.currentTimeMillis());
        baseDeal.setLastRevampTime(System.currentTimeMillis());
        baseDealDao.save(baseDeal);

        return new RspResult();
    }

    @Override
    public RspResult save(Withdraw entity) {
        return null;
    }

    @Override
    public Withdraw findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Withdraw entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
