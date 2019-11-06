import { routes as auth } from './auth'

import { routes as businessArea } from './businessArea'
import { routes as systems } from './systems'

export default [
  ...auth,

  ...businessArea,
  ...systems
]
