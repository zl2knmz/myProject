package com.knmz.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knmz.db.pool.DataProcess;
import com.knmz.util.FileOutStreamUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author: zl
 * @Date: 2019/6/25 18:41
 */
public class JsonTest {
    /**
     * json文件路径
     */
    private static final String FILEPATH = "E:" + File.separator + "Program Files" + File.separator + "image" + File.separator;

    public static void main(String args[]) {
        jobTest();
    }

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

        String fileName = "job.json";
        FileOutStreamUtil.charOutStream(FILEPATH, fileName, jsonArray.toJSONString());

        System.out.println("json文件路径：" + FILEPATH + " 文件名：" + fileName);
    }
}
