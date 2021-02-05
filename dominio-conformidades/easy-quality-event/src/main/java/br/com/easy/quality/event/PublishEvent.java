package br.com.easy.quality.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.easy.quality.event.Exception.DomainException;
import br.com.easy.quality.event.command.Command;
import lombok.Data;

@Data
public class PublishEvent implements Event {

	private Exception exception;
	private final Command command;
	private String id;
	private long stopTime;

	public PublishEvent(Command command) {
		this.command = command;
		id = gerarGUID();
	}

	@Override
	public Object getSource() {
		return getCommand();
	}

	@Override
	public String toJson() {

		try {
			var mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			Map<String, Object> message = new HashMap<>(Map.of("event", getOrigin()));
			message.put("content", getCommand());
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
	public String getJson() {

		try {
			var mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			JsonNode root = mapper.createObjectNode();
			JsonNode node = mapper.readTree(getCommand().getBodyJson());
			ObjectNode message = (ObjectNode) root;
			message.set("content", node);
			message.put("event", getOrigin());
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

	public Command getCommand() {
		return command;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getOrigin() {
		return getSource().getClass().getSimpleName();
	}

	public boolean hasError() {
		return !isSuccess();
	}

	public boolean isSuccess() {
		return exception == null;
	}

	public void stopTimer() {
		stopTime = System.nanoTime();
	}
}
