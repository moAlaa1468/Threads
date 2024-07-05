//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            SimpleThreadExample myThing = new SimpleThreadExample(i);
            // if you need to extend the Thread class just you need to take an object from the SimpleThreadExample

            // if you need to implement the Runnable interface not Extend the Thread Class
            Thread myThread = new Thread(myThing);
            myThread.start();
            try {

                if (i == 2) {
                    myThread.join();  // This will end the thread after completing
                }
                boolean status = myThread.isAlive(); //
                System.out.println(status);
            } catch (InterruptedException e) {
                e.getMessage();
            }

        }
        throw new RuntimeException();
    }
}
