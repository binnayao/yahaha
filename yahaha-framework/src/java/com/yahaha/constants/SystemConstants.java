package com.yahaha.constants;

public class SystemConstants {
    public static final String ARTICLE_STATUS_NORMAL = "0";
    public static final String CATEGORY_STATUS_NORMAL = "0";
    public static final String Link_STATUS_NORMAL = "0";
    public static final int COMMENT_FOR_PAGE = 0;
    public static final int COMMENT_FOR_FRIEND_LINK = 1;
    public static final int COMMENT_ROOT_ID = -1;
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_START_NUM = 1;

    // redis key
    public static final String REDIS_KEY_LOGIN_USER_PREFIX = "loginUser:";
    public static final String REDIS_KEY_ADMIN_LOGIN_USER_PREFIX = "adminLoginUser:";
    public static final String REDIS_KEY_VIEW_COUNT = "viewCount";
}
