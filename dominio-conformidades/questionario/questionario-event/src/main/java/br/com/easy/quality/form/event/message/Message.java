package br.com.easy.quality.form.event.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Message implements MessageCommand {

	private String tag;
	private String body;

}
