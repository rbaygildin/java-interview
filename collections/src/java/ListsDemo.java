import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListsDemo {

    private static final int N = 1_000;
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ListsDemo tests = TestFactory.getTestRunner(ListsDemo.class);
        List<Integer> x = new ArrayList<>();
        for(int i = 0; i < N; i++){
            x.add(i);
        }
        List<Integer> y = new LinkedList<>();
        for(int i = 0; i < N; i++){
            y.add(i);
        }
        tests.benchmarkArrayLists_Add();
        tests.benchmarkLinkedLists_Add();
        tests.benchmarkArrayLists_Get(x);
        tests.benchmarkLinkedLists_Get(y);
    }

    @Benchmark
    public void benchmarkArrayLists_Add() {
        List<Integer> x = new ArrayList<>();
        for(int i = 0; i < N; i++){
            x.add(i);
        }
    }

    @Benchmark
    public void benchmarkLinkedLists_Add() {
        List<Integer> y = new LinkedList<>();
        for(int i = 0; i < N; i++){
            y.add(i);
        }
    }

    @Benchmark
    public void benchmarkArrayLists_Get(List<Integer> x) {
        for(int i = 0; i < N; i++){
            Integer res = x.get(i);
        }
    }

    @Benchmark
    public void benchmarkLinkedLists_Get(List<Integer> y) {
        for(int i = 0; i < N; i++){
            Integer res = y.get(i);
        }
    }
}

class TestFactory {
    public static <T> T getTestRunner(Class<? extends T> targetClazz) throws IllegalAccessException, InstantiationException {
        T instance = targetClazz.newInstance();
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(targetClazz);
        f.setFilter(m -> m.isAnnotationPresent(Benchmark.class));
        Class proxyClazz = f.createClass();
        T proxy = (T) proxyClazz.newInstance();
        ((Proxy) proxy).setHandler((o, method, method1, args) -> {
            long t0 = System.nanoTime();
            Object res = method.invoke(instance, args);
            long t1 = System.nanoTime();
            System.out.println("Run " + targetClazz.getName() + "." + method.getName() + "() at " + (t1 - t0) + " nanosecs");
            return res;
        });
        return proxy;
    }
}