package com.cloud.util;

import com.cloud.model.CityDTO;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @author zl
 * @date 2019/5/21 14:15
 * 根据ip获取对应城市
 */
public class IpToCity {
    /**
     * 普通版
     */
    private static DbSearcher dbSearcher;

    /**
     * 增强版
     */
    private static DatabaseReader ipSearcher;

    /**
     * 调用静态初始化块，初始化客户端。
     */
    static {
        dbSearcher = IpToCity.getDbIpClient();
        ipSearcher = IpToCity.getIpSearcher();
    }

    public static DbSearcher getDbIpClient() {
        String path = "./config/ip2region.db";
        final File dbFile = new File(path);
        DbConfig config = null;
        byte[] mybyte = new byte[0];
        try {
            config = new DbConfig();
            mybyte = IOUtils.toByteArray(new FileInputStream(dbFile));
        } catch (DbMakerConfigException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DbSearcher searcher = new DbSearcher(config, mybyte);
        return searcher;
    }

    public static DatabaseReader getIpSearcher() {
        File database = new File("./config/GeoLite2-City.mmdb");
        DatabaseReader reader = null;
        try {
            reader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    /**
     * 普通版，获取cityId、city
     */
    public static CityDTO getCityName(String ip) {
        CityDTO model = new CityDTO();
        DataBlock dataBlock = null;
        try {
            dataBlock = dbSearcher.memorySearch("113.90.94.16");
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.setCityId(String.valueOf(dataBlock.getCityId()));
        model.setCity(dataBlock.getRegion().split("\\|")[3].replace("市", ""));
        return model;
    }

    /**
     * 增强版，cityId、city、province、country
     */
    public static CityDTO getCity(String ip) {
        CityDTO model = new CityDTO();
        if (StringUtils.isNotBlank(ip)) {
            CityResponse response = null;
            try {
                InetAddress ipAddress = InetAddress.getByName(ip);
                response = ipSearcher.city(ipAddress);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeoIp2Exception e) {
                e.printStackTrace();
            }

            City city = response.getCity();
            Subdivision division = response.getLeastSpecificSubdivision();
            Country country = response.getCountry();
            if (city != null && city.getGeoNameId() != null) {
                model.setCityId(String.valueOf(city.getGeoNameId()));
                if (city.getNames() != null && city.getNames().size() > 0 && city.getNames().get("zh-CN") != null) {
                    model.setCity(city.getNames().get("zh-CN").replace("市", ""));
                } else if (city.getName() != null) {
                    model.setCity(city.getName());
                }
            }
            if (division != null && division.getNames() != null && division.getNames().size() > 0) {
                model.setProvince(division.getNames().get("zh-CN"));
            }

            if (response.getCountry() != null && country.getNames() != null && country.getNames().size() > 0) {
                model.setCountry(country.getNames().get("zh-CN"));
            }

        }
        return model;
    }

    public static void main(String[] args) {

        CityDTO cityDTO = getCityName("113.90.94.16");
        System.out.println(cityDTO.toString());

        CityDTO cityDTO1 = getCity("113.90.94.16");
        System.out.println(cityDTO1.toString());
    }
}
