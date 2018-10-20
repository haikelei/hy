package com.empathy.weixinpay;

// TODO: 2018/10/19 微信申请完后配置
public class ConstantUtil {
    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY="";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="";
    /**
     * 商品描述
     */
    public static final String BODY="QQ游戏-账户充值";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key="";

    /**
     * 商户id
     */
    public static final String PARTNER_ID="";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/weixinpay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL="http://www.bairuoheng.cn/app/tenpay/notify";
}