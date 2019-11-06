export default {

  buildQuery: function (url, filter) {

    let query = [];

    if (filter.name) {
      query.push("name=" + filter.name);
    }
    if (filter.username) {
      query.push("username=" + filter.username);
    }
    if (filter.status) {
      query.push("status=" + filter.status);
    }
    if (filter.tenantId) {
      query.push("tenantId=" + filter.tenantId);
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
