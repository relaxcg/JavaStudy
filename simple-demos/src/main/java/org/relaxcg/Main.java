package org.relaxcg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.utils.TypeReference;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author relaxcg
 * @date 2023/11/29 17:46
 */
// @Slf4j
public class Main {

    public static float[][] test1(Mat mat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 将Mat对象转换为数组
        float[][] array = new float[(int) mat.size().height][(int) mat.size().width];
        mat.get(0, 0, array[0]);
        return array;

        // // 打印数组元素
        // for (int i = 0; i < array.length; i++) {
        //     for (int j = 0; j < array[i].length; j++) {
        //         System.out.print(array[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    public static void test1() {
        Mat imageData = Imgcodecs.imread("C:\\Users\\gaocheng\\Desktop\\20231026_153304319.jpg");
        Mat resizedMat = new Mat();
        Imgproc.resize(imageData, resizedMat, new Size(640, 640));
        Mat reshapedMat = new Mat();
        resizedMat.reshape(1, 3 * 640 * 640).convertTo(reshapedMat, -1, 1, 0);

        System.out.println(reshapedMat.channels());
        System.out.println(reshapedMat.size());

        int height = reshapedMat.rows();
        int width = reshapedMat.cols() / reshapedMat.channels();

        System.out.println("channels:"+resizedMat.channels());
        System.out.println(height + ", " + width);

    }

    public static void test() {
        try {
            Object execute = Forest.get("https://ftt-intelligent-cooking-seg.westus.inference.ml.azure.com/v2/models/yoloSeg/versions/1/ready")
                    .addHeader("Authorization", "Bearer 9fPRKDIbp7QbbqNxJNwKTxlpwQaU0tNy")
                    .execute();
            System.out.println("ready");
        } catch (Exception e) {
            System.out.println("ready:" + e.getMessage());
        }
        try {
            Object execute = Forest.get("https://ftt-intelligent-cooking-seg.westus.inference.ml.azure.com/v2/models/yoloSeg/versions/1")
                    .addHeader("Authorization", "Bearer 9fPRKDIbp7QbbqNxJNwKTxlpwQaU0tNy")
                    .execute();
            System.out.println("Model Metadata:" + execute);
        } catch (Exception e) {
            System.out.println("ready:" + e.getMessage());
        }

        try {
            ForestRequest<?> postRequest = Forest.post("https://ftt-intelligent-cooking-seg.westus.inference.ml.azure.com/v2/models/yoloSeg/versions/1/infer");
            postRequest.addHeader("Authorization", "Bearer 9fPRKDIbp7QbbqNxJNwKTxlpwQaU0tNy");
            postRequest.setContentType("application/json");
            postRequest.setReadTimeout(60000);
            postRequest.setConnectTimeout(10000);

            Map<String, Object> body = new HashMap<>();

            // // 读取图像
            Mat imageData = Imgcodecs.imread("C:\\Users\\gaocheng\\Desktop\\20231026_153304319.jpg");

            // 打印原始图像信息
            // System.out.println("1 " + imageData.channels() + " ");
            // System.out.println("2 " + imageData.size());


            // 调整大小为640x640
            Imgproc.resize(imageData, imageData, new Size(640, 640));

            imageData.convertTo(imageData, CvType.CV_32F);

            int height = imageData.rows();
            int width = imageData.cols();
            int channels = imageData.channels();

            float[][][] a3d = new float[channels][height][width];

            for (int i = 0; i < imageData.size().height; i++) {
                for (int j = 0; j < imageData.size().width; j++) {
                    double[] pixel = imageData.get(i, j);
                    a3d[0][i][j] = (float) pixel[0];
                    a3d[1][i][j] = (float) pixel[1];
                    a3d[2][i][j] = (float) pixel[2];
                }
            }



            float[][][][] a4d = new float[1][channels][height][width];
            a4d[0] = a3d;

            body.put("inputs", Lists.newArrayList(new HashMap<String, Object>() {{
                put("name", "images");
                put("shape", Lists.newArrayList(1, 3, 640, 640));
                put("datatype", "FP32");
                put("data", a4d);
            }}));
            body.put("outputs", Lists.newArrayList(new HashMap<String, Object>() {{
                put("name", "output0");
            }}));

            postRequest.addBody(body);

            Map<String, Object> result = postRequest.execute(MAP_TYPE_REFERENCE);
            JSONArray o = (JSONArray) result.get("outputs");
            JSONObject o1 = (JSONObject) o.get(0);
            System.out.println("o1:" + o1.getClass());
            System.out.println(o1.get("name").toString());
            System.out.println(o1.get("shape").toString());
            System.out.println(o1.get("data").getClass());
            JSONArray a = (JSONArray) o1.get("data");
            int size = a.size();
            System.out.println("a:" + size);
            System.out.println(a.get(0));
            // System.out.println("result:" + result);
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    public static void main(String[] args) throws IOException {
        // 加载OpenCV库
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // test();
        // byte a = 64;
        // float f = a;
        // System.out.println(f);
        //
        // // 读取图像
        // Mat imageData = Imgcodecs.imread("C:\\Users\\gaocheng\\Desktop\\20231026_153304319.jpg");
        // // 打印原始图像信息
        // System.out.println("1 " + imageData.channels() + " ");
        // System.out.println("2 " + imageData.size());
        //
        // HighGui.imshow("原图", imageData);
        //
        // // 调整大小为640x640
        // Imgproc.resize(imageData, imageData, new Size(640, 640));
        // HighGui.imshow("新图", imageData);
        //
        // System.out.println("channels:"+imageData.channels());
        //
        // Mat dst = new Mat();
        // Imgproc.cvtColor(imageData, dst, Imgproc.COLOR_RGB2GRAY);
        // HighGui.imshow("灰度", dst);
        //
        // HighGui.waitKey();
        // imageData.release();
        // // 打印原始图像信息
        // System.out.println("3 " + imageData.channels() + " ");
        // System.out.println("4 " + imageData.size());
        //
        // Mat fb32Mat = new Mat();
        // imageData.convertTo(fb32Mat, CvType.CV_32F);
        //
        // System.out.println("5 " + fb32Mat.channels() + " ");
        // System.out.println("6 " + fb32Mat.size());

        // // 添加批次维度
        // Mat batchedImageData = new Mat();
        // Core.expandDims(imageData, batchedImageData, 0);
        // System.out.println("4 " + Arrays.deepToString(batchedImageData.toArray()));
        // System.out.println("4.1 " + batchedImageData.size());
        //
        // // 转换为float32类型
        // batchedImageData.convertTo(batchedImageData, CvType.CV_32F);
        // batchedImageData.
        // System.out.println("5 " + Arrays.deepToString(batchedImageData.toArray()));


        JSONObject jsonObject = JSON.parseObject(new FileInputStream("C:\\Users\\gaocheng\\Desktop\\cooking_foodcategory.json"), JSONObject.class);
        JSONArray array = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < array.size(); i++) {
            JSONObject data = (JSONObject) array.get(i);
            System.out.println(data.get("id"));
            System.out.println(data.get("food_group_name").getClass());
            System.out.println(data.get("food_group_name"));
        }

        // ArrayList<String> strings = Lists.newArrayList("123", "13");
        // String jsonString = JSON.toJSONString(strings);
        // JSONArray objects = JSON.parseArray(jsonString);
        // System.out.println(objects.get(0));
    }


}