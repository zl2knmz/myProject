package com.knmz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zl
 * @date 2019/5/21 14:22
 */
@Data
public class CityDTO {
    /**
     * cityid
     */
    @JsonProperty("city_id")
    private String cityId;

    /**
     * 城市
     */
    @JsonProperty("city")
    private String city;

    /**
     * 省
     */
    @JsonProperty("province")
    private String province;

    /**
     * 国家
     */
    @JsonProperty("country")
    private String country;
}
