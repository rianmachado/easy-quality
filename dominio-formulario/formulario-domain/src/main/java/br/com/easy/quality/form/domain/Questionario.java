/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.easy.quality.form.domain.exception.CodeDomainMessage;
import br.com.easy.quality.form.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Questionario {

	private String titulo;

	private boolean status;

	private List<Pergunta> perguntas;

	private LocalDate dataCriacao;

	private LocalDate dataEdicao;

	public Questionario() {
	}

	public Questionario(String titulo, Boolean status) {
		this.status = status;
		this.titulo = titulo;
	}

	public void criarPerguntas(List<Pergunta> perguntas) throws DomainException {
		if (this.perguntas == null) {
			this.perguntas = new ArrayList<Pergunta>();
		}
		
		for (Pergunta p : perguntas) {
			if (p.isDescricaoValida() && !perguntas.contains(p)) {
				throw new DomainException(CodeDomainMessage.ERROR_QUESTIONARIO_PERGUNTAS_INVALIDAS);
			}
			this.perguntas.add(p);
		}
	}

	public void isQuestionarioValido() throws DomainException {
		if (perguntas == null || perguntas.isEmpty() || titulo.isEmpty()) {
			throw new DomainException(CodeDomainMessage.ERROR_QUESTIONARIO_INVALIDO);
		}
		if (!titulo.matches("^[A-Za-z\s?]{10,100}$")) {
			throw new DomainException(CodeDomainMessage.ERROR_QUESTIONARIO_INVALIDO);
		}
	}

	public void criarQuestionario() {
		var dataCriacao = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dataCriacao = LocalDate.parse(dataCriacao);
	}

	public void editarQuestionario() {
		if (!status) {
			throw new DomainException(CodeDomainMessage.ERROR_EDICAO_QUESTIONARIO);
		}
		var dataCriacao = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dataCriacao = LocalDate.parse(dataCriacao);
	}

}
