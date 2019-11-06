package br.com.workfinity.web.page.helper;

public enum Messages {

	MANAGEMENT("Gerenciar"), 
	OPENING_HOURS("Horário de Funcionamento"), 
	CREATED("criado"), 
	OPENING_HOURS_GROUPS("Grupos de Horários de Funcionamento"), 
	CONTRACTOR("Contratante"), 
	CUSTOMER("Cliente"), 
	SERVICE_PROVIDER("Prestador de Serviço"), 
	MANUFACTURER("Fabricante"), 
	FAMILY("Família"), 
	MODEL("Modelo"), 
	SOLUTION("Solução"), 
	DEFECT("Defeito"), 
	REASON_FOR_CANCELLATION("Motivo de Cancelamento"), 
	SERVICE_GROUP("Grupo de Serviço"), 
	STATUS("Status"), 
	WORKFLOW("Workflow"), 
	RECESS("Feriados"), 
	SERVICE_LEVEL_AGREEMENT_GROUP("Grupo de SLA"), 
	SERVICE_ORDER("Ordem de Serviço"), 
	STOCK_CONTROL("Controle de Estoque"), 
	PRODUCTION_PLANNING("Planejamento da Produção"), 
	REPAIR_ORDER("Ordem de Reparo"), 
	LOGISTIC("Logística"), 
	SHIPMENT_ORDER("Ordem de Remessa"), 
	BANRISUL("Banrisul"), 
	IMPORT_SOLICITATIONS_E_INSTALLATIONS("Importar Solicitações e Instalações"), 
	IMPORT_PROCESS("Processos de Importação"), 
	EXPORT("Exportar"), 
	UPDATED("atualizado"), 
	SAVED("salvo"), 
	DELETED("excluído"), 
	DISABLE("Desabilitado"), 
	ENABLED("Habilitado"), 
	ALL("Todos"), 
	LABORATORY("Laboratório"), 
	START("Início"), 
	END_WITH_SUCCESS("Finalizado com sucesso"), 
	END_WITH_FAIL("Fim sem Sucesso"), 
	FIELD("Campo"), 
	TYPE_PRODUCT("Produto"), 
	TEMPLATE("Template"), 
	GENERIC_CONTRACT("Contrato Genérico"), 
	TEXT("TEXTO"), 
	CONSECUTIVE("Consecutivos"), 
	HOURS("Horas"), 
	USEFUL("Úteis"), 
	COUNTRY_TOWN("Cidade do Interior"), 
	DISTANCE_FROM_CAPITAL("Distância da Capital"), 
	UNTIL("Até"), 
	GREATER_THAN("Maior que"), 
	LESS_THAN("Menos que"), 
	MINUTES("Minutos"), 
	STATE_AND_OR_CITY("Estado e Cidade"), 
	NORTH("Norte"), 
	REGION("Região"), 
	SOUTH("Sul"), 
	CENTRAL("Central"), 
	NOTIFICATION("Notificação"),
	INFO("Informação"),
	PERMANENT("Permanente"),
	PERIOD("Período"),
	ONCE("Uma vez"),
	WARNING("Aviso"),
	IMPORTANT("Importante"),
	SUCCESS("Sucesso"),
	SCHEDULED_GRID("Grade de agendamento"),
	CONTRACT_RENTAL("Contrato de locação"),
	REPORT("Relatório"),
	RENTAL_REPORT("Relatório de locação"),
	WORKPACK("WorkPack"),
	SHIPMENT_ORDER_TYPE("Tipo de Remessa"),
	SERVICE_AREA("Áreas de Atendimento"),
	PROFILE_SETTINGS("Configurações por Perfil"),
	WARRANTY_PROVIDER("Fornecedor de Garantia"),
	
	/*TEXTOS RELACIONADOS A DADOS NO SISTEMA*/
	SERVICE_GROUP_GENERIC("GRUPO_DE_SERVICO_GENERICO"), 
	WORKFLOW_GENERIC("WORKFLOW_GENERICO"), 
	OPENING_HOURS_GROUP_GENERIC("MON-FRI 08h/18h"), 
	STATUS_SERVICE_ORDER_START("STATUS_NOVA_GENERICO"), 
	STATUS_SERVICE_ORDER_ENCAMINHADA("STATUS_ENCAMINHADA_GENERICO"),
	STATUS_SERVICE_ORDER_FINALIZADA("STATUS_FINALIZADA_GENERICO"),
	SERVICE_PROVIDER_GENERIC("PRESTADOR DE SERVIÇO GENERICO"), 
	MANUFACTURER_GENERIC("FABRICANTE GENERICO"), 
	FAMILY_GENERIC("FAMILIA GENERICA"), 
	CONTRACTOR_GENERIC("CONTRATANTE GENERICO"), 
	CUSTOMER_GENERIC("CLIENTE GENERICO"), 
	DOCUMENT_CUSTOMER_GENERIC("65.497.388/0001-23");

	private String label;

	Messages(String label) {
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