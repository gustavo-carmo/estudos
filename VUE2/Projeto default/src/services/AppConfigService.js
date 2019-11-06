export default {

  apiUrl() {

    /*console.log("Output;");
    console.log(location.hostname);
    console.log(document.domain);
    console.log(window.location.hostname);
    console.log("document.URL : " + document.URL);
    console.log("document.location.href : " + document.location.href);
    console.log("document.location.origin : " + document.location.origin);
    console.log("document.location.hostname : " + document.location.hostname);
    console.log("document.location.host : " + document.location.host);
    console.log("document.location.pathname : " + document.location.pathname);*/

    return process.env.API_URL;
  }
}

