import java.util.Date;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by hongjiyao_2014150120 on 16-9-19.
 */
public class Graph {
    private static PointX[] X; //点对数组
    private static PointX aa; //最近点对其中一个点
    private static PointX bb; //最近点对另外一个点
    private static double dd; //最近点对的距离

    private static void Copy(Point[] a, Point[] b, int left, int right) { // 拷贝数组b至a Type可以是PointX或者PointY
        int i;
        for (i = left; i <= right; i++)
            a[i] = b[i];
    }

    private static double dis(Point u, Point v) { // 返回两个点的距离 Point可以是PointX或者PointY
        double dx = u.x - v.x;
        double dy = u.y - v.y;
        return sqrt(dx * dx + dy * dy);
    }

    /*归并排序 此处采用也是分治法的应用的归并排序 其实用其他排序方法也可以
    由于对不同类定义好了对应的比较器small() Point为PointX时按x坐标排序 为PointY时按照y坐标来排序*/
    private static void Merge(Point[] c, Point[] d, int l, int m, int r) { // c d合并为有序的d
        int i = l, j = m + 1, k = l;
        while ((i <= m) && (j <= r)) {
            if (c[i].small(c[j])) {
                d[k++] = c[i++];
            } else {
                d[k++] = c[j++];
            }
        }
        if (i > m) {
            for (int q = j; q <= r; q++) {
                d[k++] = c[q];
            }
        } else {
            for (int q = i; q <= m; q++) {
                d[k++] = c[q];
            }
        }
    }

    private static void MergeSort(Point[] a, Point[] b, int left, int right) { // 归并排序
        if (left < right) {
            int i = (left + right) / 2;
            MergeSort(a, b, left, i);
            MergeSort(a, b, i + 1, right); // 分割子序列
            Merge(a, b, left, i, right); // 合并到数组b
            Copy(a, b, left, right); // 复制回数组a
        }
    }

    /*求最小点对*/
    private static void closest(PointX[] X, PointY[] Y, PointY[] Z, int l, int r, PointX a, PointX b, double d) {
        int i, j;
        if (r - l == 1) { // 两点的情形 递归基
            a = X[l];
            b = X[r];
            d = dis(X[l], X[r]);
            return;
        }

        if (r - l == 2) { // 三点的情形 递归基
            double d1 = dis(X[l], X[l + 1]);
            double d2 = dis(X[l + 1], X[r]);
            double d3 = dis(X[l], X[r]);

            if (d1 <= d2 && d1 <= d3) {
                a = X[l];
                b = X[l + 1];
                d = d1;
                return;
            }

            if (d2 <= d3) {
                a = X[l + 1];
                b = X[r];
                d = d2;
            } else {
                a = X[l];
                b = X[r];
                d = d3;
            }
            return;
        }
        //多于3点的情形，用分治法 递归到递归基为止
        int m = (l + r) / 2; //为了编码方便起见 这里用头尾平均数 实际应该用中位数
        int f = l, g = m + 1;

        //在算法预处理阶段，已经将数组X中的点依x坐标排序，将数组Y中的点依y坐标排序
        //算法分割阶段，将子数组X[l:r]均匀划分成两个不相交的子集，取m=(l+r)/2
        //X[l:m]和X[m+1:r]就是满足要求的分割。
        for (i = l; i <= r; i++) {
            if (Y[i].p > m)
                Z[g++] = Y[i]; //右半部分为l,m
            else
                Z[f++] = Y[i]; //左半部分为m+1,r
        }

        closest(X, Z, Y, l, m, a, b, d); //求解X[l:m] 把分好的Z当作Y来用分给Y Z和Y作为存放结果的地方交替循环使用


        PointX ar = new PointX(), br = new PointX();
        double dr = 0.0;
        closest(X, Z, Y, m + 1, r, ar, br, dr); //求解X[m+1:r] 把分好的Z当作Y来用分给Y Z和Y作为存放结果的地方交替循环使用

        if (dr < d) {
            a = ar;
            b = br;
            d = dr;
        } //比较两个子问题的解

		/*以上求得两边的最近点对 以下合并解*/
        Merge(Z, Y, l, m, r); //重构数组Y
        //对P1中所有点p，对排好序的点列作一次扫描
        //就可以找出所有最接近点对的候选者，对P1中每一点最多只要检查P2中排好序的相继6个点。

        //d矩形条内的点置于Z[l:k-1]中
        int k = l;
        for (i = l; i <= r; i++) {
            if (abs(X[m].x - Y[i].x) < d) { //abs()是求浮点数的绝对值
                Z[k++] = Y[i];
            }
        }


        //搜索Z[l:k-1]  这种搜索一趟最多只有6个点
        for (i = l; i < k; i++) { //对于每个p
            for (j = i + 1; j < k && Z[j].y - Z[i].y < d; j++) {
                double dp = dis(Z[i], Z[j]);
                if (dp < d) {
                    d = dp;
                    a = X[Z[i].p];
                    b = X[Z[j].p];
                }
            }
        }
    }

    private static boolean Cpair2(int n) { //n是本次比较的问题的点的个数
        /* 预处理 */
        int i;
        if (n < 2) return false;
        PointX[] tmpX = new PointX[n];
        MergeSort(X, tmpX, 0, n - 1); //按x来排序

        PointY[] Y = new PointY[n];
        for (i = 0; i < n; i++) { //将数组X中的点复制到数组Y中
            Y[i] = new PointY();
            Y[i].p = i;
            Y[i].x = X[i].x;
            Y[i].y = X[i].y;
        }

        PointY[] tmpY = new PointY[n];
        MergeSort(Y, tmpY, 0, n - 1); //数组Y按y来排序
        //tmpX tmpY 都是归并排序所需要的临时数组

        PointY[] Z = new PointY[n];
        aa = X[0];
        bb = X[0];
        dd = dis(aa, bb); //初始化
        closest(X, Y, Z, 0, n - 1, aa, bb, dd); //求解问题
        return true;
    }

    private static int initX() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入点对数：");
        int size = in.nextInt();
        X = new PointX[size];
        System.out.println("初始点:");
        for (int i = 0; i < size; i++) {
            X[i] = new PointX();
            X[i].ID = i;
            X[i].x = (int) (Math.random() * 100);
            X[i].y = (int) (Math.random() * 100);
            System.out.print(X[i].toString() + " ");
        }
        System.out.println();
        return size;
    }

    private static void displayResult() {
        System.out.println("最近的点对是点" + aa.toString() + "和" + bb.toString());
        System.out.println("它们的距离是" + Graph.dd);
    }

    public static void main(String[] args) {
        int size = initX();
        long beginTime = new Date().getTime();
        Cpair2(size);
        long endTime = new Date().getTime();
        displayResult();
        System.out.println("总共花费" + (endTime - beginTime) + "毫秒");
    }
}
