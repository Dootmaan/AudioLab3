package realtime_ui;

public class RecThread implements Runnable {

  @Override
  public void run() {
    // TODO Auto-generated method stub
    while (true) {
      try {
        new Capture().startRecognize();

        Runtime rt = Runtime.getRuntime();
        String cmdStr = "";
        try {
          cmdStr = "hcopy -A -D -T 1 -C D:\\tr_wav.cfg -S D:\\list2.scp";// 要调用的程序路径
          rt.exec("cmd.exe /c start " + cmdStr);
        } catch (Exception e) {
          System.out.println("open failure");
        }

        Recognizer rec = new Recognizer("D:\\temp.mfc");
        Recorder.result.setText(rec.Recognize());

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

