package com.lunatech.kafka.producer;

import com.lunatech.kafka.common.java.Introduction;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@SuppressWarnings("Duplicates")
public class KafkaProducerMain {
    private static String BOOTSTRAP_SERVERS = "mtn.westeurope.cloudapp.azure.com:9092";

    private static String AUTHOR = null; // f.e; "michiel";

    private static String TOPIC = "introduction";

    public static void main(String ... args) {

        sendIntroduction();

//        sendTransaction();

    }


    private static void sendIntroduction() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, String.format("introduction-producer-%s", AUTHOR));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntroductionSerializer.class.getName());

        Producer<String, Introduction> producer = new KafkaProducer<>(props);

        ProducerRecord<String, Introduction> record = null; // Create ProducerRecord, key by AUTHOR

        producer.send(
                record,
                (RecordMetadata metadata, Exception exception) -> System.out.println(String.format("!!SEND!! %s", record.toString()))
        );

        // Record will (probably) NOT be send! (But might on very slow networks..) Why?

        // Flush
    }


    private static void sendTransaction() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, String.format("introduction-producer-%s", AUTHOR));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntroductionSerializer.class.getName());

        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, AUTHOR);


        Producer<String, Introduction> producer = new KafkaProducer<>(props);

        ProducerRecord<String, Introduction> record = null;

        // Init Transaction

        // Begin Transaction

        // Send Record(s)

        // Commit Transaction

        // Flush
    }
}
