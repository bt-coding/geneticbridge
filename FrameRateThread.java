public class FrameRateThread implements Runnable{
    private Display screen;
    //in frames per second
    private int frameRate;
    public FrameRateThread(Display s, int fr){
        screen = s;
        frameRate = fr;
    }
    public void run(){
        while(true){
            new Thread(new FrameThread(screen)).start();
            try{
                Thread.sleep(1000/frameRate);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}   