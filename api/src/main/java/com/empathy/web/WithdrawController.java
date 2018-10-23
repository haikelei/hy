package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.user.bo.LoginBo;
import com.empathy.domain.withdraw.bo.*;
import com.empathy.service.IWithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/4/17.
 */
@RestController
@RequestMapping("/withdraw")
@Api(tags = {"提现接口"})
public class WithdrawController {

    @Autowired
    private IWithdrawService withdrawService;

    @ApiOperation(value = "申请提现", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/addWithdraw", method = RequestMethod.POST)
    public RspResult addWithdraw(WithdrawAddBo bo) {
        return withdrawService.addWithdraw(bo);
    }

    @ApiOperation(value = "修改支付密码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/modifyPayPassword", method = RequestMethod.POST)
    public RspResult modifyPayPassword(ModifyPasswordBo bo) {
        return withdrawService.modifyPayPassword(bo);
    }

    @ApiOperation(value = "获取修改支付密码的验证码", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/modifyPayPasswordCode", method = RequestMethod.POST)
    public RspResult getPayPasswordCode(ModifyPasswordCodeBo bo) {
        return withdrawService.getPayPasswordCode(bo);
    }

    @ApiOperation(value = "审核提现", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/updWithdraw", method = RequestMethod.POST)
    public RspResult updWithdraw(WithdrawUpdBo bo) {

        return withdrawService.updWithdraw(bo);

    }

    @ApiOperation(value = "提现列表", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findWithdraw", method = RequestMethod.POST)
    public RspResult findWithdraw(WithdrawFindBo bo) {

        return withdrawService.findWithdraw(bo);

    }

    @ApiOperation(value = "提现列表数据", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findWithdrawCount", method = RequestMethod.POST)
    public String findWithdrawCount(WithdrawFindBo bo) {

        return withdrawService.findWithdrawCount(bo);

    }


}
