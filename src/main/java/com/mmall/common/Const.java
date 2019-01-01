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

    public static final int SALE_STATUS_ON = 1;
}
