import repositoryService from '../../services/RepositoryService'

export default {

  buildQuery: function (url, filter) {

    let query = [];

    if (filter.code) {
      /*TODO vai ser uma lista de codigos*/
      query.push("code=" + filter.code);
    }
    if (filter.status) {
      query.push("status=" + filter.status);
    }

    if (filter.openingDate) {
      var openingDateStart = filter.openingDate[0];
      if (openingDateStart) {
        query.push("openingDateStart=" + openingDateStart.toISOString()
            .slice(0, 10) + 'T00:00:00Z');
      }
      var openingDateEnd = filter.openingDate[1]
      if (openingDateEnd) {
        query.push("openingDateEnd=" + openingDateEnd.toISOString()
            .slice(0, 10) + 'T23:59:59Z');
      }
    }

    if (filter.closingDate) {
      var closingDateStart = filter.closingDate[0]
      if (closingDateStart) {
        query.push("closingDateStart=" + closingDateStart.toISOString()
            .slice(0, 10) + 'T00:00:00Z');
      }
      var closingDateEnd = filter.closingDate[1]
      if (closingDateEnd) {
        query.push("closingDateEnd=" + closingDateEnd.toISOString()
            .slice(0, 10) + 'T23:59:59Z');
      }
    }

    if (filter.currentPage) {
      query.push("currentPage=" + filter.currentPage);
    }
    if (filter.itemsPerPage) {
      query.push("itemsPerPage=" + filter.itemsPerPage);
    }
    if (query.length) {
      url = url + "?" + query.join("&");
    }

    return url
  }
}
