/**
 * 背包中的物品的数据结构
 * Created by hongjiyao_2014150120 on 16-11-8.
 */
class Item implements Comparable<Item> {
    private static final int MAX_PRICE = 100; // 最大价值
    private int num;    // 物品编号
    int weight;     // 物品重量
    int price;      // 物品价值

    /**
     * 构造函数
     *
     * @param num       物品标号
     * @param maxWeight 物品最大重量
     */
    Item(int num, int maxWeight) {
        this.num = num;
        this.weight = (int) (Math.random() * (maxWeight - 1)) + 1; // 保证质量在 1 ~ maxWeight 之间
        this.price = (int) (Math.random() * MAX_PRICE);
    }

    /**
     * 比较接口
     *
     * @param o 被比较的Item元素
     * @return 比较结果 0表示相等 this大则是1 o大则是-1
     */
    @Override
    public int compareTo(Item o) {
        if (this.weight == o.weight) {
            if (this.price == o.price) {
                return 0;
            }
            return (this.price > o.price) ? 1 : -1;
        }
        return (this.weight > o.weight) ? 1 : -1;
    }


    @Override
    public String toString() {
        return "编号：" + this.num + " 重量：" + this.weight + " 价值：" + this.price;
    }
}
