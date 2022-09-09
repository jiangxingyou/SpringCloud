import java.util.concurrent.atomic.AtomicInteger;

public class T {
    public static void main(String[] args) {
        System.out.println("xx");

        AtomicInteger atomicInteger = new AtomicInteger(0);

        System.out.println(atomicInteger.compareAndSet(0, 2022)+"\t"+"current data"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(0, 2019)+"\t"+"current data"+atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
