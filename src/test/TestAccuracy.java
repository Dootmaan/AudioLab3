package test;

import java.io.IOException;
import org.junit.Test;
import realtime.Recognizer;

public class TestAccuracy {

  @Test
  public void test() throws IOException {
//    fail("Not yet implemented");
    int i,j,k=0;
    for(i=1;i<=10;i++) {
      for(j=1;j<=5;j++) {
        Recognizer rec=new Recognizer("C:\\Users\\samma\\Desktop\\Records\\"+i+"-"+j+".mfc");
        switch(i) {
        case 1:
          if(rec.Recognize().equals("开始")){k++;}
          break;
        case 2:
          if(rec.Recognize().equals("结束")){k++;}
          break;
        case 3:
          if(rec.Recognize().equals("打开")){k++;}
          break;
        case 4:
          if(rec.Recognize().equals("关闭")){k++;}
          break;
        case 5:
          if(rec.Recognize().equals("上升")){k++;}
          break;
        case 6:
          if(rec.Recognize().equals("下降")){k++;}
          break;
        case 7:
          if(rec.Recognize().equals("攻击")){k++;}
          break;
        case 8:
          if(rec.Recognize().equals("防御")){k++;}
          break;
        case 9:
          if(rec.Recognize().equals("你好")){k++;}
          break;
        case 10:
          if(rec.Recognize().equals("再见")){k++;}
          break;
        }
      }
    }
    System.out.println("识别正确率："+k/50.0);
  }
}