package rpc.turbo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 服务接口声明，以及服务接口、方法配置，<br>
 * 当接口中包含公开的默认方法时自动注册为failover<br>
 * 所有public的方法返回值类型都必须为CompletableFuture.
 *
 * @author Hank
 * @author liupu
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface TurboService {

    /**
     * 默认权重
     */
    int DEFAULT_WEIGHT = 100;
    /**
     * 默认分组
     */
    String DEFAULT_GROUP = "DEFAULT_GROUP";
    /**
     * 默认应用
     */
    String DEFAULT_APP = "DEFAULT_APP";
    /**
     * 默认版本（X.Y.Z）
     */
    String DEFAULT_VERSION = "1.0.0";
    /**
     * 默认超时
     */
    long DEFAULT_TIME_OUT = 5 * 1000L;
    /**
     * 默认是否忽略
     */
    boolean DEFAULT_IGNORE = false;

    /**
     * for METHOD and TYPE, RPC服务方法版本<br>
     * 仅第一位为实际使用，第一位不一样时表示是不兼容的方法调用<br>
     * method有则用method的，method没有则使用class的，都没有则使用默认值1.0.0
     *
     * @return 版本（X.Y.Z）
     */
    String version();

    /**
     * for METHOD and TYPE, millseconds, 当等于-1时程序会自动处理为默认值5000<br>
     * method有则用method的，method没有则使用class的，class没有则使用默认值5000
     *
     * @return 超时时间（毫秒）
     */
    long timeout() default -1;

    /**
     * for METHOD and TYPE，忽略，不对外提供服务
     *
     * @return 是否对外提供服务
     */
    boolean ignore() default DEFAULT_IGNORE;

    /**
     * for METHOD and TYPE, 仅设置接口无效，只有Method上设置了才会启用,<br>
     * 支持GET、POST<br>
     *
     * <pre>
     * &#64;RPCService(rest = "/user", version = "1.2.1")
     * public class UserService {
     *
     * 	&#64;RPCService(rest = "/create", version = "1.2.1")
     * 	public CompletableFuture&lt;Boolean&gt; createUser(User user);
     *
     * 	&#64;RPCService(rest = "/get", version = "2.2.1")
     * 	public CompletableFuture&lt;User&gt; getUser(long id);
     * }s
     * </pre>
     * <p>
     * 通过HTTP GET http://host:port/group/app/user/get?v=2&amp;id=1 请求;<br>
     * v=2必须为第一个参数，不能修改为?id=1&amp;v=2;<br>
     * <br>
     * 通过HTTP POST http://host:port/group/app/user/get?v=2请求, body内容为{"id": 1}.<br>
     *
     * @return REST风格的服务路径
     */
    String rest() default "";

}
