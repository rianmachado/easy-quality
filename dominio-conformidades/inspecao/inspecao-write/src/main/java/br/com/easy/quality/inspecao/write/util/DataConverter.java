package br.com.easy.quality.inspecao.write.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DataConverter {

	public LocalDateTime toLocalDateTime(String formato, String data) {
		return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(formato));
	}

	public LocalDateTime toLocalDateTime(String data) {
		return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

}
