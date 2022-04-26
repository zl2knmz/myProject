package com.cloud.kafka;

import com.alibaba.fastjson.JSON;
import com.cloud.model.UserDO;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Date;
import java.util.Properties;

/**
 * @author zl
 * @date 2019/9/16 18:06
 */
public class Stream {
    public static void main(String[] args) throws Exception {
        // kafka stream
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-application");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.95:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("user_do");
        KTable<String, Long> wordCounts = textLines
                .map((k, v) -> {
                    UserDO user = JSON.parseObject(v, UserDO.class);
                    user.setUpdateDate(new Date());
                    user.setName("hdx_zl");
                    return new KeyValue<>(JSON.toJSONString(user), "1");
                })
                .groupByKey()
                .count();

        wordCounts.toStream().to("user_do_stream", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
