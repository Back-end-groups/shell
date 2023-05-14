//package com.improve.shell.config;
//
///**
// * @Author: fengxin
// * @CreateTime: 2023-05-12  22:11
// * @Description: TODO
// */
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.util.*;
//
//public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
//
//    public HeaderMapRequestWrapper(HttpServletRequest request) {
//        super(request);
//    }
//
//    private Map<String, String> headerMap = new HashMap<>();
//
//    /**
//     * add a header with given name and value
//     *
//     * @param name
//     * @param value
//     */
//    public void addHeader(String name, String value) {
//        headerMap.put(name, value);
//    }
//    @Override
//    public String getHeader(String name) {
//        String headerValue = super.getHeader(name);
//        if (headerMap.containsKey(name)) {
//            headerValue = headerMap.get(name);
//        }
//        return headerValue;
//    }
//
//    /**
//     * get the Header names
//     */
//    @Override
//    public Enumeration<String> getHeaderNames() {
//        List<String> names = Collections.list(super.getHeaderNames());
//        for (String name : headerMap.keySet()) {
//            names.add(name);
//        }
//        return Collections.enumeration(names);
//    }
//
//    @Override
//    public Enumeration<String> getHeaders(String name) {
//        List<String> values = Collections.list(super.getHeaders(name));
//        if (headerMap.containsKey(name)) {
//            values = Arrays.asList(headerMap.get(name));
//        }
//        return Collections.enumeration(values);
//    }
//}
//
