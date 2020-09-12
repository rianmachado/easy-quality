package br.com.easy.quality.inspecao.adapter.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.adapter.event.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInspecaoHandlerEvent {

	private boolean statusEventoCriacaoInspecao = false;

	private final Command command;

	private String id;

	public CreateInspecaoHandlerEvent(Command command) {
		this.command = command;
		this.id = UUID.randomUUID().toString().substring(0, 7);
	}

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

}
