package business.enums

//TODO - TESTES
enum DocumentType {

    CPF, CNPJ

    static marshalObject(object) {

        if (object) {
            DocumentType documentTypeInstance = (DocumentType) object;

            return documentTypeInstance.toString()
        }

        return null
    }
}