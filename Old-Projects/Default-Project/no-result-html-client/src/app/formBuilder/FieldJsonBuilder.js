export default {

  build: function (field) {

    var type = field.type;

    if (type == 'header') {
      return this.header(field);
    } else if (type == 'text') {
      return this.text(field);
    } else if (type == 'numeric') {
      return this.numeric(field)
    } else if (type == 'date') {
      return this.date(field)
    } else if (type == 'dateTime') {
      return this.dateTime(field)
    } else if (type == 'time') {
      return this.time(field)
    } else if (type == 'textArea') {
      return this.textArea(field)
    } else if (type == 'cnpj') {
      return this.cnpj(field)
    } else if (type == 'cpf') {
      return this.cpf(field)
    } else if (type == 'currency') {
      return this.currency(field)
    } else if (type == 'email') {
      return this.email(field)
    } else if (type == 'boolean') {
      return this.boolean(field)
    }

    return {};
  },

  commons: function (field) {
    return {
      id: field.id,
      type: field.type,
      name: '',
      columnName: '',
      persistent: true
    }
  },

  commonsLabel: function () {
    return {
      labelPt: '',
      labelEs: '',
      labelEn: ''
    }
  },

  commonsPlaceholder: function () {
    return {
      placeholderPt: '',
      placeholderEs: '',
      placeholderEn: ''
    }
  },

  buildPrefix: function () {
    return {
      prefix: ''
    }
  },

  buildSufix: function () {
    return {
      sufix: ''
    }
  },

  buildMask: function () {
    return {
      mask: ''
    }
  },

  buildRequired: function () {
    return {
      required: true
    }
  },

  buildMinSize: function () {
    return {
      minSize: 0
    }
  },

  buildMaxSize: function () {
    return {
      maxSize: 0
    }
  },

  buildRegex: function () {
    return {
      regex: ''
    }
  },

  buildMinValue: function () {
    return {
      minValue: 0
    }
  },

  buildMaxValue: function () {
    return {
      maxValue: 0
    }
  },

  /////

  header: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    return obj;
  },

  text: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildPrefix());
    obj = Object.assign(obj, this.buildSufix());
    obj = Object.assign(obj, this.buildMask()); // TODO - Precisa fazer a máscara
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildMinSize());
    obj = Object.assign(obj, this.buildMaxSize());
    obj = Object.assign(obj, this.buildRegex()); // TODO - Precisa implementar o Regex
    return obj;
  },

  numeric: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.buildPrefix());
    obj = Object.assign(obj, this.buildSufix());
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildMinValue());
    obj = Object.assign(obj, this.buildMaxValue());
    return obj;
  },

  date: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildRegex()); // TODO - Precisa implementar o Regex
    return obj;
  },

  dateTime: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildRequired());
    return obj;
  },

  time: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildRequired());
    return obj;
  },

  textArea: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildMinSize());
    obj = Object.assign(obj, this.buildMaxSize());
    return obj;
  },

  cnpj: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildMask()); // TODO - Precisa fazer a máscara
    obj = Object.assign(obj, this.buildRequired());
    return obj;
  },

  cpf: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildMask()); // TODO - Precisa fazer a máscara
    obj = Object.assign(obj, this.buildRequired());
    return obj;
  },

  currency: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildPrefix());
    obj = Object.assign(obj, this.buildMask()); // TODO - Precisa fazer a máscara
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildMinValue());
    obj = Object.assign(obj, this.buildMaxValue());
    return obj;
  },

  email: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    obj = Object.assign(obj, this.commonsPlaceholder());
    obj = Object.assign(obj, this.buildPrefix());
    obj = Object.assign(obj, this.buildRequired());
    obj = Object.assign(obj, this.buildRegex()); // TODO - Precisa implementar o Regex
    return obj;
  },

  boolean: function (field) {
    var obj = Object.assign({}, this.commons(field));
    obj = Object.assign(obj, this.commonsLabel());
    return obj;
  }
}
