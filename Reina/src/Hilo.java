public class Hilo extends Thread{
    @Override
    public void run() {
        int i = 0;
        while (true){
            System.out.println("A " + i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
