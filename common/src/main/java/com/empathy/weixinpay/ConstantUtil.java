package com.empathy.weixinpay;

// TODO: 2018/10/19 微信申请完后配置
public class ConstantUtil {
    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wxad49888a3aa32ae8";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY="gW7Zr2ry0mei7h1qCo35ormt4ZZC0ki8";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1517033091";
    /**
     * 商品描述
     */
    public static final String BODY="华语-订单支付";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key="";

    /**
     * 商户id
     */
    public static final String PARTNER_ID="1517033091";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL="http://47.106.196.89:8080/hy/alipay/wechatCallBack/";
}