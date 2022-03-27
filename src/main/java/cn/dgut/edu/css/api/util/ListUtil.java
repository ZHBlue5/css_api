package cn.dgut.edu.css.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZHBlue
 * @since 2021/10/22 09:57
 */
public class ListUtil {
    /**
     * list1与list2的差集
     */
    public static <T> List<T> subtract(final List<T> list1, final List<T> list2) {
        final ArrayList<T> result = new ArrayList<>(list1);
        for (T t : list2) {
            result.remove(t);
        }
        return result;
    }

    /**
     * set的差集
     */
    public static <T> Set<T> subtract(final Set<T> list1, final Set<T> list2) {
        final HashSet<T> result = new HashSet<>(list1);
        for (T t : list2) {
            result.remove(t);
        }
        return result;
    }

    /**
     * list1与list2的交集
     */
    public static <T> List<T> retainAll(Collection<T> collection, Collection<T> retain) {
        List<T> list = new ArrayList<>(Math.min(collection.size(), retain.size()));
        for (T obj : collection) {
            if (retain.contains(obj)) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 对List进行分页
     * 
     * @param list
     *            数据源
     * @param currentPage
     *            当前页
     * @param size
     *            每页显示的记录数
     * @param pageNum
     *            总页数
     * @return
     */
    public static List getPageList(List list, int currentPage, int size, int pageNum) {

        // 从哪里开始截取
        int fromIndex = 0;
        // 截取几个
        int toIndex = 0;
        if (list == null || list.size() == 0) {
            return null;
        }
        // 当前页小于或等于总页数时执行
        if (currentPage <= pageNum && currentPage != 0) {
            fromIndex = (currentPage - 1) * size;

            if (currentPage == pageNum) {
                toIndex = list.size();

            } else {
                toIndex = currentPage * size;
            }
        }
        return list.subList(fromIndex, toIndex);
    }
}
