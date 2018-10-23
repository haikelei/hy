package com.empathy.weixinpay;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PayRecord extends BaseDomain {
    private String phone;
    private long serialId;
    private int type;
    private long generateTime;
    private float totalAmount;
    private long payTime;
}
