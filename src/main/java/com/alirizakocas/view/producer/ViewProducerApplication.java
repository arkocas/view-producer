package com.alirizakocas.view.producer;

import com.alirizakocas.view.producer.model.ProductViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class ViewProducerApplication {

	public static void main(String[] args) throws FileNotFoundException, JsonProcessingException, InterruptedException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ViewProducerApplication.class, args);

		String kafkaAdress = applicationContext.getEnvironment().getProperty("product.views.kafka.address");
		String topicName = applicationContext.getEnvironment().getProperty("product.views.kafka.topic");

		// setting properties
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAdress);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

		// create the producer
		KafkaProducer<String, ProductViewModel> produce = new KafkaProducer<>(props);
		log.info("Procuder is created");

		//reading file
		File read = new File("./src/main/resources/product-views.json");
		Scanner scan = new Scanner(read);
		while(scan.hasNextLine()){

			String data = scan.nextLine();

			ObjectMapper mapper = new ObjectMapper();
			ProductViewModel productViewModel = mapper.readValue(data, ProductViewModel.class);

			//create the producer record
			ProducerRecord<String, ProductViewModel> record = new ProducerRecord<>(topicName, productViewModel);

			//send data
			produce.send(record);

			TimeUnit.SECONDS.sleep(1);
		}

		//flush and close
		produce.flush();
		produce.close();
	}

}
