package com.relaxcg.forest.interceptor;

import com.dtflys.forest.converter.ForestEncoder;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.reflection.ForestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author relaxcg
 * @date 2024/1/12 16:49
 */
@Component
public class SimpleInterceptor implements Interceptor<Object> {


    private final static Logger log = LoggerFactory.getLogger(SimpleInterceptor.class);

    /**
     * 该方法在被调用时，并在beforeExecute前被调用
     *
     * @Param request Forest请求对象
     * @Param args 方法被调用时传入的参数数组
     */
    @Override
    public void onInvokeMethod(ForestRequest req, ForestMethod method, Object[] args) {
        log.info("on invoke method");
        // req 为Forest请求对象，即 ForestRequest 类实例
        // method 为Forest方法对象，即 ForestMethod 类实例
        // addAttribute作用是添加和Forest请求对象以及该拦截器绑定的属性
        addAttribute(req, "A", "value1");
        addAttribute(req, "B", "value2");
    }

    /**
     * 在请求体数据序列化后，发送请求数据前调用该方法
     * 默认为什么都不做
     * 注: multlipart/data类型的文件上传格式的 Body 数据不会调用该回调函数
     *
     * @param request     Forest请求对象
     * @param encoder     Forest转换器
     * @param encodedData 序列化后的请求体数据
     */
    @Override
    public byte[] onBodyEncode(ForestRequest request, ForestEncoder encoder, byte[] encodedData) {
        log.info("on bodyEncode method");
        // request: Forest请求对象
        // encoder: 此次转换请求数据的序列化器
        // encodedData: 序列化后的请求体字节数组
        // 返回的字节数组将替换原有的序列化结果
        // 默认不做任何处理，直接返回参数 encodedData
        if (encodedData.length < 1024) {
            return encodedData;
        }
        request.addHeader("Content-Encoding", "gzip");
        log.info("before gzip:{}", encodedData.length);
        byte[] zippedData = gzip(encodedData);
        log.info("after gzip:{}", zippedData.length);
        return zippedData;
        // return encodedData;
    }

    private byte[] gzip(byte[] encodedData) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(encodedData);
            gzip.close();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 该方法在请求发送之前被调用, 若返回false则不会继续发送请求
     *
     * @Param request Forest请求对象
     */
    @Override
    public boolean beforeExecute(ForestRequest req) {
        log.info("invoke Simple beforeExecute");
        // 执行在发送请求之前处理的代码
        req.addHeader("accessToken", "11111111");  // 添加Header
        req.addQuery("username", "foo");  // 添加URL的Query参数
        return true;  // 继续执行请求返回true
    }

    /**
     * 该方法在请求成功响应时被调用
     */
    @Override
    public void onSuccess(Object data, ForestRequest req, ForestResponse res) {
        log.info("invoke Simple onSuccess");
        // 执行成功接收响应后处理的代码
        int status = res.getStatusCode(); // 获取请求响应状态码
        String content = res.getContent(); // 获取请求的响应内容
        // String result = (String) data;  // data参数是方法返回类型对应的返回数据结果,注意需要视情况修改对应的类型否则有可能出现类转型异常
        // result = res.getResult(); // getResult()也可以获取返回的数据结果
        // response.setResult("修改后的结果: " + result);  // 可以修改请求响应的返回数据结果
        log.info("content:{}", content);
        // 使用getAttributeAsString取出属性，这里只能取到与该Forest请求对象，以及该拦截器绑定的属性
        String attrValue1 = getAttributeAsString(req, "A1");

    }

    /**
     * 该方法在请求发送失败时被调用
     */
    @Override
    public void onError(ForestRuntimeException ex, ForestRequest req, ForestResponse res) {
        log.info("invoke Simple onError");
        // 执行发送请求失败后处理的代码
        int status = res.getStatusCode(); // 获取请求响应状态码
        String content = res.getContent(); // 获取请求的响应内容
        String result = res.getResult().toString(); // 获取方法返回类型对应的返回数据结果
    }

    /**
     * 该方法在请求发送之后被调用
     */
    @Override
    public void afterExecute(ForestRequest req, ForestResponse res) {
        log.info("invoke Simple afterExecute");
        // 执行在发送请求之后处理的代码
        int status = res.getStatusCode(); // 获取请求响应状态码
        String content = res.getContent(); // 获取请求的响应内容
        String result = res.getResult().toString(); // 获取方法返回类型对应的最终数据结果
    }
}


