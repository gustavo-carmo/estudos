package bridge

import bridge.exception.AppBusinessException
import bridge.exception.AppException

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
/*
        "/api/initialization"(controller: "misc", action: "initialization")
        "/api/resetPassword"(controller: "misc", action: "resetPassword")
        "/api/changePasswordByToken"(controller: "misc", action: "changePasswordByToken")
*/

        "/f/businessAreas"(controller: "api", action: "businessLines")
        "/f/businessAreas/$businessLineId/systems"(controller: "api", action: "contracts")
        "/f/businessAreas/$businessLineId/systems/$contractId/current"(controller: "api", action: "contractCurrent")
        "/f/businessAreas/$businessLineId/systems/$contractId/$contractDeployId/modules"(controller: "api", action: "contractModules")
        "/f/businessAreas/$businessLineId/systems/$contractId/$contractDeployId/modules/menu/items/load"(controller: "api", action: "contractMenu")
        "/f/businessAreas/$businessLineId/systems/$contractId/$contractDeployId/modules/$moduleId"(controller: "api", action: "contractModule")
        "/f/businessAreas/$businessLineId/systems/$contractId/$contractDeployId/modules/$moduleId/rows" {
            controller = "api"
            action = [
                    GET: "contractModuleDataRows",
                    POST: "contractModuleDataRowsSave"
            ]
        }
        "/f/businessAreas/$businessLineId/systems/$contractId/$contractDeployId/modules/$moduleId/rows/$moduleDataRowId" {
            controller = "api"
            action = [
                    GET: "contractModuleDataRow",
                    PUT: "contractModuleDataRowsUpdate"
            ]
        }
        "/f/businessAreaLogo/$id"(controller: "api", action: "businessLineLogo")
        "/f/systemLogo/$id"(controller: "api", action: "contractLogo")
        "/f/systemBackground/$id"(controller: "api", action: "contractAreaBg")

        // Default mapping
        "/api/$controller" {
            action = [GET: "index", POST: "save"]
            format = 'json'
        }
        "/api/$controller/$id" {
            action = [GET: "show", PUT: "update", DELETE: "delete"]
            format = 'json'
        }

        "/api/contract/deploy/$id"(controller: "contract", action: "deploy")

        // "/api/businessLine/updateLogo"(controller: "businessLine", action: "updateLogo")

        "/api/form/updateFieldDesc/$id"(controller: "form", action: "updateFieldDesc")
        "/api/image/$instanceType/$imageType/$id/$cache"(controller: "upload", action: "image")

        // Users
        "/api/users/$id/roles" {
            controller = "users"
            action = [GET: "rolesList", PUT: "rolesUpdate"]
            format = 'json'
        }

//       AutoComplete
        /*
        "/api/autoComplete/models"(controller: "autoComplete", action: "models")
        "/api/autoComplete/defects"(controller: "autoComplete", action: "defects")
        "/api/autoComplete/serviceOffereds"(controller: "autoComplete", action: "serviceOffereds")
        "/api/autoComplete/tenants"(controller: "autoComplete", action: "tenants")
        "/api/autoComplete/contractors"(controller: "autoComplete", action: "contractors")
        "/api/autoComplete/cities"(controller: "autoComplete", action: "cities")
*/
        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
        "500" (controller: 'error', action: 'handleAppException', exception: AppException)
        "500" (controller: 'error', action: 'handleAppBusinessException', exception: AppBusinessException)

        /*"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }*/
    }
}