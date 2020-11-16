package br.com.easy.quality.inspecao.adapter.kafka.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TransactionalEventQuestionario {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventQuestionario.class);

	private ConsummerQuestionarioHandlerEvent consummerQuestionarioHandlerEvent;

	@KafkaListener(topics = "${custonKafka.integration.cadastro.inspecao.questionario-enriquecido}", groupId = "${spring.kafka.consumer.groupId}")
	public void consume(final String itemEvent, final Acknowledgment ack) {

		log.info("Received event {}. Trying to apply it to the latest state of aggregate with ID {}. " + itemEvent);
		try {
			consummerQuestionarioHandlerEvent.onEvent(itemEvent).thenRun(ack::acknowledge);
		} catch (Exception e) {
			// log the exception and do *not* acknowledge the event
			log.warn("Unable to apply event {} to the latest state of aggregate with ID {}.", e);
		}

	}
}
