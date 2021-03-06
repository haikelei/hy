package com.empathy.domain.user.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class PasswordDealUpdBo {
    @ApiModelProperty("密码")
    @Required
    private String password;
    @ApiModelProperty("用户id")
    @Required
    private Long id;
    @ApiModelProperty("验证码")
    @Required
    private String code;

}
