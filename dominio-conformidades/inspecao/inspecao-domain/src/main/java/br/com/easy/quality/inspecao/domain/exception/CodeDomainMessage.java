package br.com.easy.quality.inspecao.domain.exception;

/**
 * 
 * @author rianmachado@gmail.com
 *
 */
public enum CodeDomainMessage implements CodeMsg {

	INFO_INSPECAO_NAO_LOCALIZADA("MSG_INSPECAO_NAO_LOCALIZADA", 404),
	INFO_LIST_INSPECAO_VAZIA("MSG_INSPECAO_LISTA_VAZIA", 204),
	ERROR_INSPECAO_READ_DATA("MSG_ERRO_NA_LEITURA", 500),
	ERROR_INSPECAO_PERGUNTAS_INVALIDAS("MSG_ERRO_PRE_CONDICAO_inspecao", 412),
	ERROR_INSPECAO_INVALIDO("MSG_ERRO_PRE_CONDICAO_inspecao", 412),
	ERROR_EDICAO_INSPECAO("MSG_ERRO_PRE_CONDICAO_EDICAO_inspecao", 412);

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