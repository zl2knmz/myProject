package com.knmz.kafka;

import com.knmz.model.UserDO;
import com.knmz.util.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * @Author: zl
 * @Date: 2019/9/16 18:06
 */
public class Producer {

    public static void main(String[] args) {
        UserDO userDO = new UserDO(12345678L,"wuliWeiyi","技术部","1001",1,"18770913789","pwd",
                "zl", "2019-09-16 18:20:00",1,new Date(),new Date());
        try {
            saveToKafka(Utils.getObjectMapper().writeValueAsString(userDO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToKafka(String text) {
        org.apache.kafka.clients.producer.Producer<String, String> producer = null;
        if (text != null && text.length() > 0) {
            try {
                producer = new KafkaProducer<>(getKafkaConfig());
                UserDO stat = Utils.getObjectMapper().readValue(text, UserDO.class);
                if (stat != null) {
                    ProducerRecord record = new ProducerRecord<>("user_do", Utils.getObjectMapper().writeValueAsString(stat));
                    producer.send(record, (metadata, exception) -> {
                        if (metadata != null) {
                            System.out.println(String.format("data sent successfully. meta(partition=%d, offset=%d)", metadata.partition(), metadata.offset()));
                        } else {
                            exception.printStackTrace();
                        }
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (producer != null) {
                    producer.close();
                }
            }
        }
    }

    private static Properties getKafkaConfig() {
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "192.168.1.95:9092");
        prop.setProperty("client.id", "user_do_" + UUID.randomUUID().toString());
        prop.setProperty("acks", "all");
        prop.setProperty("retries", "0");
        prop.setProperty("batch.size", "16384");
        prop.setProperty("linger.ms", "1");
        prop.setProperty("buffer.memory", "33554432");
        prop.setProperty("max.request.size", "10240000");
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return prop;
    }
}
