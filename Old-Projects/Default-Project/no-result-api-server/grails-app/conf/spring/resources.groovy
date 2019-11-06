import business.AuthService

beans = {
    userDetailsService(AuthService) {
        repositoryService = ref("repositoryService")
    }
}
