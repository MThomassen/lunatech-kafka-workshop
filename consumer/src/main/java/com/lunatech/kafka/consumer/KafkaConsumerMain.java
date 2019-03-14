package com.lunatech.kafka.consumer;

import com.lunatech.kafka.common.java.StockMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaConsumerMain {
    private static String BOOTSTRAP_SERVERS = "mtn.westeurope.cloudapp.azure.com:9092";

    private static String AUTHOR = null; // f.e; "michiel";

    private static Pattern TOPICS = Pattern.compile("introduction");

    public static void main(String ... args) {

        consume();

    }

    private static void consume() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, String.format("stock-ticker-consumer-%s", AUTHOR));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, String.format("consumer-group-%s", AUTHOR));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntroductionDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, StockMessage> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(TOPICS);

        while (true) {
            ConsumerRecords<String, StockMessage> records = consumer.poll(Duration.ofSeconds(10));

            if (records.isEmpty()) {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException ex) {
                    break;
                }
            }


            records.forEach(record -> {
                System.out.println(String.format("!!RECEIVED!! %s", record.toString()));
            });


            // Commit Offsets?
        }
    }
}
