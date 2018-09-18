package com.ty.show.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtil {

    // 定义jackson库ObjectMapper类
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * @param data
     * @return
     */
    public static String beanToJson(Object data) {
        try {
            String str = mapper.writeValueAsString(data);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     * @param  jsonData
     * @return
     */
    public static <T> T jsonToBean(String jsonData, Class<T> beanType) {
        try {
            T t = mapper.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 与javaBean json数组字符串转换为列表
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = (List<T>) mapper.readValue(jsonArrayStr, javaType);
        return lst;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}