public class FrameThread implements Runnable{
    private Display screen;
    public FrameThread(Display s){
        screen = s;
    }
    public void run(){
        screen.update();
        screen.draw();
    }
}