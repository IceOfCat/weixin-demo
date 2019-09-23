package com.adiosava.weixin.common.constant;

/**
 * 通用类型
 */
public class ConstantType {

    /**
     * 方法类型
     */
    public static class Method_Type{
        /**
         * 添加方法
         */
        public static final String METHOD_ADD="METHOD_ADD";
        /**
         * 跟新方法
         */
        public static final String METHOD_UPDATE="METHOD_UPDATE";
        /**
         *
         */
        public static final String METHOD_START ="METHOD_START";

        public static final String METHOD_END ="METHOD_END";
    }

    /**
     *  用户类型
     */
    public static class UserType{

        public static final String  MeetingStaffType="MeetingStaffType";

        public static final String  UserType="UserType";

        public static final String  AdminType="AdminType";

    }

    /**
     * 角色类型
     */
    public static class RoleType{
        /**
         * 参会人角色
         */
        public static final String MEETING="MEETING";
        /**
         * 管理员角色
         */
        public static final String ADMIN="ADMIN";

    }

}
