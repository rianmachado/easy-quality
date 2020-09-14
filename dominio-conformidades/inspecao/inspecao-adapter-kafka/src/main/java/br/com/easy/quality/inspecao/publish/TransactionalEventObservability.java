/**
 * @author rianmachado@gmail.com
 */

package br.com.easy.quality.inspecao.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.event.EventConverter;
import br.com.easy.quality.inspecao.adapter.event.ObservabilityEventStore;
import br.com.easy.quality.inspecao.adapter.event.InternalEvent;

@Component
public class TransactionalEventObservability implements ObservabilityEventStore {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventObservability.class);

	private final String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public TransactionalEventObservability(@Value("${custonKafka.loggin.inspecao.topic}") final String topicName,
			final KafkaTemplate<String, String> kafkaTemplate, final EventConverter converter) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void registrar(final InternalEvent event) {
		log.info("Attempting to log {} to topic {}.", event, topicName);
		kafkaTemplate.executeInTransaction(operations -> {
			final String key = event.getId();
			operations.send(topicName, key, event.toJson()).addCallback(this::onSuccess, this::onFailure);
			return true;
		});
	}

	private void onSuccess(final SendResult<String, String> result) {
		log.info("AvroItemEvent '{}' has been written to topic-partition {}-{} with ingestion timestamp {}.",
				result.getProducerRecord().key(), result.getRecordMetadata().topic(),
				result.getRecordMetadata().partition(), result.getRecordMetadata().timestamp());
	}

	private void onFailure(final Throwable t) {
		log.warn("Unable to write AvroItemEvent to topic {}.", topicName, t);
	}
}
