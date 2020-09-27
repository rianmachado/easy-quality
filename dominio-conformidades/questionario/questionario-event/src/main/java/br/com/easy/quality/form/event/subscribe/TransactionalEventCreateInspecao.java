package br.com.easy.quality.form.event.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TransactionalEventCreateInspecao {

	private static final Logger log = LoggerFactory.getLogger(TransactionalEventCreateInspecao.class);

	private ConsummerCreateInspecaoHandlerEvent consummerCreateInspecaoHandlerEvent;

	@KafkaListener(topics = "${custonKafka.integration.cadastro.inspecao.questionario}", groupId = "${kafka.consumer.groupId}")
	public void consume(final String itemEvent, final Acknowledgment ack) {

		log.info("Received event {}. Trying to apply it to the latest state of aggregate with ID {}. " + itemEvent);
		try {

			consummerCreateInspecaoHandlerEvent.onEvent(itemEvent).thenRun(ack::acknowledge);

		} catch (Exception e) {
			// log the exception and do *not* acknowledge the event
			log.warn("Unable to apply event {} to the latest state of aggregate with ID {}.", e);
		}

	}
}