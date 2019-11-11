package com.bkw.core.network.rx.databus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据总线
 *
 * @author bkw
 */
public class RxBus {

    /**
     * 分发事件标记
     */
    private final static String START_RUN = "dpProcessInvoke start emitter run";

    private Set<Object> subscribers;

    /**
     * 注册
     */
    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 移除
     */
    public synchronized void unRegister(Object subscriber) {
        subscribers.remove(subscriber);
    }

    private static volatile RxBus instance;

    private RxBus() {
        //容器初始化
        subscribers = new CopyOnWriteArraySet<>(); //稳定的安全的
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void doProcessInvoke(Function function) {
        //function提供给耗时操作
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(START_RUN);
                emitter.onComplete();
            }
        }).map(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object data) throws Exception {

                        if (data != null) {
                            sendAction(data);
                        }
                    }
                });
    }

    /**
     * TODO 对外暴露API 2
     *
     * @param observable 用于Rx观察者
     * @param <T>
     * @param <R>
     */
    public <T, R> void doProcessInvoke(Observable<String> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String data) throws Exception {
                        if (data != null) {
                            sendAction(data);
                        }
                    }
                });
    }

    /**
     * 发送并负责扫描被注册的目标
     *
     * @param data
     */
    public void sendAction(Object data) {
        //扫描注册的对象，所以需要遍历subscribers容器
        for (Object subscriber : subscribers) {
            checkSubscriberAnnotationMethod(subscriber, data);
        }
    }

    /**
     * 事件总线发射
     */
    private void checkSubscriberAnnotationMethod(Object subscriberTarget, Object data) {
        Method[] declaredMethods = subscriberTarget.getClass().getDeclaredMethods();

        for (Method method : declaredMethods) {
            //查找被注解的方法
            method.setAccessible(true);

            RegisterRxBus registerRxBus = method.getAnnotation(RegisterRxBus.class);
            if (registerRxBus != null) {
                //找到目标方法的参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                String parameterType = parameterTypes[0].getName();

                //判断目标方法
                if (data.getClass().getName().equals(parameterType)) {
                    try {
                        method.invoke(subscriberTarget, new Object[]{data});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
