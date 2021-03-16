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
import br.com.easy.quality.event.command.PublishCommand;

public class PublishEvent extends ObservabilityEvent {

	private final PublishCommand command;
	private String id;

	public PublishEvent(PublishCommand command) {
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

	public PublishCommand getCommand() {
		return command;
	}


	public String getOrigin() {
		return getSource().getClass().getSimpleName();
	}

	public boolean hasError() {
		return !isSuccess();
	}

}
