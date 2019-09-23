package com.adiosava.weixin.common.constant;

public class ErrorCode {
    /**
     * 请求成功
     */
    public final static int SUCCESS = 0;// 请求成功

    /**
     * 通用错误
     *
     */
    public static class CommonError {
        /**
         * 非法参数
         */
        public static final int INVALID_PARAM = 1001;
        /**
         * 非法错误
         */
        public static final int ILLEGAL_STATE = 1002;
        /**
         * 404页面找不到
         */
        public static final int NOT_FOUND = 1003;

        /**
         * 验证码超时
         */
        public static final int CODE_TIMEOUT = 1004;

        /**
         * 验证码错误
         */
        public static final int CODE_ERROR = 1005;

        /**
         * mac校验失败
         */
        public static final int MAC_CHECK_ERROR = 1006;

        /**
         * 验证码已经使用
         */
        public static final int CODE_USED_ERROR = 1007;
    }

    /**
     * 权限错误
     *
     */
    public static class AuthError {
        /**
         * 权限不足
         *
         */
        public static final int PERMISSION_LACK = 2001;
    }

    /**
     * 业务错误
     *
     */
    public static class BusinessError {
        /**
         * 业务默认异常
         *
         */
        public static final int DEFAULT = 3000;

        /**
         * 已存在错误
         */
        public static final int EXIST_ERR = 3001;

        /**
         * 不存在错误
         */
        public static final int NONEXIST_ERR = 3002;

    }

    /**
     * 登录错误
     *
     */
    public static class LoginError {
        /**
         * 未登录
         */
        public static final int UNLOGIN = 4000;

        /**
         * 用户不存在
         */
        public static final int NO_USER = 4001;

        /**
         * 停用账户
         */
        public static final int STATUS_ERR = 4002;

        /**
         * 密码错误
         */
        public static final int PASSWORD_ERR = 4003;

        /**
         * 密码错误次数超限
         */
        public static final int PASSWORD_ERR_NUM_LIMIT = 4004;

        /**
         * 登录过期
         */
        public static final int LOGIN_TIME_OUT = 4005;

        /**
         * 未绑定手机号
         */
        public static final int NOT_BIND_PHONE = 4006;

        /**
         * thirdSession过期
         */
        public static final int THIRD_SESSION_OUT = 4007;

    }

    // 直接提示-5000
    public static class AlertError {
        /**
         * 弹出提示
         *
         */
        public static final int ALERT = 5001;

    }

    // 资源错误-6000
    public static class ResourceError {

    }

    // 系统错误--9000
    public static class SystemError {
        /**
         * 内部错误
         */
        public static final int INNER_ERROR = 9000;
        /**
         * IP访问过于频繁
         */
        public static final int IP_LIMIT_ERROR = 9001;
    }
}
