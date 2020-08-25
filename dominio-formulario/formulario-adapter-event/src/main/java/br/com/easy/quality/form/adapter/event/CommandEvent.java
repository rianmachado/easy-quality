package br.com.easy.quality.form.adapter.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.form.adapter.event.command.Command;
import br.com.easy.quality.form.domain.exception.DomainException;

public class CommandEvent extends InternalEvent {

	private final Command command;

	public CommandEvent(Command command) {
		startTimer();
		this.command = command;
		super.setId(UUID.randomUUID().toString().substring(0, 7));
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
}
