package realtime_ui;

/**
 * 什么都有的DTW算法，有框并且有W补偿
 * @author samma
 *
 */
public class DTW_RegAndDiv {
  private float a[][];
  private float b[][];

  public DTW_RegAndDiv(float a[][], float b[][]) {
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
    int w = 0;
    for (int i = 0; i < lena; i++) {
      for (int j = 0; j < lenb; j++) {
        if (1.0 * i / j > 2 || 1.0 * i / j < 0.5 || 1.0 * (lena - i) / (lenb - j) > 2
            || 1.0 * (lena - i) / (lenb - j) < 0.5) {
          result[i][j] = Float.MAX_VALUE;
        } else {
          result[i][j] = 0;
        }
      }
    }
    for (int i = 0; i < lena; i++) {
      for (int j = 0; j < lenb; j++) {

        float tmp = 0;
        for (int k = 0; k < dimension; k++)
          tmp += Math.abs(a[k][i] - b[k][j]); // a的第i帧和b的第j帧的差

        if (j == 0 && i == 0) {
          w+=2;
          result[i][j] = 2 * tmp; // [0][0]初始化
        }

        if (i > 0 && j > 0) {
          float min = getMin(result[i][j - 1]/(w+1), result[i - 1][j - 1]/(w+2), result[i - 1][j]/(w+1));
          if (min == result[i - 1][j - 1]/(w+2)) {
            w += 2;
            result[i][j] = 2 * tmp + min*w;
          } else {
            w++;
            result[i][j] = tmp + min*w;
          }
        }
      }
    }
    distance = result[lena - 1][lenb - 1]/w;
    return distance;
  }
}
