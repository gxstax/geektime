package com.ant.lesson17;

/**
 * <p>
 * 用户信息上下文
 * </p>
 *
 * @author Ant
 * @since 2021/2/7 1:57 下午
 */
public class UserContext {

    private static ThreadLocal<Integer> USERID = new ThreadLocal();


    // 去掉私有的构造函数
//    private UserContext() {
//        throw new UnsupportedOperationException();
//    }


    public String getUserID() {
        return String.valueOf(USERID.get());
    }


    public void setUserID(Integer userID) {
        USERID.set(userID);
    }

    public void clearUserId() {
        USERID.set(null);
    }

}
