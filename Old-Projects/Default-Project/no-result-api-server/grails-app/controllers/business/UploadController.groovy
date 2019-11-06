package business

import grails.transaction.Transactional

class UploadController {

    static responseFormats = ['json', 'xml']

    @Transactional(readOnly = false)
    def save() {

        def file = request.getFile("file")

        if (params.instance == 'businessLine') {
            def businessLine = BusinessLine.get(params.id as String)
            businessLine.bgdata = file.getBytes()
            businessLine.background = file.getOriginalFilename()
            render "OK"
        } else if (params.instance == 'contract') {
            def contract = Contract.get(params.id as String)
            if (params.type == 'background') {
                contract.bgdata = file.getBytes()
                contract.background = file.getOriginalFilename()
            } else {
                contract.logodata = file.getBytes()
                contract.logo = file.getOriginalFilename()
            }
            contract.save(failOnError: true, flush: true)
            render "OK"
        }
    }

    // TODO - quando não tem um background, dá erro
    def image(String id, String instanceType, String imageType) {

        def img

        if (instanceType == 'businessLine') {
            def businessLine = BusinessLine.get(id)
            img = businessLine.bgdata
        } else if (instanceType == 'contract') {
            def contract = Contract.get(params.id as String)
            if (imageType == 'background') {
                img = contract.bgdata
            } else {
                img = contract.logodata
            }
        }

        response.setContentType("Content-type: image")
        response.setContentLength(img.size())
        OutputStream out = response.getOutputStream();
        out.write(img);
        out.close();
    }
}
