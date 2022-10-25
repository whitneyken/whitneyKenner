import java.util.ArrayList;
public class Main {

    static int answer = 0;
    static int maxValue = 100;
static void badSum(ArrayList<Thread> threads){
    int numThreads = 15;
    for (int i = 0; i < numThreads; i++) {
        final int finalI = i;
        Thread thread = new Thread(() -> {
            for (int j = (finalI * maxValue / numThreads); j <  (Math.min((finalI+1)* maxValue/numThreads, maxValue)); j++){
                answer += j;
            }

        });
        thread.start();
        threads.add(thread);
    }
    for (Thread thread : threads){
        try {

            System.out.println("The calculated answer is: " + answer);
            System.out.println("The real answer is: " + (maxValue * (maxValue - 1) / 2));
            thread.join();
        }catch(Exception e){
            System.out.println("thread joining failure");
        }

    }
}
//The results seem random because all the threads are running at the same time and doing whatever the computer likes
// so each thread is pulling "answer", adding to its value and then saving it again, so everything is getting pulled
// in an unpredictable order resulting in these random values

    //When setting the max value to 100, the values are the same every time I run it. This is probably because The max
    // value is so low that it is able to be completed quickly without using many threads





static void sayHello(ArrayList<Thread> myThreads){

    for (int i = 0; i < 10; i++){
        Thread myThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                int count = 0;
                while (count < 100) {

                    System.out.println("hello number " + count++  + " from thread number " + Thread.currentThread().threadId());
                }
            }
        });
        myThreads.add(myThread);
        myThread.start();
    }
}




    public static void main(String[] args) {
        ArrayList<Thread> allThreads = new ArrayList<>();
        sayHello(allThreads);
        for (Thread allThread : allThreads) {
            try {
                allThread.join();
                System.out.println(allThread.threadId() + " Has been closed");
            } catch (Exception e) {
                System.out.println("an exception has occurred");
            }
        }
        ArrayList<Thread> answerThreads = new ArrayList<>();
        badSum(answerThreads);






    }



}

//    Create a static function in your main class called sayHello.
//
//        Within that method:
//
//        Create an array of threads (start with 10 or so).
//        Set each thread to run a function that counts to 100, printing
//        ("hello number ___ from thread number ___") for each number (you can use Thread.currentThread().getID()
//        to get the thread's number).
//        Start all the threads in your array.
//        Join all the threads in your array.
//        What happens? Do all the threads run in order?
//
//        Modify your thread function slightly to use print rather than println. Print a newline every 10th
//        hello. This should make the interaction between threads more obvious.
//
//        Run your program a couple of times - does the same thread always print the 1st hello? The last hello?