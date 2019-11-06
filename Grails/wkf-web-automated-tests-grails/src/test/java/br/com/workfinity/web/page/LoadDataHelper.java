package br.com.workfinity.web.page;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import careman.util.FileLoader;

public class LoadDataHelper {

	// SCRIPTS CONTRACTOR
	public static void createContractor(WebDriver driver, String baseUrl, String contractorName,
			String documentNumber) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("contractorCreate"),
				Arrays.asList("%name%",
						"%documentNumber%"),
				
				Arrays.asList(contractorName,
						StringUtils.removeSpecialChar(documentNumber)));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteContractor(WebDriver driver, String baseUrl, String documentNumber) {

		String command = StringUtils.replace(
				FileLoader.readFile("contractorDelete"),
				"%documentNumber%",
				StringUtils.removeSpecialChar(documentNumber));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS FAMILY
	public static void createFamily(WebDriver driver, String baseUrl, String name) {

		String command = StringUtils.replace(
				FileLoader.readFile("familyCreate"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteFamily(WebDriver driver, String baseUrl, String name) {

		String command = StringUtils.replace(
				FileLoader.readFile("familyDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS SOLUTION
	public static void createSolution(WebDriver driver, String baseUrl, String name, boolean changeEquipment) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("solutionCreate"),
				Arrays.asList("%name%",
						"%changeEquipment%"),
				
				Arrays.asList(name,
						String.valueOf(changeEquipment)));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteSolution(WebDriver driver, String baseUrl, String name) {

		String command = StringUtils.replace(
				FileLoader.readFile("solutionDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS MANUFACTURER
	public static void createManufacturer(WebDriver driver, String baseUrl, String name) {

		String command = StringUtils.replace(
				FileLoader.readFile("manufacturerCreate"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteManufacturer(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("manufacturerDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS MODEL
	public static void createModel(WebDriver driver, String baseUrl, String manufacturer, String family,
			String name, String type) {
		
		createModel(driver, 
				baseUrl, 
				manufacturer, 
				family,
				name,
				type,
				"",
				null,
				false);
	}

	public static void createModel(WebDriver driver, String baseUrl, String manufacturer, String family, String name,
			String type,String equipmentType, List<String> modelsToAddInModel, boolean consumable) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("modelCreate"),
				Arrays.asList("%name%",
						"%manufacturer%",
						"%family%",
						"%type%",
						"%equipmentType%",
						"%consumable%"),
				
				Arrays.asList(name,
						manufacturer,
						family,
						type.toUpperCase(),
						equipmentType,
						consumable + ""));

		if (modelsToAddInModel == null) {
			
			command = StringUtils.replace(command, "%models%", "");
		} else {
			
			String models = "\"" + StringUtils.myJoin("\",\"", modelsToAddInModel);
			models = models.substring(0, models.length() - 2);
			command = StringUtils.replaceAll(command, Arrays.asList("%models%"), Arrays.asList(models));
		}

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteModel(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("modelDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS SERVICE_PROVIDER
	public static void createServiceProvider(WebDriver driver, String baseUrl, String name, String alias,
			String documentType, String documentNumber, String contactName, String email, String phoneNumber,
			String address, String addressNumber, String zipCode, String city, String state) {

		createServiceProvider(driver,
				baseUrl,
				name,
				alias,
				documentType,
				documentNumber,
				contactName,
				email,
				phoneNumber,
				address,
				addressNumber,
				zipCode,
				city,
				state,
				false);
	}

	public static void createServiceProvider(WebDriver driver, String baseUrl, String name, String alias,
			String documentType, String documentNumber, String contactName, String email, String phoneNumber,
			String address, String addressNumber, String zipCode, String city, String state,
			boolean technicianStockControlField) {

		createServiceProvider(driver,
				baseUrl, 
				contactName,
				alias, 
				documentType,
				documentNumber,
				contactName, 
				email,
				phoneNumber, 
				address, 
				addressNumber, 
				zipCode,
				city, 
				state, 
				"FIELD",
				technicianStockControlField,
				true,
				"");
	}

	public static void createServiceProvider(WebDriver driver, String baseUrl, String name, String alias,
			String documentType, String documentNumber, String contactName, String email, String phoneNumber,
			String address, String addressNumber, String zipCode, String city, String state, String serviceProviderType,
			boolean technicianStockControlField, boolean hq, String serviceProviderHeadquartersName) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("serviceProviderCreate"),
				Arrays.asList("%name%", 
						"%alias%", 
						"%documentType%",
						"%documentNumber%", 
						"%contactName%",
						"%email%",
						"%phoneNumber%",
						"%address%",
						"%addressNumber%", 
						"%zipCode%",
						"%city%",
						"%state%",
						"%serviceProviderType%",
						"%technicianStockControlField%",
						"%hq%", 
						"%serviceProviderHeadquartersName%"),
				
				Arrays.asList(name, 
						alias,
						documentType, 
						StringUtils.removeSpecialChar(documentNumber),
						contactName,
						email,
						phoneNumber, 
						address,
						addressNumber,
						zipCode,
						city,
						state,
						serviceProviderType.toUpperCase(),
						String.valueOf(technicianStockControlField),
						String.valueOf(hq),
						serviceProviderHeadquartersName));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void createServiceProviderContractorAndServiceGroup(WebDriver driver, String baseUrl,
			String serviceProvider, String contractor, String serviceGroup, String serviceAreas) {

		String command = StringUtils.replace(
				FileLoader.readFile("serviceProviderAddContractorAndServiceGroup"),
				"%serviceProvider%",
				serviceProvider);

		if (contractor != "") {
			
			command = StringUtils.replace(command, "%contractor%", contractor);
		} else {
			
			command = StringUtils.replace(command,
					"serviceProvider.addToContractors(Contractor.findByAlias(\"%contractor%\"))", "");
		}

		if (serviceGroup != "") {
			
			command = StringUtils.replace(command, "%serviceGroup%", serviceGroup);
		} else {
			
			command = StringUtils.replace(command,
					"serviceProvider.addToServiceGroups(ServiceGroup.findByName(\"%serviceGroup%\"))", "");
		}
		
		if (serviceAreas != "") {
			
			command = StringUtils.replace(command, "%serviceAreas%", serviceAreas);
		} else {
			
			command = StringUtils.replace(command,
					"serviceProvider.addToServiceAreas(ServiceAreas.findByName(\"%serviceAreas%\"))", "");
		}

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteServiceProvider(WebDriver driver, String baseUrl, String alias) {

		String command = StringUtils.replace(
				FileLoader.readFile("serviceProviderDelete"), 
				"%alias%",
				alias);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS OPENING_HOURS
	public static void createOpeningHours(WebDriver driver, String baseUrl, String day, int startAt, int endAt) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("openingHoursCreate"),
				Arrays.asList("%day%",
						"%startAt%",
						"%endAt%"),
				
				Arrays.asList(day.toUpperCase(), 
						String.valueOf(startAt), 
						String.valueOf(endAt)));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteOpeningHours(WebDriver driver, String baseUrl, String day, int startAt, int endAt) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("openingHoursDelete"),
				Arrays.asList("%day%", 
						"%startAt%",
						"%endAt%"),
				
				Arrays.asList(day.toUpperCase(), 
						String.valueOf(startAt), 
						String.valueOf(endAt)));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS STATUS
	public static void createStatus(WebDriver driver, String baseUrl, String name, String type, String workflowType) {
		
		String command = "";
		
		if (type == null) {
			
			command = StringUtils.replaceAll(
					FileLoader.readFile("workFlowStatusCreate"),
					Arrays.asList("%name%",
							"StepStatusType.%type%",
							"%workflowType%"),
					
					Arrays.asList(name,
							"null", 
							workflowType.replace(" ", "_").toUpperCase()));
		} else {
			
			command = StringUtils.replaceAll(
					FileLoader.readFile("workFlowStatusCreate"),
					Arrays.asList("%name%",
							"%type%", 
							"%workflowType%"), 
					
					Arrays.asList(name,
							type.replace(" ", "_").toUpperCase(),
							workflowType.replace(" ", "_").toUpperCase()));
		}
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void createStatus(WebDriver driver, String baseUrl, String name, String type) {
		
		createStatus(driver, baseUrl, name, type, "SERVICE_ORDER");
	}

	public static void deleteStatus(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("workFlowStatusDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS EQUIPMENT
	public static void createEquipment(WebDriver driver, String baseUrl, String contractorAlias, String customerDocumentType,
			String customerDocumentNumber, String serviceProviderName, String manufacturerName, String modelName,
			String situation, String serialNumber, Integer quantity, Boolean inventoried) {
		
		createEquipment(driver,
				baseUrl, 
				contractorAlias,
				customerDocumentType, 
				customerDocumentNumber,
				serviceProviderName,
				manufacturerName, 
				modelName,
				situation, 
				serialNumber,
				quantity, 				
				inventoried,
				"",
				"",
				"", 
				"");
	}
	
	public static void createEquipment(WebDriver driver, String baseUrl, String contractorAlias, String customerDocumentType,
			String customerDocumentNumber, String serviceProviderName, String manufacturerName, String modelName,
			String situation, String serialNumber, Integer quantity,Boolean inventoried, String po, String si) {
		
		createEquipment(driver,
				baseUrl, 
				contractorAlias,
				customerDocumentType, 
				customerDocumentNumber,
				serviceProviderName,
				manufacturerName, 
				modelName,
				situation, 
				serialNumber,
				quantity,				
				inventoried,
				po,
				si,
				"", 
				"");
	}
	
	public static void createEquipment(WebDriver driver, String baseUrl, String contractorAlias, String customerDocumentType,
			String customerDocumentNumber, String serviceProviderName, String manufacturerName, String modelName,
			String situation, String serialNumber, Integer quantity, Boolean inventoried, String po, String si, 
			String typeOfEquipmentAvailability, String equipmentCondition) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("equipmentCreate"),
				Arrays.asList("%contractorAlias%",
						"%documentType%",
						"%documentNumber%", 
						"%serviceProviderName%",
						"%manufacturerName%",
						"%modelName%", 
						"%situation%",
						"%serialNumber%",
						"%typeOfEquipmentAvailability%",
						"%equipmentCondition%",
						"%quantity%",						
						"%inventoried%",
						"%po%",
						"%si%"),
				
				Arrays.asList(contractorAlias,
						customerDocumentType,
						StringUtils.removeSpecialChar(customerDocumentNumber),
						serviceProviderName, 
						manufacturerName,
						modelName,
						situation, 
						serialNumber,
						typeOfEquipmentAvailability,
						equipmentCondition,						
						quantity != null ? String.valueOf(quantity) : "null",						
						inventoried != null ? String.valueOf(inventoried) : "null",
						po,
						si));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteEquipment(WebDriver driver, String baseUrl, String serialNumber) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("equipmentDelete"),
				"%serialNumber%",
				serialNumber);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS SERVICE_GROUP
	public static void createServiceGroup(WebDriver driver, String baseUrl, String name) {
		
		createServiceGroup(driver,
				baseUrl, 
				name,
				"FIELD");
	}

	public static void createServiceGroup(WebDriver driver, String baseUrl, String name, String type) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("serviceGroupCreate"),
				Arrays.asList("%name%",
						"%type%"), 
				
				Arrays.asList(name,
						type));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteServiceGroup(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("serviceGroupDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS OPENING_HOURS_GROUP
	public static void createOpeningHoursGroup(WebDriver driver, String baseUrl, String name, int startAt, int endAt) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("openingHoursGroupCreate"),
				Arrays.asList("%name%",
						"%startAt%",
						"%endAt%"),
				
				Arrays.asList(name, 
						String.valueOf(startAt),
						String.valueOf(endAt)));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void deleteOpeningHoursGroup(WebDriver driver, String baseUrl, String name) {
	
		String command = StringUtils.replace(
				FileLoader.readFile("openingHoursGroupDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS WORKFLOW
	public static void createWorkflow(WebDriver driver, String baseUrl, String name, String workflowType) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("workflowCreate"),
				Arrays.asList("%name%",
						"%workflowType%"),
				
				Arrays.asList(name, 
						workflowType.replace(" ", "_").toUpperCase()));

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void createWorkflow(WebDriver driver, String baseUrl, String name) {
		
		createWorkflow(driver, 
				baseUrl,
				name,
				"SERVICE_ORDER");
	}

	public static void deleteWorkFlow(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("workflowDelete"),
				"%name%", 
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS WORKPACK
	public static void workPackUpdate(WebDriver driver, String baseUrl, String id) {

		String command = StringUtils.replace(
				FileLoader.readFile("workPackUpdate")
				, "%id%", 
				id);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// CREATE USER
	public static void createUser(WebDriver driver, String baseUrl, String username, String password,
			List<String> roles, String language) {

		String command = "import br.com.careman.domain.*\nimport br.com.careman.groovy.enums.*\n\ndef username = \"--username--\""
				+ "\ndef password = \"--password--\"\nList roles = --roles--\n\ndef name = username\ndef email = username + \"@email.com\""
				+ "\ndef language = Language.findByLocale(\"--language--\")\ndef profile = Profile.ADMINISTRATOR\n"
				+ "\ndef userInstance = new User(\nusername: username,\nenabled: true,\npassword: password,\nname: name,\nemail: email,"
				+ "\nlanguage: language,\nprofile: profile\n,passwordChangeRequest: false).save(flush: true)\n\nif(userInstance) {\nprintln userInstance\nroles.each {"
				+ "\ndef roleInstance = Role.findByAuthority(it)\nif(roleInstance) {"
				+ "\ndef userRoleInstance = UserRole.create(userInstance, roleInstance, true)\nif(userRoleInstance) {"
				+ "\nprintln userRoleInstance.user.username + \"-\" + userRoleInstance.role\n}\n}\n}\n} else {\nprintln \"user not created\"\n}";
		command = command.replace("--username--", username);
		command = command.replace("--password--", password);
		command = command.replace("--language--", language);

		StringBuilder sb = new StringBuilder("[");
		String separator = "";
		
		for (String role : roles) {
			sb.append(separator);
			separator = ",";
			sb.append("'");
			sb.append(role);
			sb.append("'");
		}
		
		sb.append("]");

		command = command.replace("--roles--", sb.toString());

		UtilConsolePage.execute(driver, baseUrl, command);

		Iterator<String> iterator = roles.iterator();
		int count = 0;
		
		while (iterator.hasNext()) {
			Assert.assertEquals(driver.findElement(By.id(String.valueOf(count + 2))).getText(),
					username + "-" + iterator.next());
			count++;
		}
	}

	public static void createUserNoPermission(WebDriver driver, String baseUrl, String username, String password,
			String language) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("userNoPermissionCreate"),
				Arrays.asList("%username%",
						"%password%",
						"%language%"), 
				Arrays.asList(username,
						password, 
						language));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS TEMPLATE
	public static void deleteTemplate(WebDriver driver, String baseUrl, String name) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("templateDelete"),
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void createTemplate(WebDriver driver, String baseUrl, String status, String name, String type) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("templateCreate"),
				Arrays.asList("%status%",
						"%name%",
						"%type%"),
				
				Arrays.asList(status.toUpperCase(), 
						name, 
						type.toUpperCase()));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS CONTRACT
	public static void deleteContract(WebDriver driver, String baseUrl, String idContract) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("contractDelete"),
				"%id%", 
				idContract);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS ADD STATUS TO EXISTENT PROFILE SETTINGS
	public static void addStatusToExistentProfile(WebDriver driver, String baseUrl, String profile, String workflowType,
			List<String> status) {
		
		String command = StringUtils.replace(
				FileLoader.readFile("addStatusToProfile"),
				"%profile%",
				
				profile.toUpperCase());
		
		String statusToAdd = "";
		
		for (int i = 0; i < status.size(); i++) {
			
			statusToAdd += "[edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType." + workflowType.toUpperCase() + ", \""
					+ status.get(i) + "\")]";
			
			if (i < status.size() - 1) {
				
				statusToAdd += ",\n";
			}
		}
		
		command = StringUtils.replace(command, "%status%", statusToAdd);

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS CUSTOMER
	public static void createCustomer(WebDriver driver, String baseUrl, String name, String documentNumber,
			String contractor) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("customerCreate"),
				Arrays.asList("%name%",
						"%documentNumber%",
						"%contractor%"),
				
				Arrays.asList(name, 
						StringUtils.removeSpecialChar(documentNumber),
						contractor));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS ADD STEP TO WORKFLOW
	public static void addStepToWorkflow(WebDriver driver, String baseUrl, String workflow, String from, String to) {
		
		addStepToWorkflow(driver,
				baseUrl,
				workflow, 
				from,
				to,
				null);
	}

	public static void addStepToWorkflow(WebDriver driver, String baseUrl, String workflow, String from, String to,
			List<String> identifiersRulesAndTriggers) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("stepWorkflowCreate"),
				Arrays.asList("%workflow%",
						"%from%", 
						"%to%"), 
				Arrays.asList(workflow,
						from,
						to));
		
		UtilConsolePage.execute(driver, baseUrl, command);

		if (identifiersRulesAndTriggers != null) {
			
			addRuleAndTriggersToStepWorkflow(driver, 
					baseUrl, 
					workflow,
					from,
					to,
					identifiersRulesAndTriggers);
		}
	}

	public static void addRuleAndTriggersToStepWorkflow(WebDriver driver, String baseUrl, String workflow, String from,
			String to, List<String> identifiers) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("addRulesAndTriggersToStepWorkflow"),
				Arrays.asList("%workflow%",
						"%from%",
						"%to%"), 
				Arrays.asList(workflow,
						from, 
						to));
		
		String identifiersToAdd = "";

		for (int i = 0; i < identifiers.size(); i++) {
			
			identifiersToAdd += identifiers.get(i);
			
			if (i < identifiers.size() - 1) {
				
				identifiersToAdd += "\", \n \"";
			}
		}

		command = StringUtils.replace(command, "%identifiers%", identifiersToAdd);

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS SERVICE CONTRACT
	public static void createServiceToContract(WebDriver driver, String baseUrl, String name, String contractor,
			String serviceGroup, String workflow, String openingHoursGroup) {
		
		createServiceToContract(driver,
				baseUrl,
				name,
				contractor,
				serviceGroup,
				workflow,
				openingHoursGroup,
				null,
				null);
	}

	public static void createServiceToContract(WebDriver driver, String baseUrl, String name, String contractor,
			String serviceGroup, String workflow, String openingHoursGroup, List<String> serviceAllows,
			List<String> models) {

		String command = FileLoader.readFile("serviceContractCreate");

		if (models == null) {
			
			command = StringUtils.replaceAll(command,
					Arrays.asList("%name%",
							"%contractor%", 
							"%serviceGroup%",
							"%workflow%", 
							"%oph_group%",
							"\"%models%\""),
					
					Arrays.asList(name,
							contractor,
							serviceGroup, 
							workflow,
							openingHoursGroup, 
							""));

		} else {
			
			String modelsRefactor = "\"" + StringUtils.myJoin("\",\"", models);
			modelsRefactor = modelsRefactor.substring(0, modelsRefactor.length() - 2);

			command = StringUtils.replaceAll(command,
					Arrays.asList("%name%", 
							"%contractor%", 
							"%serviceGroup%",
							"%workflow%",
							"%oph_group%",
							"\"%models%\""),
					
					Arrays.asList(name, 
							contractor, 
							serviceGroup, 
							workflow, 
							openingHoursGroup, 
							modelsRefactor));
		}

		if (serviceAllows != null) {
			
			command = StringUtils.replace(command, "/****/", StringUtils.myJoin("; ", serviceAllows));
		}

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	public static void addModelToContract(WebDriver driver, String baseUrl, String contractor, List<String> models) {
		
		String modelsToAdd = "\"" + StringUtils.myJoin("\",\"", models);
		modelsToAdd = modelsToAdd.substring(0, modelsToAdd.length() - 2);

		String command = StringUtils.replaceAll(
				FileLoader.readFile("addModelToContract"),
				Arrays.asList("%contractor%",
						"%models%"),
				
				Arrays.asList(contractor, 
						modelsToAdd));

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS TECHNICIAN
	public static void createTechnician(WebDriver driver, String baseUrl, String name, String password,
			List<String> rolesTechnician, String serviceProvider) {
		
		createTechnician(driver,
				baseUrl,
				name, 
				password, 
				rolesTechnician,
				serviceProvider,
				"SERVICE_PROVIDER_TECHNICIAN");
	}
	
	public static void createTechnician(WebDriver driver, String baseUrl, String name, String password,
			List<String> rolesTechnician, String serviceProvider, String technicianType) {
		
		String roles = "\"" + StringUtils.myJoin("\",\"", rolesTechnician);
		roles = roles.substring(0, roles.length() - 2);
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("technicianCreate"),
				Arrays.asList("%name%", 
						"%password%",
						"%serviceProvider%",
						"%roles%",
						"%technicianType%"),
				
				Arrays.asList(name, 
						password, 
						serviceProvider,
						roles,
						technicianType));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS LOCATION
	public static void createLocation(WebDriver driver, String baseUrl, String name, String serviceProvider) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("locationCreate"),
				Arrays.asList("%name%",
						"%serviceProvider%"),
				
				Arrays.asList(name,
						serviceProvider));

		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS SERVICE_ORDER
	public static void createServiceOrder(WebDriver driver, String baseUrl, String customerDocumentType,
			String customerDocumentNumber, String serviceName) {

		String command = StringUtils.replaceAll(
				FileLoader.readFile("serviceOrderCreate"),
				Arrays.asList("%documentType%", 
						"%documentNumber%",
						"%serviceName%"),
				
				Arrays.asList(customerDocumentType, 
						StringUtils.removeSpecialChar(customerDocumentNumber),
						serviceName));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS CARRIER (TRANSPORTADORA)
	public static void createCarrier(WebDriver driver, String baseUrl, String name, String alias){
		
		String command = StringUtils.replaceAll(FileLoader.readFile("carrierCreate"), 
				Arrays.asList("%name%",
						"%alias%"),
				
				Arrays.asList(name,
						alias));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS REPAIR LEVEL (NÍVEL DE REPARO)
	public static void createRepairLevel(WebDriver driver, String baseUrl, String name){
		
		String command = StringUtils.replace(FileLoader.readFile("repairLevelCreate"), 
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS REASON FOR CANCELLATION (MOTIVO DE CANCELAMENTO)
	public static void createReasonForCancellation(WebDriver driver, String baseUrl, String name){
		
		String command = StringUtils.replace(FileLoader.readFile("reasonForCancellationCreate"), 
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	
	// SCRIPTS Service Areas (Áreas de Atendimento)
	public static void createServiceAreas(WebDriver driver, String baseUrl, String name){
		
		String command = StringUtils.replace(FileLoader.readFile("serviceAreasCreate"), 
				"%name%",
				name);
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}

	// SCRIPTS DEFECT (DEFEITO)
	public static void createDefect(WebDriver driver, String baseUrl, String name, String family) {
		
		String command = StringUtils.replaceAll(FileLoader.readFile("defectCreate"),
				Arrays.asList("%name%",
						"%family%"),
				
				Arrays.asList(name,
						family));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS Tipo de Disponibilização de Equipamento
	public static void createTypeOfEquipmentAvailability(WebDriver driver, String baseUrl, String name, String code){

		String command = StringUtils.replaceAll(
				FileLoader.readFile("typeOfEquipmentAvailabilityCreate"),
				Arrays.asList("%name%",
						"%code%"),
				
				Arrays.asList(name, 
						code));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS Status do Equipamento
	public static void createEquipmentCondition(WebDriver driver, String baseUrl, String name, String situation, String code) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("equipmentConditionCreate"),
				Arrays.asList("%name%",
						"%situation%",
						"%code%"),
				
				Arrays.asList(name, 
						situation.toUpperCase(),
						code));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS Tipo de Equipamento
	public static void createEquipmentType(WebDriver driver, String baseUrl, String name, String family, String code) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("equipmentTypeCreate"),
				Arrays.asList("%name%",
						"%family%",
						"%code%"),
				
				Arrays.asList(name, 
						family,
						code));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS WARRANTY_PROVIDER (FORNECEDOR DE GARANTIAS)
	public static void createWarrantyProvider(WebDriver driver, String baseUrl, String name, String warrantyType, String days) {
		
		String command = StringUtils.replaceAll(FileLoader.readFile("warrantyProviderCreate"),
				Arrays.asList("%name%",
						"%warrantyType%",
						"%days%"),
				
				Arrays.asList(name,
						warrantyType.toUpperCase(),
						days));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
	
	// SCRIPTS SHIPMENT ORDER TYPE (TIPOS DE ORDEM DE REMESSA)
	public static void createShipmentOrderType(WebDriver driver, String baseUrl, String name, String workflow, String template) {
		
		createShipmentOrderType(driver,
				baseUrl,
				name, 
				workflow, 
				template,
				"",
				"",
				false,
				false,
				false,
				false,
				"");
		
	}
	
	// SCRIPTS SHIPMENT ORDER TYPE (TIPOS DE ORDEM DE REMESSA) Com mais parâmetros
	public static void createShipmentOrderType(WebDriver driver, String baseUrl, String name, String workflow, 
			String template, String typeOfEquipmentAvailabilitys, String equipmentConditions, 
			Boolean keepEquipmentsAtShipmentOrderFrom, Boolean allowedOnlyEquipmentInPromiseOfEquipmentType,
			Boolean promiseOfEquipmentTypeQuantityRestrict,
		    Boolean blockAddEquipmentUntilPromiseOfEquipmentNotConfig,
		    String promiseOfEquipmentType) {
		
		String command = StringUtils.replaceAll(
				FileLoader.readFile("shipmentOrderTypeCreate"),
				Arrays.asList("%name%",
						"%workflow%",
						"%template%",
						"%typeOfEquipmentAvailabilitys%",
						"%equipmentConditions%",
						"%keepEquipmentsAtShipmentOrderFrom%",
						"%allowedOnlyEquipmentInPromiseOfEquipmentType%",
						"%promiseOfEquipmentTypeQuantityRestrict%",
						"%blockAddEquipmentUntilPromiseOfEquipmentNotConfig%",
						"%promiseOfEquipmentType%"), 
				Arrays.asList(name,
						workflow,
						template,
						typeOfEquipmentAvailabilitys,
						equipmentConditions,						
						keepEquipmentsAtShipmentOrderFrom != null ? String.valueOf(keepEquipmentsAtShipmentOrderFrom) : "null",
						allowedOnlyEquipmentInPromiseOfEquipmentType != null ? String.valueOf(allowedOnlyEquipmentInPromiseOfEquipmentType) : "null",
						promiseOfEquipmentTypeQuantityRestrict != null ? String.valueOf(promiseOfEquipmentTypeQuantityRestrict) : "null",
						blockAddEquipmentUntilPromiseOfEquipmentNotConfig != null ? String.valueOf(blockAddEquipmentUntilPromiseOfEquipmentNotConfig) : "null",
						promiseOfEquipmentType));
		
		UtilConsolePage.execute(driver, baseUrl, command);
	}
}
