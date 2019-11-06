import { defaults } from 'lodash'
import axios from 'axios'

export const createCliente = (options = {}) => axios.create(defaults({}, options))

export default createCliente()
