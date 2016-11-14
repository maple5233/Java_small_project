/**
 * Created by hongjiyao_2014150120 on 16-9-19.
 */
public class PointX extends Point {
    public int ID; // 点编号

    @Override
    public boolean small(Point a) {
        return this.x <= a.x;// 按X坐标进行排序的类
    }
}
