package rpc.turbo.loadbalance;

import rpc.turbo.util.concurrent.ConcurrentIntegerSequencer;

import java.util.List;

/**
 * 速度最快 204.642 ± 1.959 ops/us
 *
 * @param <T> 必须为Weightable子类
 * @author Hank
 */
public class RoundRobinLoadBalance<T extends Weightable> implements LoadBalance<T> {
    private final ConcurrentIntegerSequencer sequencer = new ConcurrentIntegerSequencer(0, true);

    protected volatile WeightableGroup<T> weightableGroup = null;

    @Override
    public void setWeightables(List<T> weightables) {
        weightableGroup = new WeightableGroup<>(weightables);
    }

    @Override
    public T select() {
        final WeightableGroup<T> weightableGroup = this.weightableGroup;

        if (weightableGroup == null) {
            return null;
        }

        int sum = weightableGroup.sum();

        if (sum < 2) {
            return weightableGroup.get(0);
        }

        int seed = sequencer.next();
        return weightableGroup.get(seed);
    }

}
