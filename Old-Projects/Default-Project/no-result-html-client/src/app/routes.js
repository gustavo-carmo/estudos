import { routes as auth } from './auth';
import { routes as example } from './example';
import { routes as formBuilder } from './formBuilder';
import { routes as businessLine } from './businessLine';
import { routes as contract } from './contract';
import { routes as form } from './form';
import { routes as menu } from './menu';
import { routes as home } from './home';

export default [
  ...auth,
  ...example,
  ...formBuilder,
  ...businessLine,
  ...contract,
  ...form,
  ...menu,
  ...home
]
