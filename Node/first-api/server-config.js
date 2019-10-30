module.exports = {
    serverPort: 3001,
    mongodbUrl: "mongodb://localhost:27017/nodeapi",
    errorResponseNotFound: {
        meta: {
            code: "400"
        },
        data: {
            messageCode: "route.not.found",
            message: "A rota solicitada não foi encontrada"
        }
    }
}