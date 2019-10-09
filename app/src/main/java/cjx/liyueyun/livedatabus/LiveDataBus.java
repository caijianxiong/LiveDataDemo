package cjx.liyueyun.livedatabus;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caicai
 * @create 2019/7/17
 * @Describe
 */
public class LiveDataBus {
    private final Map<String, MutableLiveData<Object>> bus;
    private LiveDataBus() {
        bus = new HashMap<>();
    }

    private static class SingletonHolder {
        private static final LiveDataBus LIVE_DATA_BUS = new LiveDataBus();
    }

    public static LiveDataBus get() {
        return SingletonHolder.LIVE_DATA_BUS;
    }
    public synchronized <T> MutableLiveData<T> with(String key,Class<T> type){
        if (!bus.containsKey(key)){
            bus.put(key,new BusMutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }
}
