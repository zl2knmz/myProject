package com.knmz.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knmz.db.pool.DataProcess;
import com.knmz.util.FileOutStreamUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @date 2019/6/25 18:41
 */
public class JsonTest {
    /**
     * json文件路径
     */
    private static final String FILEPATH = "E:" + File.separator + "Program Files" + File.separator + "image" + File.separator;

    public static void main(String[] args) {
//        jobTest();
        jobIdToNameTest();
//        cityIdToNameTest();
    }

    /**
     * 组装两层职位格式json,如下格式：
     * [{"技术":{"jobs":{"电子/半导体":1010,"其他技术职位":1014,"软件销售支持":1013,"测试":1003},"id":10}},{"产品":{"jobs":{"高端产品职位":1102,"其他产品职位":1103,"产品经理":1101},"id":11}}]
     */
    public static void jobTest() {
        JSONArray jsonArray = new JSONArray();

        //第一层
        String sql1 = "select `NAME`,ID from job_data WHERE LEVEL = 1";

        //第二层
        String sql2 = "SELECT `NAME`,ID FROM job_data WHERE LEVEL = 2";

        List<Map<String, Object>> list1 = DataProcess.executeQuery(sql1, null);
        for(Map<String, Object> map1 : list1){
            JSONObject jsonObject = new JSONObject();
            //第一层数据
            JSONObject jsonObject1 = new JSONObject();
            String key1 = map1.get("NAME").toString();
            Integer value1 = Integer.valueOf(map1.get("ID").toString());
            jsonObject1.put("id", value1);

            //第二层数据
            JSONObject jsonObject2 = new JSONObject();
            String sql = sql2 + " AND ID LIKE '" + value1 + "%'";
            List<Map<String, Object>> list2 = DataProcess.executeQuery(sql, null);
            for(Map<String, Object> map2 : list2){
                String key2 = map2.get("NAME").toString();;
                Integer value2 = Integer.valueOf(map2.get("ID").toString());
                jsonObject2.put(key2, value2);
            }

            jsonObject1.put("jobs", jsonObject2);
            jsonObject.put(key1, jsonObject1);
            jsonArray.add(jsonObject);
        }

        String fileName = "jobLevel.json";
        FileOutStreamUtil.charOutStream(FILEPATH, fileName, jsonArray.toJSONString());

        System.out.println("json文件路径：" + FILEPATH + " 文件名：" + fileName);
    }

    /**
     * jobId -> name 格式json,如下格式：
     * {"240401":"高端供应链职位","240403":"物流总监"}
     */
    public static void jobIdToNameTest() {
        JSONObject jsonObject = new JSONObject();
        String sql = "select `ID`,`NAME` from job_data";

        List<Map<String, Object>> list = DataProcess.executeQuery(sql, null);
        for(Map<String, Object> map : list){
            String key = map.get("ID").toString();
            String value = map.get("NAME").toString();
            jsonObject.put(key, value);
        }

        String fileName = "jobIdToName.json";
        FileOutStreamUtil.charOutStream(FILEPATH, fileName, jsonObject.toJSONString());

        System.out.println("json文件路径：" + FILEPATH + " 文件名：" + fileName);
    }

    /**
     * cityId -> name 格式json,如下格式：
     * {"330683":"嵊州市","2306":"大庆市","2305":"双鸭山市"}
     */
    public static void cityIdToNameTest() {
        JSONObject jsonObject = new JSONObject();
        String sql = "select `CODE`,`NAME` from `area`";

        List<Map<String, Object>> list = DataProcess.executeQuery(sql, null);
        for(Map<String, Object> map : list){
            String key = map.get("CODE").toString();
            String value = map.get("NAME").toString();
            jsonObject.put(key, value);
        }

        String fileName = "cityIdToName.json";
        FileOutStreamUtil.charOutStream(FILEPATH, fileName, jsonObject.toJSONString());

        System.out.println("json文件路径：" + FILEPATH + " 文件名：" + fileName);
    }
}
