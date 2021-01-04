package br.com.easy.quality.inspecao.adapter.kafka.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class EventRecuperaQuestionario {

	private static final Logger log = LoggerFactory.getLogger(EventRecuperaQuestionario.class);

	@Autowired
	private HandlerEventRecuperaQuestionario consummerQuestionarioHandlerEvent;

	@KafkaListener(topics = "${custonKafka.entregas.questionarios}", groupId = "${spring.kafka.consumer.groupId}")
	public void consume(final String itemEvent, final Acknowledgment ack) {
		log.info("Received event {}. Trying to apply it to the latest state of aggregate with ID {}. " + itemEvent);
		consummerQuestionarioHandlerEvent.onEvent(itemEvent).thenRun(ack::acknowledge);

	}
}
