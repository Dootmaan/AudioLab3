package realtime_ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recognizer {
  FileInputStream inputstream;
  List<float[][]> sample = new ArrayList<>();

  public Recognizer(String src) throws IOException {
    inputstream = new FileInputStream(src);
    Init("C:\\Users\\samma\\Desktop\\Records\\");
  }

  public void Init(String src) throws IOException {
    int i = 1;
    for (i = 1; i <= 10; i++) {   //Remember to change this number！！！
      sample.add(getMat(new FileInputStream(src + i + "-1.mfc")));
    }
  }

  public float[][] getMat(FileInputStream inputstream) throws IOException {
    byte[] column = new byte[4];
    inputstream.read(column);
    int nframes = Byte2Int(column);

    byte[] rate = new byte[4];
    inputstream.read(rate);
    Byte2Int(rate);

    byte[] row = new byte[2];
    inputstream.read(row);
    short nbytes = Byte2Short(row);

    byte[] fea = new byte[2];
    inputstream.read(fea);
    Byte2Short(fea);

    int ndim = nbytes / 4;

    float[][] input = new float[ndim][nframes];
    int i = 0, j = 0;
    for (i = 0; i < nframes; i++) {
      for (j = 0; j < ndim; j++) {
        byte[] temp = new byte[4];
        inputstream.read(temp, 0, 4);

        input[j][i] = Byte2Float(temp);
      }
    }

    return input;
  }

  public int Byte2Int(byte[] b) {
    return ((b[3] & 0xFF) | ((int) b[2] << 8) | ((int) b[1] << 16) | ((int) b[0] << 24));
  }

  public short Byte2Short(byte[] b) {
    return (short) ((b[1] & 0xFF) | ((short) b[0] << 8));
  }

  // public float Byte2Float(byte[] b) {
  // return Float.intBitsToFloat((int) ((b[3] & 0xFF) | ((long) b[2] << 8) | ((long) b[1] << 16) |
  // ((long) b[0] << 24)));
  // }

  public static float Byte2Float(byte[] b) {
    int l;
    l = b[3];
    l &= 0xff;
    l |= ((long) b[2] << 8);
    l &= 0xffff;
    l |= ((long) b[1] << 16);
    l &= 0xffffff;
    l |= ((long) b[0] << 24);
    return Float.intBitsToFloat(l);
  }

  public String Recognize() throws IOException {
    float[][] input = getMat(inputstream);
    int i = 0;
    int min_index = 0;
    float min = Float.MAX_VALUE;
    for (i = 0; i < sample.size(); i++) {
      float sum = 0;
      sum = new DTW_RegAndDiv(input, sample.get(i)).dtw();
      if (sum < min) {
        min = sum;
        min_index = i + 1;
      }
    }
    
    String result="";
    switch (min_index) {
      case 1:
        System.out.println("开始");
        result="开始";
        break;
      case 2:
        System.out.println("结束");
        result="结束";
        break;
      case 3:
        System.out.println("打开");
        result="打开";
        break;
      case 4:
        System.out.println("关闭");
        result="关闭";
        break;
      case 5:
        System.out.println("上升");
        result="上升";
        break;
      case 6:
        System.out.println("下降");
        result="下降";
        break;
      case 7:
        System.out.println("攻击");
        result="攻击";
        break;
      case 8:
        System.out.println("防御");
        result="防御";
        break;
      case 9:
        System.out.println("你好");
        result="你好";
        break;
      case 10:
        System.out.println("再见");
        result="再见";
        break;
      default:
        System.out.println("序号错误");
    }
    return result;
  }

}
