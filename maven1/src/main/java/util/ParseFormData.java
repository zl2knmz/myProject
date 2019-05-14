package util;

import model.FormData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: zl
 * @Date: 2019/5/14 11:10
 * 解析报名表单中用户的公司、职位、学历
 */
public class ParseFormData {

    /**
     * 报名表单是一条记录一条记录解析的
     */
    public static FormData parseBizExtra(String bizExtra) {
        FormData formData = new FormData();
        if (null != bizExtra && bizExtra.length() > 10) {
            //[{"Key":"I_10001","Value":"阿里巴巴"},{"Key":"I_10002","Value":"研发中心"},{"Key":"I_10003","Value":"数据分析师"}]

            try {
                JSONArray arr = JSONArray.parseArray(bizExtra);
                if (arr != null && arr.size() > 0) {
                    JSONObject obj = null;
                    String key = null;
                    String val = null;
                    for (int i = 0; i < arr.size(); i++) {
                        obj = JSONObject.parseObject(arr.get(i).toString());
                        key = obj.get("Key").toString();
                        val = obj.get("Value").toString();
                        if (key != null && key.contains("I_100") && val != null && val.length() > 0) {
                            if (val.startsWith("[") && val.endsWith("]")) {
                                val = val.substring(1, val.length() - 1);

                                //数据导入一定要替换掉 单引号 以及转义符号，双引号其实无所谓，单引号可以替换成双引号
                                if (val.contains("\"")) {
                                    val = val.replace("\"", "");
                                }

                                if (val.contains("\'")) {
                                    val = val.replace("\'", "");
                                }

                                if (val.contains("\\")) {
                                    val = val.replace("\\", "");
                                }
                            }

                            if (null != val && val.length() > 0) {
                                switch (key) {
                                    case "I_10001":
                                        //公司限定了 256
                                        if (val.length() > 256) {
                                            val = val.substring(0, 256);
                                        }
                                        formData.setCompany(val);
                                        break;
                                    case "I_10003":
                                        //职位限定了 50
                                        if (val.length() > 50) {
                                            val = val.substring(0, 50);
                                        }
                                        formData.setJob(val);
                                        break;
                                    case "I_10009":
                                        //学历限定了 30
                                        if (val.length() > 30) {
                                            val = val.substring(0, 30);
                                        }
                                        formData.setDegree(val);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return formData;
    }
}

