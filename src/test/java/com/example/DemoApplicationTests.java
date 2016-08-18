package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {DemoApplication.class, DemoApplicationTests.Config.class})
public class DemoApplicationTests {
	@Autowired
	KafkaProperties kafkaProperties;
	@Autowired
	EmbeddedKafka embeddedKafka;
	
	@Test
	public void contextLoads() {
		assertThat(kafkaProperties.getPort()).isNotEqualTo(123);
		assertThat(kafkaProperties.getPort()).isGreaterThan(0);
		
		assertThat(kafkaProperties.getPort()).isEqualTo(embeddedKafka.getPort());
	}

	@Configuration
	static class Config {
		@Bean
		public EmbeddedKafka kafka(KafkaProperties kafkaProperties) {
			EmbeddedKafka embeddedKafka = new EmbeddedKafka();
			embeddedKafka.setPort(kafkaProperties.getPort());
			return embeddedKafka;
		}
	}
}
