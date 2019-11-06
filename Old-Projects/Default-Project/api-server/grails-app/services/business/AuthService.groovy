package business

import grails.converters.JSON
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.transaction.Transactional
import org.grails.web.json.JSONElement
import org.springframework.dao.DataAccessException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import bridge.User

@Transactional(readOnly = true)
class AuthService implements GrailsUserDetailsService {

    def repositoryService

    @Override
    UserDetails loadUserByUsername(String credentials, boolean loadRoles)
            throws UsernameNotFoundException, DataAccessException {
        loadUserByUsername(credentials)
    }

    @Override
    UserDetails loadUserByUsername(String credentials) throws UsernameNotFoundException {

        if (!credentials) {
            def error = "Credentials is a required param"
            log.error error
            throw new UsernameNotFoundException(error)
        }

        JSONElement _credentials = JSON.parse(credentials)
        def username = _credentials.username

        if (!username) {
            def error = "Credentials malformed, required username"
            log.error error
            throw new UsernameNotFoundException(error)
        }

        User user
        def error = "User not found"

        if (!user) {
            log.error error
            throw new UsernameNotFoundException(error)
        }

        return user.getUserDetails()
    }
}


