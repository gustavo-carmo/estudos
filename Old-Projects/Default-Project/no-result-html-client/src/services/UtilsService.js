export default {

  resolveCurrentDomain() {
    return document.location.origin
  },

  localStorageSetItem(label, obj) {
    window.localStorage.setItem(label, JSON.stringify(obj))
  },

  localStorageGetItem(label) {
    const string = window.localStorage.getItem(label)
    return JSON.parse(string)
  },

  buildErrorMessage(errorsParam) {

    let errors = [];

    let i;
    for (i in errorsParam) {
      let error = errorsParam[i];
      errors[error.field] = error;
    }
    console.log("Devera retornar os erros em uma lista");
    console.log("errors");
    console.log(errors);

    return errors
  }
}
