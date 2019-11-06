import fieldJsonBuilder from './FieldJsonBuilder'

import repositoryService from '../../services/RepositoryService';
import appConfig from '../../services/AppConfigService';

const PATH = appConfig.apiUrl() + '/api/form';

export default {

  generateId: function () {
    function s4() {
      return Math.floor((1 + Math.random()) * 0x10000)
        .toString(16)
        .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  },
  addRow: function (rows, quantity) {
    var row = {
      "cols": []
    };
    for (var i = 0; i < quantity; i++) {
      row.cols.push({"fields": []});
    }
    rows.push(row);
  },
  findFieldIndex: function (id, fields) {
    var index = _.findIndex(fields, function (o) {
      return o.id == id;
    });
    if (index != undefined) {
      return index;
    }
  },
  findField: function (id, fields) {
    var index = this.findFieldIndex(id, fields);
    if (index != undefined) {
      return fields[index];
    }
  },

  fieldBuildObject: function (field, fields) {

    var currentField = this.findField(field.id, fields);

    if (currentField != undefined) {
      var copy = Object.assign({}, currentField);
      return copy;
    }

    return fieldJsonBuilder.build(field);
  },

  saveFieldDesc: function(payload) {
    let url = PATH + "/updateFieldDesc/" + payload.id;
    return repositoryService.save(url, payload)
  }
}
