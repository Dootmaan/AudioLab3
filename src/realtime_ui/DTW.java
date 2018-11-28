package realtime_ui;

public class DTW {
  private float a[][];
  private float b[][];

  public DTW(float a[][], float b[][]) {
    this.a = a;
    this.b = b;
  }

  public float getMin(float a, float b, float c) {
    float min = a;
    if (b < min) {
      min = b;
    }
    if (c < min) {
      min = c;
    }
    return min;
  }

  public float dtw() {
    float distance = 0;
    int lena = a[0].length;
    int lenb = b[0].length;
    int dimension = a.length;
    float[][] result = new float[lena][lenb];
    for (int i = 0; i < lena; i++) {
      for (int j = 0; j < lenb; j++) {
        result[i][j] = 1;
      }
    }
    for (int i = 0; i < lena; i++) {
      for (int j = 0; j < lenb; j++) {
        
        float tmp = 0;
        for (int k = 0; k < dimension; k++)
          tmp += Math.abs(a[k][i] - b[k][j]);    //a的第i帧和b的第j帧的差
        
        if (j == 0 && i == 0)
          result[i][j] = tmp;         //[0][0]初始化
        else if (i == 0 && j > 0)
          result[i][j] = result[i][j - 1] + tmp;    //先填最下面一行
        
        if (i > 0) {
          if (j == 0)
            result[i][j] = tmp + result[i - 1][j];   //i行最左边填好
          else
            result[i][j] = getMin(result[i][j - 1]+tmp, result[i - 1][j - 1]+2*tmp, result[i - 1][j]+tmp);
        }
      }
    }
    distance = result[lena - 1][lenb - 1];
    return distance;
  }
}
