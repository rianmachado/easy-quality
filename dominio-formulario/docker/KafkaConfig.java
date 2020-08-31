package br.com.sascar.devicetickets.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import br.com.sascar.ticket.avro.DeviceTicketCreateEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.spring.TracingConsumerFactory;
import io.opentracing.contrib.kafka.spring.TracingProducerFactory;

@Configuration
public class KafkaConfig {

	private static final String SASL_SSL = "SASL_SSL";

	private static final String SCRAM_SHA_512 = "SCRAM-SHA-512";

	@Value(value = "${spring.kafka.consumer.bootstrap.servers}")
	private String consumerBootstrapAddress;

	@Value(value = "${spring.kafka.consumer.schema.registry}")
	private String consumerSchemaRegistry;

	@Value(value = "${spring.kafka.consumer.topic}")
	private String consumerTopic;

	@Value(value = "${spring.kafka.consumer.groupId}")
	private String consumerGroupId;

	@Value(value = "${spring.kafka.consumer.username}")
	private String consumerUsername;

	@Value(value = "${spring.kafka.consumer.password}")
	private String consumerPassword;

	@Value(value = "${spring.kafka.consumer.keystore.path}")
	private String consumerKeystorePath;

	@Value(value = "${spring.kafka.consumer.truststore.path}")
	private String consumerTrustStorePath;

	@Value(value = "${spring.kafka.consumer.keystore.password}")
	private String consumerKeystorePathPwd;

	@Value(value = "${spring.kafka.consumer.truststore.password}")
	private String consumerTrustStorePathPwd;

	@Value(value = "${spring.kafka.producer.bootstrap.servers}")
	private String producerBootstrapAddress;

	@Value(value = "${spring.kafka.producer.schema.registry}")
	private String producerSchemaRegistry;

	@Value(value = "${spring.kafka.producer.topic}")
	private String producerTopic;

	@Value(value = "${spring.kafka.producer.username}")
	private String producerUsername;

	@Value(value = "${spring.kafka.producer.password}")
	private String producerPassword;

	@Value(value = "${spring.kafka.producer.keystore.path}")
	private String producerKeystorePath;

	@Value(value = "${spring.kafka.producer.truststore.path}")
	private String producerTrustStorePath;

	@Value(value = "${spring.kafka.producer.keystore.password}")
	private String producerKeystorePathPwd;

	@Value(value = "${spring.kafka.producer.truststore.password}")
	private String producerTrustStorePathPwd;

	private KafkaTemplate<String, DeviceTicketCreateEvent> template;

	@Autowired
	private Tracer tracer;
	
	@Bean
	public KafkaTemplate<String, DeviceTicketCreateEvent> kafkaTemplate(
			@Autowired ProducerFactory<String, DeviceTicketCreateEvent> producerFactory) {
		template = new KafkaTemplate<>(producerFactory);
		return template;
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", "device-tickets");
	}

	@Bean
	public ConsumerFactory<String, DeviceTicketCreateEvent> consumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put("bootstrap.servers", consumerBootstrapAddress);
		configProps.put("acks", "1");
		configProps.put("key.deserializer", StringDeserializer.class.getName());
		configProps.put("value.deserializer", KafkaAvroDeserializer.class.getName());
		configProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
		configProps.put("schema.registry.url", consumerSchemaRegistry);

