package com.example.demo.Service;

import com.example.demo.properties.PropertiesConfig;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
@SuppressWarnings("unchecked")
public class Aggregate {

        private final PropertiesConfig propertiesConfig = new PropertiesConfig();

        @Autowired
        private ProcessData processData;

        private final Log log = LogFactory.getLog(getClass());

//        @Autowired
//        public Aggregate(ProcessData processData){
//            this.processData = processData;
//        }

        @PostConstruct
        public void init(){

            Topology topology = new Topology();

            Properties properties = new Properties();
            properties = propertiesConfig.createProperties();

            topology.addSource("Source", propertiesConfig.getTopicInitial())
                    .addProcessor("Process", () -> processData, "Source");

            KafkaStreams streams = new KafkaStreams(topology, properties);

            log.info("\n************Starting Stream****************\n");

            streams.start();

            Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        }
}
