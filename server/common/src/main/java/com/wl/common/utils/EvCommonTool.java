package com.wl.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:13
 **/
public class EvCommonTool {
    public static String getExceptionMsg(Exception ex) {
        if (ex != null) {
            if (ex instanceof InvocationTargetException) {
                Throwable t = ((InvocationTargetException)ex).getTargetException();
                return StringUtils.isEmpty(t.getMessage()) ? t.toString() : t.getMessage();
            } else {
                return StringUtils.isEmpty(ex.getMessage()) ? ex.toString() : ex.getMessage();
            }
        } else {
            return "";
        }
    }
    public static void responseInfo(HttpServletResponse response,JrsfReturn ret) {
        String json = JSONObject.toJSONString(ret);

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
            out.flush();
        } catch (IOException e) {
            EvLog.error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    public static LinkedHashMap<String, Field> getClassField(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            LinkedHashMap<String, Field> fieldMap = new LinkedHashMap();
            Field[] var2 = clazz.getDeclaredFields();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field f = var2[var4];
                fieldMap.put(f.getName(), f);
            }

            return fieldMap.size() > 0 ? fieldMap : null;
        }
    }

    public static LinkedHashMap<String, Field> getClassAllField(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            LinkedHashMap fieldMap;
            for(fieldMap = new LinkedHashMap(); !clazz.getName().equals(Object.class.getName()); clazz = clazz.getSuperclass()) {
                Field[] var2 = clazz.getDeclaredFields();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Field f = var2[var4];
                    if (!fieldMap.containsKey(f.getName())) {
                        fieldMap.put(f.getName(), f);
                    }
                }
            }

            return fieldMap.size() > 0 ? fieldMap : null;
        }
    }

    public static LinkedHashMap<String, Method> getClassMethod(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            LinkedHashMap<String, Method> methodMap = new LinkedHashMap();
            Method[] var2 = clazz.getDeclaredMethods();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Method m = var2[var4];
                methodMap.put(m.getName(), m);
            }

            return methodMap.size() > 0 ? methodMap : null;
        }
    }

    public static LinkedHashMap<String, Method> getClassAllMethod(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            LinkedHashMap methodMap;
            for(methodMap = new LinkedHashMap(); !clazz.getName().equals(Object.class.getName()); clazz = clazz.getSuperclass()) {
                Method[] var2 = clazz.getDeclaredMethods();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Method m = var2[var4];
                    if (!methodMap.containsKey(m.getName())) {
                        methodMap.put(m.getName(), m);
                    }
                }
            }

            return methodMap.size() > 0 ? methodMap : null;
        }
    }
    public static Method getMethodIgnoreCase(Class<?> clazz, String methodName, boolean isIgnoreParam, Class... parameterTypes) {
        if (clazz == null) {
            return null;
        } else {
            List<Method> nameList = new ArrayList();
            Map<String, Method> allMethod = getClassAllMethod(clazz);
            Iterator var6 = allMethod.values().iterator();

            Method m;
            while(var6.hasNext()) {
                m = (Method)var6.next();
                if (m.getName().equalsIgnoreCase(methodName)) {
                    nameList.add(m);
                }
            }

            int i;
            if (!isIgnoreParam) {
                for(i = nameList.size() - 1; i > -1; --i) {
                    m = (Method)nameList.get(i);
                    Class<?>[] cps = m.getParameterTypes();
                    if (parameterTypes != null && parameterTypes.length > 0) {
                        boolean[] isMatchList = new boolean[parameterTypes.length];
                        if (cps != null) {
                            if (cps.length == parameterTypes.length) {
                                for(int j = cps.length - 1; j > -1; --j) {
                                    if (parameterTypes[j] != null && cps[j].getName().equals(parameterTypes[j].getName())) {
                                        isMatchList[j] = true;
                                    }
                                }
                            }

                            boolean isMatch = true;
                            boolean[] var11 = isMatchList;
                            int var12 = isMatchList.length;

                            for(int var13 = 0; var13 < var12; ++var13) {
                                boolean b = var11[var13];
                                if (!b) {
                                    isMatch = false;
                                }
                            }

                            if (!isMatch) {
                                nameList.remove(m);
                            }
                        }
                    } else if (cps != null && cps.length != 0) {
                        nameList.remove(m);
                    }
                }
            }

            if (nameList.size() > 1) {
                for(i = nameList.size() - 1; i > -1; --i) {
                    if (!((Method)nameList.get(i)).getName().equals(methodName)) {
                        nameList.remove(i);
                    }
                }
            }

            if (nameList.size() > 1) {
                throw new RuntimeException(clazz.getName() + "方法" + methodName + "有" + nameList.size() + "个");
            } else {
                return nameList.size() > 0 ? (Method)nameList.get(0) : null;
            }
        }
    }
    public static String toString(Object obj) {
        return obj == null ? null : obj.toString();
    }
    public static <T> List<T> findObjects(List<T> list, Predicate<T> predicate) {
        return list != null && predicate != null ? (List)list.stream().filter(predicate).collect(Collectors.toList()) : null;
    }

    public static <T> T findObject(List<T> list, Predicate<T> predicate) {
        if (list != null && predicate != null) {
            Iterator<T> var2 = list.iterator();

            T t;
            do {
                if (!var2.hasNext()) {
                    return null;
                }

                t = var2.next();
            } while(!predicate.test(t));

            return t;
        } else {
            return null;
        }
    }
}