		configProps.put("ssl.truststore.location", consumerTrustStorePath);
		configProps.put("ssl.truststore.password", consumerTrustStorePathPwd);
		configProps.put("ssl.keystore.location", consumerKeystorePath);
		configProps.put("ssl.keystore.password", consumerKeystorePathPwd);
		String jaasConfig = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""
				+ consumerUsername + "\" password=\"" + consumerPassword + "\";";
		configProps.put("sasl.jaas.config", jaasConfig);
		configProps.put("sasl.mechanism", SCRAM_SHA_512);
		configProps.put("security.protocol", SASL_SSL);
		configProps.put("ssl.endpoint.identification.algorithm", ""); 
		// Interceptor
		configProps.put("consumer.interceptor.classes",
				"io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor,io.opentracing.contrib.kafka.TracingConsumerInterceptor");
		configProps.put("confluent.monitoring.interceptor.bootstrap.servers", consumerBootstrapAddress);
		configProps.put("confluent.monitoring.interceptor.security.protocol", SASL_SSL);
		configProps.put("confluent.monitoring.interceptor.sasl.mechanism", SCRAM_SHA_512);
		configProps.put("confluent.monitoring.interceptor.ssl.endpoint.identification.algorithm", "");
		configProps.put("confluent.monitoring.interceptor.ssl.truststore.location", consumerTrustStorePath);
		configProps.put("confluent.monitoring.interceptor.ssl.truststore.password", consumerTrustStorePathPwd);
		configProps.put("confluent.monitoring.interceptor.sasl.jaas.config", jaasConfig);
		configProps.put("client.id", "device-tickets");

		// definindo certificado para autenticar via https com o schema registry
		System.setProperty("javax.net.ssl.keyStore", consumerKeystorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", consumerKeystorePathPwd);

		return new TracingConsumerFactory<>(new DefaultKafkaConsumerFactory<>(configProps), tracer);
	}

	@Bean
	public ProducerFactory<String, DeviceTicketCreateEvent> producerFactory() {
		Map<String, Object> configProps = new HashMap<>(6);
		configProps.put("bootstrap.servers", producerBootstrapAddress);
		configProps.put("acks", "1");
		configProps.put("retries", "3");
		configProps.put("key.serializer", StringSerializer.class);
		configProps.put("value.serializer", KafkaAvroSerializer.class);
		configProps.put("schema.registry.url", producerSchemaRegistry);

		configProps.put("ssl.truststore.location", producerTrustStorePath);
		configProps.put("ssl.truststore.password", producerTrustStorePathPwd);
		configProps.put("ssl.keystore.location", producerKeystorePath);
		configProps.put("ssl.keystore.password", producerKeystorePathPwd);
		String jaasConfig = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""
				+ producerUsername + "\" password=\"" + producerPassword + "\";";
		configProps.put("sasl.jaas.config", jaasConfig);
		configProps.put("sasl.mechanism", SCRAM_SHA_512);
		configProps.put("security.protocol", SASL_SSL);
		configProps.put("ssl.endpoint.identification.algorithm", "");
		// Interceptor
		configProps.put("consumer.interceptor.classes",
				"io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor,io.opentracing.contrib.kafka.TracingProducerInterceptor");
		configProps.put("confluent.monitoring.interceptor.bootstrap.servers", producerBootstrapAddress);
		configProps.put("confluent.monitoring.interceptor.security.protocol", SASL_SSL);
		configProps.put("confluent.monitoring.interceptor.sasl.mechanism", SCRAM_SHA_512);
		configProps.put("confluent.monitoring.interceptor.ssl.endpoint.identification.algorithm", "");
		configProps.put("confluent.monitoring.interceptor.ssl.truststore.location", producerTrustStorePath);
		configProps.put("confluent.monitoring.interceptor.ssl.truststore.password", producerTrustStorePathPwd);
		configProps.put("confluent.monitoring.interceptor.sasl.jaas.config", jaasConfig);
		configProps.put("interceptor.classes",
				"io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");
		configProps.put("client.id", "device-tickets-producer");

		// definindo certificado para autenticar via https com o schema registry
		System.setProperty("javax.net.ssl.keyStore", producerKeystorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", producerKeystorePathPwd);
		return new TracingProducerFactory<>(new DefaultKafkaProducerFactory<>(configProps), tracer);
	}

	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
		exponentialBackOffPolicy.setInitialInterval(1000L);
		exponentialBackOffPolicy.setMultiplier(2L);
		exponentialBackOffPolicy.setMaxInterval(60000L);
		retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(5);
		retryTemplate.setRetryPolicy(retryPolicy);
		return retryTemplate;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DeviceTicketCreateEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DeviceTicketCreateEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setRetryTemplate(retryTemplate());
		factory.setErrorHandler(
				new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer((KafkaTemplate) template), 10));
		return factory;
	}

}
