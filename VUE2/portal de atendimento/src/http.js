import { defaults } from 'lodash'
import axios from 'axios'

export const createClient = (options = {}) => {

  let http = axios.create(defaults({}, options))
  http.defaults.headers.post['Content-Type'] = 'application/json';
  http.defaults.headers.put['Content-Type'] = 'application/json';
  return http
}

export default createClient()
