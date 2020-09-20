/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.easy.quality.inspecao.domain.exception.CodeDomainMessage;
import br.com.easy.quality.inspecao.domain.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Inspecao {

	public enum Status {
		ATIVA_COM_OCORRENCIA, ATIVA_SEM_OCORRENCIA, INATIVA_COM_OCORRENCIA, INATIVA_SEM_OCORRENCIA,
		EXPIRADA_SEM_OCORRENCIA, EXPIRADA_COM_OCORRENCIA
	}

	private String titulo;

	private boolean status;

	private String nomeColaboradorEntrevistador;

	private String nomeColaboradorEntrevistado;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataEdicao;

	private LocalDateTime dataDeExpiracao;

	private LocalDateTime dataDeUsoQuestionarioModelo;

	private Questionario questionario;

	public void criarInspecao() throws DomainException {

		if (dataDeExpiracao.isBefore(LocalDateTime.now())) {
			throw new DomainException(CodeDomainMessage.ERROR_INSPECAO_INVALIDA);
		}
		var dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dataCriacao = LocalDateTime.parse(dataCriacao);

	}

	public void auditarInspecao() throws DomainException {

		if (nomeColaboradorEntrevistado.equalsIgnoreCase(nomeColaboradorEntrevistador)) {
			throw new DomainException(CodeDomainMessage.ERROR_INSPECAO_INVALIDA);
		}
		var dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dataCriacao = LocalDateTime.parse(dataCriacao);

	}

	public void editarInspecao() throws DomainException {
		if (!status) {
			throw new DomainException(CodeDomainMessage.ERROR_INSPECAO_INVALIDA);
		}
		var dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.dataCriacao = LocalDateTime.parse(dataCriacao);
	}

	public Status obterStatusInspecao() throws DomainException {

		Status status = Status.ATIVA_SEM_OCORRENCIA;

		long respostaNao = questionario.getPerguntas().stream()
				.filter(respostas -> respostas.getOpcaoResposta().equals("NAO")).count();

		if (dataDeExpiracao.isAfter(LocalDateTime.now())) {
			if (respostaNao > Constants.PERCENTUAL_INSPECAO) {
				status = Status.EXPIRADA_COM_OCORRENCIA;
			} else {
				status = Status.EXPIRADA_SEM_OCORRENCIA;
			}
		}

		if (this.status && respostaNao > Constants.PERCENTUAL_INSPECAO) {
			status = Status.ATIVA_COM_OCORRENCIA;
		} else if (this.status && respostaNao < Constants.PERCENTUAL_INSPECAO) {
			status = Status.ATIVA_SEM_OCORRENCIA;
		} else if (!this.status && respostaNao > Constants.PERCENTUAL_INSPECAO) {
			status = Status.INATIVA_COM_OCORRENCIA;
		} else if (!this.status && respostaNao < Constants.PERCENTUAL_INSPECAO) {
			status = Status.INATIVA_SEM_OCORRENCIA;
		}
		return status;

	}

	@Override
	public String toString() {
		return "Inspecao [titulo=" + titulo + ", status=" + status + ", nomeColaboradorEntrevistador="
				+ nomeColaboradorEntrevistador + ", nomeColaboradorEntrevistado=" + nomeColaboradorEntrevistado
				+ ", dataCriacao=" + dataCriacao + ", dataEdicao=" + dataEdicao + ", dataDeExpiracao=" + dataDeExpiracao
				+ ", dataDeUsoQuestionarioModelo=" + dataDeUsoQuestionarioModelo + ", questionario=" + questionario
				+ "]";
	}
	
	
	

}
