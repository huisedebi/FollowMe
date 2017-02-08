package com.Mystar.www.constant;

import com.Mystar.www.util.AppUtil;

/**
 * Created by Administrator on 2016/4/7.
 */
public class Constant {
    public static final String HOST = "http://www.g5hd.com/index.php?key="+ AppUtil.getMD5Time();

    public static final class Url {
        public static final String LOGIN =HOST + "&g=App&m=Login&a=register";
    }

    public static final class Key {
    }

    public static final class SP_KEY {
    }

    public static final class RequestAndResultCode {
        //0被自动升级占用了
    }

    /**
     * Intent传值的KEY
     */
    public static final class IntentKey {
    }
}
