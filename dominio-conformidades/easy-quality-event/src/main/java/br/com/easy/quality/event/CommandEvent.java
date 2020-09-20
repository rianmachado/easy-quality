package br.com.easy.quality.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.event.Exception.DomainException;
import br.com.easy.quality.event.command.Command;

public class CommandEvent extends ObservabilityEvent {

	private final Command command;
	private String id;

	public CommandEvent(Command command) {
		startTimer();
		this.command = command;
		id = gerarGUID();
	}

	public Command getCommand() {
		return command;
	}

	@Override
	public Object getSource() {
		return getCommand();
	}

	public String toJson() {

		try {
			var mapper = new ObjectMapper();
			Map<String, Object> message = new HashMap<>(Map.of("event", getOrigin()));
			message.put("content", getCommand());
			message.put("elapsedTimeInMilli", getElapsedTimeInMilli());

			if (hasError()) {
				message.put("message", getException().getMessage());

				if (getException() instanceof DomainException domainException && domainException.hasError()) {
					message.put("errors", domainException.getErrors().toString());
				}
			}

			return mapper.writeValueAsString(message);

		} catch (JsonProcessingException jsonException) {
			return String.format("%s - %s", command, jsonException);
		}
	}

	@Override
	public String gerarGUID() {
		return UUID.randomUUID().toString().substring(0, 7);
	}

	@Override
	public String obterGUID() {
		return id;
	}
}
