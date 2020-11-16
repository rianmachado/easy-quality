package br.com.easy.quality.questionario.domain.exception;

/**
 * 
 * @author rianmachado@gmail.com
 *
 */
public enum CodeDomainMessage implements CodeMsg {

	INFO_QUESTIONARIO_NAO_LOCALIZADO("MSG_QUESTIONARIO_NAO_LOCALIZADO", 404),
	INFO_LIST_QUESTIONARIO_VAZIO("MSG_QUESTIONARIO_LISTA_VAZIA", 204),
	ERROR_QUESTIONARIO_READ_DATA("MSG_ERRO_NA_LEITURA", 500),
	ERROR_QUESTIONARIO_PERGUNTAS_INVALIDAS("MSG_ERRO_PRE_CONDICAO_QUESTIONARIO", 412),
	ERROR_QUESTIONARIO_INVALIDO("MSG_ERRO_PRE_CONDICAO_QUESTIONARIO", 412),
	ERROR_EDICAO_QUESTIONARIO("MSG_ERRO_PRE_CONDICAO_EDICAO_QUESTIONARIO", 412);

	private String idBundle;
	private int codeError;

	private CodeDomainMessage(String idBundle, int codeError) {
		this.idBundle = idBundle;
		this.codeError = codeError;
	}

	@Override
	public String getIdBundle() {
		return idBundle;
	}

	@Override
	public int getCodeError() {
		return codeError;
	}

}