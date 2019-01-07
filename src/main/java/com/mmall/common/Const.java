package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by dgx on 2018/12/28.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public interface ProductListOrderBy{
        // 使用Set原因：Set的contains方法时间复杂度比List小
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Cart{
        int CHECKED = 1; //购物车选中状态
        int UN_CHECKED = 0; //购物车未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public static final int SALE_STATUS_ON = 1;

    public enum OrderStatusEnum {
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "已付款"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }
        private int code;
        private String value;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private int code;
        private String value;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


}
