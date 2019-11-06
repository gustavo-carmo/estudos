package pa

class BootStrap {

    def bootStrapService

    def init = { servletContext ->
        bootStrapService.init()
    }

    def destroy = {
    }
}