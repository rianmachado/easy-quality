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

import br.com.easy.quality.event.Event;
import br.com.easy.quality.event.EventStore;

@Component
public class TransactionalEventCreateInspecao implements EventStore {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventCreateInspecao.class);

	private final String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public TransactionalEventCreateInspecao(
			@Value("${custonKafka.integration.cadastro.inspecao.questionario}") final String topicName,
			final KafkaTemplate<String, String> kafkaTemplate) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void registrar(final Event event) {
		log.info("Attempting to log {} to topic {}.", event, topicName);
		kafkaTemplate.executeInTransaction(operations -> {
			final String key = event.obterGUID();
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
