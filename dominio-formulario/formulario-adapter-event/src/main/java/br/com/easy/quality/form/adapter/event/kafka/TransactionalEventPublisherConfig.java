package br.com.easy.quality.form.adapter.event.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author rianmachado@gmail.com
 */
@Configuration
public class TransactionalEventPublisherConfig {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String BOOTSTRAP_SERVERS_CONFIG;

	@Value("${custonKafka.kamax.in.flight.requests.per.connection}")
	private String MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION;

	@Value("${custonKafka.retries}")
	private String RETRIES_CONFIG;

	@Value("${custonKafka.enable.idempotence}")
	private String ENABLE_IDEMPOTENCE_CONFIG;

	@Value("${custonKafka.transactional.id}")
	private String TRANSACTIONAL_ID_CONFIG;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		final Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
		config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);
		config.put(ProducerConfig.RETRIES_CONFIG, RETRIES_CONFIG);
		config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, ENABLE_IDEMPOTENCE_CONFIG);
		config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, TRANSACTIONAL_ID_CONFIG);
		final DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(config);
		factory.setTransactionIdPrefix(TRANSACTIONAL_ID_CONFIG);
		return factory;
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(@Autowired ProducerFactory<String, String> factory) {
		return new KafkaTemplate<>(factory);
	}
}
