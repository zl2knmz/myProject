package com.knmz.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.apache.poi.ss.usermodel.*;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * @date 2019/5/21 14:15
 * 常用工具类
 */
public class Utils {

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return mapper;
    }

    public static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    public static int getRandomIndex(int min, int max) {

        Random random = new Random();
        int index = random.nextInt((max - min) + 1) + min;
        return index;

    }

    public static String MD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return str;
    }


    public static void shutdownAndAwaitTermination(ScheduledExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(10, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }


    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = null;
        if (request != null) {
            //nginx处理保存的客户端IP地址，优先从该请求头解析
            ip = request.getHeader("X-Real-IP");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-FORWARDED-FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                if (ip.indexOf(",") != -1) ip = ip.split(",")[0];
            }
        }
        return ip;
    }

    /**去掉文本中html方法*/
    public static String deleteAllHTMLTag(String source) {

        if(source == null) {
            return "";
        }

        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }

    /**
     * 统一的apache CloseableHttpClient post请求
     */
    public static String post(String url, List<NameValuePair> listParams) {
        String responseText = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            if (listParams != null && !listParams.isEmpty()) {
                httpPost.setEntity(new UrlEncodedFormEntity(listParams, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();
                responseText = EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseText;
    }

    /**
     * 经纬度超过最大值过滤
     */
    public static String reverveLocation(String location) {
        String[] arr = location.split(",");
        if (arr.length > 1) {
            //经度
            String longitude = arr[0].replaceAll(" ", "");
            //纬度
            String latitude = arr[1].replaceAll(" ", "");

            if ((Double.valueOf(longitude) <= 180) && (Double.valueOf(latitude) <= 90)) {
                return latitude + "," + longitude;
            } else {
                return "0.0,0.0";
            }
        }
        return location;
    }

    /**
     * @param str 手机号或邮箱
     *            手机号或邮箱的简单校验
     */
    public static String checkData(String str) {
        if (null != str && str.length() > 0) {
            if (str.contains("@")) {
                //邮箱
                return str;
            } else if (str.length() == 11 && "1".equals(str.substring(0, 1))) {
                //手机号
                return str;
            }
        }
        return null;
    }

    /**
     * @param phone 手机号
     *            隐藏手机号中间4位
     */
    public static String hiddenPhone(String phone) {
        if (null != phone && phone.length() > 7) {
            phone = phone.substring(0, 3) + "****" + phone.substring(7);
            return phone;
        }
        return null;
    }

    /**
     * @param email 邮箱
     *            隐藏邮箱
     */
    public static String hiddenEmail(String email) {
        if (null != email && email.length() > 0) {
            int index = email.indexOf("@");
            if (index > 0) {
                int start = (index + 1) / 2;
                StringBuilder stars = new StringBuilder();
                for (int i = 0; i < start; i++) {
                    stars.append("*");
                }
                email = email.substring(0, start) + stars.toString() + email.substring(index);
                return email;
            }
        }
        return null;
    }

    /**
     * 上传excel文件
     *
     * @param name 文件名
     * @param savePath 文件保存路径
     * @return 上传后文件名（含路径）
     */
    public static String saveFile(InputStream stream, String name, String savePath) {
        String ret = null;
        if (TextUtils.isBlank(savePath)) {
            savePath = System.getProperty("user.dir");
            savePath = Paths.get(savePath, "uploads").toString();
        }
        if (stream != null && !TextUtils.isBlank(name)) {
            try {
                java.nio.file.Path path = FileSystems.getDefault().getPath(Paths.get(savePath, name).toString());
                if (!Files.exists(path.getParent())) {
                    path.getParent().toFile().mkdirs();
                }
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                Files.copy(stream, path);
                ret = path.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 读取 excel名单
     *
     * @param path 文件路径
     * @return 收件人名单列表
     */
    public static List<String> parseExcel(String path) {
        List<String> ret = null;
        if (!TextUtils.isBlank(path)) {
            try {
                Workbook workbook = WorkbookFactory.create(new File(path));
                if (workbook != null && workbook.getNumberOfSheets() > 0) {
                    //读取第一个工作表
                    Sheet sheet = workbook.getSheetAt(0);
                    if (sheet != null && sheet.getPhysicalNumberOfRows() > 0) {
                        ret = new ArrayList<>();
                        List<String> finalRet = new ArrayList<>();
                        DecimalFormat df = new DecimalFormat("0");
                        sheet.forEach(row -> {
                            //读取第一列数据
                            Cell cell = row.getCell(0);
                            String cellValue = null;
                            //邮箱
                            if (cell.getCellTypeEnum() == CellType.STRING) {
                                cellValue = cell.getRichStringCellValue().getString();
                            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                //手机号
                                cellValue = df.format(cell.getNumericCellValue());;
                            }

                            if (!TextUtils.isBlank(cellValue)) {
                                //数据校验
                                String data = checkData(cellValue.trim());
                                finalRet.add(data);
                            }

                        });
                        ret = finalRet;
                    }
                    workbook.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 校验发送时间,限定30分钟后48小时内有效。
     *
     * @param sendAt 发送时间
     * @return true or false
     */
    public static boolean checkSendAt(Date sendAt) {
        return sendAt != null
                && sendAt.after(new Date(System.currentTimeMillis() + (30 * 60 * 1000)))
                && sendAt.before(new Date(System.currentTimeMillis() + (48 * 3600 * 1000)));
    }

    public static long getTimeInMillisBeforeTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }
}
