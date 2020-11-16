package br.com.easy.quality.questionario.write.exception;

/**
 * 
 * @author rianmachado@gmail.com
 *
 */
public enum CodeBusinessMessage implements ICodeBusinessMessage {

	ERROR_QUESTIONARIO_INCOMPLETO("MSG_ERRO_QUESTIONAR_INCOMPLETO", 412);

	private String idBundle;
	private int codeError;

	private CodeBusinessMessage(String idBundle, int codeError) {
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