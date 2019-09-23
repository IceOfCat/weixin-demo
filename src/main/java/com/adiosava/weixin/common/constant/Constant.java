package com.adiosava.weixin.common.constant;

/**
 * 常量类
 */
public class Constant {

    /**
     * 编码方式
     */
    public static class Encoding_Type{

        public final static  String ISO="ISO-8859-1";

        public final static  String iso="iso-8859-1";

        public final static  String UTF8="UTF-8";

        public final static  String GBK="GBK";

    }

    /**
     * redis 中存储的key值
     */
    public static class RedisKey{
        /**
         * 参会人id集合
         */
        public final static String ONLINE_USER="HHT:ONLINE:ONLINE_USER_";

        /**
         * 在线人数
         */
        public final static String ONLINE_NUM="HHT:ONLINE:ONLINE_NUM";

        /**
         * 在线人数锁
         */
        public final static String ONLINE_NUM_LOCK="HHT:LOCK:ONLINE_NUM_LOCK";

        /**
         * 角色
         */
        public static final String ROlE_LOCK ="HHT:LOCK:REDIS_ROLE_LOCK_";

        /**
         * 客户端配置
         */
        public static final String CLIENT_CONFIG ="HHT:CONFIG:CLIENT_CONFIG";
        /**
         * 会议
         */
        public static final String MEETING_LOCK ="HHT:LOCK:MEETING_LOCK_";
        /**
         * 会议室座位安排
         */
        public static final String MEETING_STAFF_SEAT ="HHT:CONFIG:MEETING_STAFF_SEAT_" ;
        /**
         * 文件上传
         */
        public static final String FILE_UPLOAD_LOCK ="HHT:LOCK:FILE_UPLOAD_";
        /**
         * 首页配置
         */
        public static final String OPTION_HOME_PAGE="HHT:OPTION:HOME_PAGE";
        /**
         * 首页配置统计
         */
        public static final String OPTION_HOME_PAGE_STATEMENT="HHT:OPTION:HOME_PAGE_STATEMENT";
        /**
         * 前屏配置
         */
        public static final String OPTION_FRONT_SREEN = "HHT:OPTION:FRONT_SCREEN";
        /**
         * 会议室图+会议室id
         */
        public static final String PICDATA ="HHT:OPTION:PICDATA_" ;

        public static final String BOARD ="HHT:MEETING:BOARD:";
        /**
         * 设配数量锁
         */
        public static final String EQUIPMENT_NUM_LOCK = "HHT:LOCK:EQUIPMENT_NUM_LOCK";
        /**
         * 设备数量
         */
        public static final String EQUIPMENT_NUM ="HHT:ONLINE:EQUIPMENT_NUM";

        public static final String BOARD_USER ="HHT:MEETING:BOARD:USER:";
        public static final String BOARD_USER_SHAPE ="HHT:MEETING:BOARD:USER:SHAPE:" ;
        /**
         * 字典
         */
        public static final String DICT ="HHT:DICT:";
        public static final String RIGHT ="HHT:OPTION:RIGHT" ;
    }

    /**
     * session key
     */
    public static class SessionKey{
        public final static String USER_AGENT="USER_AGENT";
        /**
         * 参会人id集合
         */
        public final static String MEETING_STAFF_ID="MEETING_STAFF_ID";
        /**
         * 会议id
         */
        public static final String MEETING_ID = "MEETING_ID";
    }


    /**
     * 调用接口类型
     */
    public static class UserAgent{
        /**
         客户端
         */
        public final static String CLIENT="CLIENT";
        /**
         服务端
         */
        public final static String SERVER="SERVER";

    }

    /**
     *  rabbitMq路由键
     */
    public static class RabbitRoutingKey{
        /**
         * 通知路由键
         */
        public static final String  Notice_Routing_Key="notice.#";
    }

    /**
     * rabbitMq交换机名称
     */
    public static class  RabbitMqTopic{

        public static final String Notice_Topic="Notice_Topic";
    }

    /**
     * redis锁过期时间
     */
    public static class RedisExpire{

        public static final Long COMMON_EXPIRE=1800L;

        public static final Long COMMON_EXPIRE_3600=3600L;

    }

    /**
     * websocket目标
     */
    public static class WebSocketTarget{

        public static final String ALL="ALL";

        public static final String CLIENT="CLIENT";

        public static final String PROJECTOR="PROJECTOR";

        public static final String DESKTOPSYNC="DESKTOPSYNC";

        public static final String SERVICECONSOLE="SERVICECONSOLE";

        public static final String MIDSERVER="MIDSERVER";

        public static final String RECORDER="RECORDER";

        /**
         * 心跳
         */
        public static final String PING="PING";

    }

    /**
     *  特殊字符
     */
    public static class SpecialCharacter{

        public static final String UNDER_LINE="_";

    }


}
