/**
 * @author rianmachado@gmail.com
 */

package br.com.easy.quality.form.adapter.event.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.easy.quality.form.adapter.event.EventConverter;
import br.com.easy.quality.form.adapter.event.EventPublisher;
import br.com.easy.quality.form.adapter.event.InternalEvent;

@Component
public class TransactionalEventPublisher implements EventPublisher {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventPublisher.class);

	private final String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final EventConverter converter;

	@Autowired
	public TransactionalEventPublisher(@Value("${loggin.questionatio.topic}") final String topicName,
			final KafkaTemplate<String, String> kafkaTemplate, final EventConverter converter) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
		this.converter = converter;
	}

	@Override
	public void log(final InternalEvent event) {
		log.info("Attempting to log {} to topic {}.", event, topicName);
		kafkaTemplate.executeInTransaction(operations -> {
			final String key = event.getId();
			try {
				operations.send(topicName, key, converter.from(event)).addCallback(this::onSuccess, this::onFailure);
			} catch (JsonProcessingException e) {
				log.error(e.getLocalizedMessage(), e);
			}
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
