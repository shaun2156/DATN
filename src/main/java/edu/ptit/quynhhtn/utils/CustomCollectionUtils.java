package edu.ptit.quynhhtn.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomCollectionUtils {
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0){
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }
        if(page <= 0){
            throw new IllegalArgumentException("invalid page num: " + page);
        }
        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static <T> boolean isEmpty(Collection<T> collection){
        return collection == null || collection.size() == 0;
    }
}
