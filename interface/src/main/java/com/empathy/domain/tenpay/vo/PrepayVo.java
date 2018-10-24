package com.empathy.domain.tenpay.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrepayVo {

    @ApiModelProperty("应用ID")
    private String appid;

    @ApiModelProperty("商户号")
    private String partnerid;

    @ApiModelProperty("预支付交易会话ID")
    private String prepayid;

    @ApiModelProperty("扩展字段")
    @JSONField(name = "package")
    private String packageStr;

    @ApiModelProperty("随机字符串")
    private String noncestr;

    @ApiModelProperty("时间戳")
    private String timestamp;

    @ApiModelProperty("签名")
    private String sign;

}
