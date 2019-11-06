import localeEN from './en';
import enLocale from 'element-ui/lib/locale/lang/en'
import localePTBR from './pt-br';
import elementLocalePTBR from 'element-ui/lib/locale/lang/pt-br'
import localeES from './es';
import esLocale from 'element-ui/lib/locale/lang/es'

import helloI18n from '../components/i18n'

const messages = {
  en: {
    ...localeEN,
    ...enLocale,
    ...helloI18n.en
  },
  pt: {
    ...localePTBR,
    ...elementLocalePTBR,
    ...helloI18n.pt
  },
  es: {
    ...localeES,
    ...esLocale,
    ...helloI18n.es
  }
};

export default {
  ...messages
}
