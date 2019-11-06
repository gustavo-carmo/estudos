package br.com.workfinity.web.page.helper;

public enum ServiceOrderGeneric{
	NOVA("STATUS_NOVA_GENERICO"), 
	ENCAMINHADA("STATUS_ENCAMINHADA_GENERICO"), 
	FINALIZADA("STATUS_FINALIZADA_GENERICO"), 
	WORKFLOW("WORKFLOW_GENERICO"), 
	CONTRACTOR("CONTRATANTE_GENERICO"), 
	CUSTOMER("CLIENTE_GENERICO"), 
	SERVICE_GROUP("GRUPO_DE_SERVICO_GENERICO"), 
	OPENING_HOURS_GROUP("OPENING_HOURS_GENERICO"), 
	SERVICE("SERVICO_GENERICO"), 
	DOCUMENT_CUSTOMER("41.764.422/0001-10");

	private String label;

	ServiceOrderGeneric(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
