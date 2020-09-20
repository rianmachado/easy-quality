package br.com.easy.quality.form.adapter.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.event.ObservabilityEvent;
import br.com.easy.quality.form.adapter.event.subscribe.MessageCommand;

public class ConsummerHandlerEvent extends ObservabilityEvent {

	private final MessageCommand command;
	private String id;

	public ConsummerHandlerEvent(MessageCommand command) {
		startTimer();
		this.command = command;
		id = gerarGUID();
	}

	public MessageCommand getCommand() {
		return command;
	}

	@Override
	public Object getSource() {
		return getCommand();
	}

	@Override
	public String obterGUID() {
		return id;
	}

	@Override
	public String toJson() {
		try {
			var mapper = new ObjectMapper();
			Map<String, Object> message = new HashMap<>();
			message.put("content", getCommand());
			return mapper.writeValueAsString(message);
		} catch (JsonProcessingException jsonException) {
			return String.format("%s - %s", command, jsonException);
		}
	}

	@Override
	public String gerarGUID() {
		return UUID.randomUUID().toString().substring(0, 7);
	}

}
