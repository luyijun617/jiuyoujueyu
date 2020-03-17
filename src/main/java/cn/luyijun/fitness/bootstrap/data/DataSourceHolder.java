package cn.luyijun.fitness.bootstrap.data;

/**
 * Created by Yao on 2016/9/28.
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();

    static void setDataSource(DataSourceType type) {
        holder.set(type);
    }

    static DataSourceType getDataSouce() {
        return holder.get();
    }
}
