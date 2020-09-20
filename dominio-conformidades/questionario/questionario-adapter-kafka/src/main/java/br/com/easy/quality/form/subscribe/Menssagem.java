package br.com.easy.quality.form.subscribe;

import br.com.easy.quality.form.adapter.event.subscribe.MessageCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Menssagem implements MessageCommand {

	private String tag;
	private String body;

}
