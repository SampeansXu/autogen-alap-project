package cn.hqwx.autogen.alap.cloud.service.autogen.utils;

import cn.hqwx.autogen.alap.cloud.model.ApplicationMetadata;
import cn.hqwx.autogen.alap.cloud.model.ProjectMetadata;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-09-28
 */
public class MetaDataUtil {
    private static Map<String, Object> toMap(Object bean, boolean ignoreNull) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(bean) : null;
                resultMap.put(key, value);
            }
            resultMap.remove("class");

            Set<String> set = resultMap.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (ignoreNull && Objects.isNull(resultMap.get(key))) {
                    resultMap.remove(key);
                    set = resultMap.keySet();
                    it = set.iterator();
                }
            }
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> toMap(ApplicationMetadata applicationMetadata, ProjectMetadata projectMetadata) {
        Map<String, Object> resultMap = new HashMap<>();

        if (Objects.nonNull(applicationMetadata)) {
//            Map<String, Object> dataMap = Arrays.stream(BeanUtils.getPropertyDescriptors(applicationMetadata.getClass()))
//                    .filter(itm -> !"class".equals(itm.getName()))
//                    .collect(HashMap::new,
//                            (map, pd) -> map.put(pd.getName(), ReflectionUtils.invokeMethod(pd.getReadMethod(), applicationMetadata)),
//                            HashMap::putAll);
            Map<String, Object> dataMap=toMap(applicationMetadata, true);
            if(!CollectionUtils.isEmpty(dataMap)) {
                resultMap.putAll(dataMap);
            }
        }

        if (Objects.nonNull(projectMetadata)) {
            Map<String, Object> dataMap=toMap(projectMetadata, true);
            if(!CollectionUtils.isEmpty(dataMap)) {
                resultMap.putAll(dataMap);
            }
        }

        return resultMap;
    }

    public static void main(String[] args) {

    }
}
