package br.com.easy.quality.inspecao.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.event.Event;
import br.com.easy.quality.event.command.Command;

public class CreateInspecaoEvent implements Event {

	private final Command command;
	private String id;

	public CreateInspecaoEvent(Command command) {
		this.command = command;
		id = UUID.randomUUID().toString().substring(0, 7);
	}

	public Command getCommand() {
		return command;
	}

	
	public String toJson() {

		try {
			var mapper = new ObjectMapper();
			Map<String, Object> message = new HashMap<>(Map.of("event", command.getClass().getSimpleName()));
			message.put("content", getCommand());
			return mapper.writeValueAsString(message);

		} catch (JsonProcessingException jsonException) {
			return String.format("%s - %s", command, jsonException);
		}
	}

	@Override
	public Object getSource() {
		// TODO Auto-generated method stub
		return null;
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
