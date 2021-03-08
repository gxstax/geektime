package com.ant.lesson17;

/**
 * <p>
 * 用户账号类
 * </p>
 *
 * @author Ant
 * @since 2021/2/7 2:13 下午
 */
public class UserAccounts {

    // 把对象当成类的一个字段
//    private UserContext context = new UserContext();

    private UserContext context;

    // 保证多个类使用同一个 UserContext 对象，所以把 UserContext 放到构造函数中传过来
    public UserAccounts(final UserContext context) {
        this.context = context;
    }

    public void loginOut() {
        context.clearUserId();
    }


}
