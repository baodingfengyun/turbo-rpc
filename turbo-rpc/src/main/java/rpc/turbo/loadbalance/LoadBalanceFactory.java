package rpc.turbo.loadbalance;

/**
 * @param <T> 必须为Weightable子类
 * @author Hank
 */
public interface LoadBalanceFactory<T extends Weightable> {

    /**
     * 创建一个新的LoadBalance
     *
     * @return
     */
    LoadBalance<T> newLoadBalance();
}
