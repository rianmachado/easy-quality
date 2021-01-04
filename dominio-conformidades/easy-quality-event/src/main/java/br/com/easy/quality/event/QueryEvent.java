/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.event;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.event.Exception.DomainException;
import br.com.easy.quality.event.query.Query;

public class QueryEvent extends ObservabilityEvent {

	private final Query query;

	public QueryEvent(Query query) {
		startTimer();
		this.query = query;
	}

	public Query getQuery() {
		return query;
	}

	@Override
	public Object getSource() {
		return getQuery();
	}

	@Override
	public String toJson() {

		try {
			var mapper = new ObjectMapper();
			Map<String, Object> message = new HashMap<>(Map.of("event", getOrigin()));
			message.put("content", getQuery());
			message.put("elapsedTimeInMilli", getElapsedTimeInMilli());

			if (hasError()) {
				message.put("message", getException().getMessage());

				if (getException() instanceof DomainException domainException && domainException.hasError()) {
					message.put("errors", domainException.getErrors().toString());
				}
			}

			return mapper.writeValueAsString(message);

		} catch (JsonProcessingException jsonException) {
			return String.format("%s - %s", query, jsonException);
		}
	}

	@Override
	public String gerarGUID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obterGUID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
