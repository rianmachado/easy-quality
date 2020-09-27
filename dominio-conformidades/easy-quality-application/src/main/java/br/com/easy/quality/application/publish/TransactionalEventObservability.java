/**
 * @author rianmachado@gmail.com
 */

package br.com.easy.quality.application.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import br.com.easy.quality.event.Event;
import br.com.easy.quality.event.EventStore;

@Component
public class TransactionalEventObservability implements EventStore {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventObservability.class);

	private final String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	// TODO: CONVERSOR PARA FORMATOS DESEJADOS NA PERSISTENCIA
	// private final EventConverter converter;

	@Autowired
	public TransactionalEventObservability(@Value("${custonKafka.loggin.questionatio.topic}") final String topicName,
			final KafkaTemplate<String, String> kafkaTemplate) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
		// this.converter = converter;
	}

	@Override
	public void registrar(final Event event) {
		log.info("Attempting to log {} to topic {}.", event, topicName);
		kafkaTemplate.executeInTransaction(operations -> {
			final String key = event.obterGUID();
			// TODO: COM CONVERSOR
			// operations.send(topicName, key,
			// converter.from(event)).addCallback(this::onSuccess, this::onFailure);
			operations.send(topicName, key, event.toJson()).addCallback(this::onSuccess, this::onFailure);
			return true;
		});
	}

	private void onSuccess(final SendResult<String, String> result) {
		log.info("QuestionarioEvent '{}' has been written to topic-partition {}-{} with ingestion timestamp {}.",
				result.getProducerRecord().key(), result.getRecordMetadata().topic(),
				result.getRecordMetadata().partition(), result.getRecordMetadata().timestamp());
	}

	private void onFailure(final Throwable t) {
		log.warn("Unable to write QuestionarioEvent to topic {}.", topicName, t);
	}

}
