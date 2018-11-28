package realtime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Recorder {
  private JLabel result;

  /**
   * Launch the application.
   */
  public Recorder() {
    
     JFrame f = new JFrame();
     f.setTitle("语音识别器");
     f.setVisible(true);
     
     JPanel contentPane;
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.setBounds(100, 100, 450, 224);
     contentPane = new JPanel();
     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
     f.setContentPane(contentPane);
     contentPane.setLayout(null);
     
     JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528");
     label.setFont(new Font("微软雅黑", Font.ITALIC, 25));
     label.setHorizontalAlignment(SwingConstants.CENTER);
     label.setBounds(100, 29, 226, 66);
     contentPane.add(label);
     
     JLabel label_1 = new JLabel("\u60A8\u8BF4\u7684\u662F\uFF1A");
     label_1.setBounds(147, 108, 91, 29);
     contentPane.add(label_1);
     
     result = new JLabel("\u65E0\u8F93\u5165");
     result.setBounds(254, 113, 72, 18);
     contentPane.add(result);

     while(true)
       rec();
  }
  
  private void rec() {
    try {
//      AudioFormat audioFormat =
//          // new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100F,
//          // 8, 1, 1, 44100F, false);
//          new AudioFormat(16000, 16, 1,true, true);
//      DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
//      TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
//      targetDataLine.open(audioFormat);
//      info = new DataLine.Info(SourceDataLine.class, audioFormat);
//      targetDataLine.start();
//      
//      
//      while (true) {
//        int nByte = 0;
//        final int bufSize = 655266;
//        byte[] buffer = new byte[bufSize];
//        // System.in.read();
//        nByte = targetDataLine.read(buffer, 0, bufSize);
//        
//        int i,j;
//        for(i=0;i<bufSize;i+=512) {
//          List<Integer> frame = new ArrayList<>();
//          for(j=0;j<256;j+=2) {
//            byte[] buf=new byte[2];
//            buf[0]=buffer[i*512+j];
//            buf[1]=buffer[i*512+j+1];
//            
//            frame.add(readInt(buf));
//          }
//          data.add(frame);
//        }
//        
//        //端點檢測
//        List<List<Integer>> after=new ArrayList<>();  //samples after processing
//        int i1 = 0, j1 = 0;
//        int length = data.size();
//        for (i1 = 0; i1 < length; i1++) // every frame's energy
//        {
//          long en = 0;
//          List<Integer> temp = data.get(i1); // frame i
//          int size = temp.size();
//          for (j1 = 0; j1 < size; j1++) {
//            en += Math.pow(temp.get(j1), 2); // energy
//          }
//          
//          if(en>50000000) {     //单门限法
//            after.add(temp);
//          }
//        }
//        
//        FileOutputStream audiofile=new FileOutputStream("D:\\temp.pcm");
//        for(List<Integer> list:after) {
//          for(Integer integer:list) {
//            byte byte1= (byte)(integer&0xFF);
////            System.out.println(byte1);
//            byte byte2=(byte)((integer>>8)&0xFF);
////            System.out.println(byte2);
//            audiofile.write(byte1);
//            audiofile.write(byte2);
//          }
//        }
//        audiofile.close();
//        
//        //转换成wav
//        AudioFormat af = new AudioFormat(16000, 16, 1, true, false);
//        File sourceFile = new File("D:\\temp.pcm");
//        FileOutputStream out = new FileOutputStream("D:\\temp.wav");
//        AudioInputStream audioInputStream =
//            new AudioInputStream(new FileInputStream(sourceFile), af, sourceFile.length());
//        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
        new Capture().startRecognize();
      
        Runtime rt = Runtime.getRuntime();
        String cmdStr = "";
        try {
            cmdStr = "hcopy -A -D -T 1 -C D:\\tr_wav.cfg -S D:\\list2.scp";// 要调用的程序路径
            rt.exec("cmd.exe /c start "+cmdStr);
        } catch (Exception e) {
            System.out.println("open failure");
        }

        Recognizer rec=new Recognizer("D:\\temp.mfc");
        this.result.setText(rec.Recognize());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
//  private int readInt(byte[] buf) {
//    int res = (buf[0] & 0x000000FF) | (((int) buf[1]) << 8);
//    return res;
//  }
}
