package dtw;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      Recognizer rec=new Recognizer("C:\\Users\\samma\\Desktop\\Records\\5-4.mfc");
      rec.Recognize();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
