package com.my.shiro.constant;

/**
 * @author Gzy
 * @version 1.0
 * @Description shiro常量类
 */
public class CacheConstant extends SuperConstant{

    //区分shiro缓存 key
    public final static String GROUP_CAS = "group_shiro:";

    //角色对象 key
    public final static String ROLE_KEY = GROUP_CAS + "role_key:";

    //角色标签 key
    public final static String ROLE_LABEL_KEY = GROUP_CAS + "role_label_key:";

    //资源对象 key
    public final static String PERMISSION_KEY = GROUP_CAS + "permissions_key:";

    //资源标签 key
    public final static String PERMISSION_LABEL_KEY = GROUP_CAS + "permissions_label_key:";

    //资源ID key
    public final static String PERMISSION_KEY_IDS = GROUP_CAS + "permission_key_ids:";

    //通过登录名查询的用户 key
    public final static String FIND_USER_BY_LOGINNAME = GROUP_CAS + "findUserByLoginName:";

    //登录用户session前缀
    public final static String SESSION_PREFIX = GROUP_CAS + "session:";

    //redis 在线(已登录未登出)用户sessionID队列缓存key 前缀
    public final static String ACTIVE_SESSIONID_QUEUE_PREFIX = SESSION_PREFIX + "active:queue:";

}
