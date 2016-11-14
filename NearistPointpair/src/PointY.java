/**
 * Created by hongjiyao_2014150120 on 16-9-19.
 */
public class PointY extends Point {
    public int p;// 同一点在数组x中的坐标排好序后的序号（方便从中间切割）

    @Override
    public boolean small(Point a) {
        return this.y <= a.y;
    }
}
