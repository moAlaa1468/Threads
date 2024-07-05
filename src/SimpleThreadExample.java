import java.awt.*;

public class SimpleThreadExample  implements Runnable{
/*
* This is just the best way to implement threads in java
* */
    private int threadNumber;

    public SimpleThreadExample(int threadNumber) {
        this.threadNumber = threadNumber;
    }


    @Override
    public void run() throws RuntimeException {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " from Thread : " + threadNumber);
        }
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

