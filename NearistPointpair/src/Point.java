/**
 * Created by hongjiyao_2014150120 on 16-9-19.
 */
abstract public class Point {
    protected double x;
    protected double y;
    public abstract boolean small(Point a);// 比较器
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
