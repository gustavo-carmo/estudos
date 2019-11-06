package bridge

class UrlMappings {

    static mappings = {

        /*
        "/api/st/index1"(controller: "securityTest", action: "index1") // no ROLE
        "/api/st/index2"(controller: "securityTest", action: "index2") // permitAll
        "/api/st/index3"(controller: "securityTest", action: "index3") // ROLE_USER

        "/api/st-index1"(controller: "securityTest", action: "index1") // no ROLE
        "/api/st-index2"(controller: "securityTest", action: "index2") // permitAll
        "/api/st-index3"(controller: "securityTest", action: "index3") // ROLE_USER
        */

        // MISC
        "/api/initialization"(controller: "misc", action: "initialization")
        "/api/resetPassword"(controller: "misc", action: "resetPassword")
        "/api/changePasswordByToken"(controller: "misc", action: "changePasswordByToken")

        // Default mapping
        "/api/$controller" {
            action = [GET: "index", POST: "save"]
            format = 'json'
        }
        "/api/$controller/$id" {
            action = [GET: "show", PUT: "update", DELETE: "delete"]
            format = 'json'
        }

        // Users
        "/api/users/$id/roles" {
            controller = "users"
            action = [GET: "rolesList", PUT: "rolesUpdate"]
            format = 'json'
        }

//       AutoComplete
        "/api/autoComplete/models"(controller: "autoComplete", action: "models")
        "/api/autoComplete/defects"(controller: "autoComplete", action: "defects")
        "/api/autoComplete/serviceOffereds"(controller: "autoComplete", action: "serviceOffereds")
        "/api/autoComplete/tenants"(controller: "autoComplete", action: "tenants")
        "/api/autoComplete/contractors"(controller: "autoComplete", action: "contractors")
        "/api/autoComplete/cities"(controller: "autoComplete", action: "cities")

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        /*"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }*/
    }
}