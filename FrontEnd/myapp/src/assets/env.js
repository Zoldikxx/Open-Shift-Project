(function (window) {
    window["env"] = window["env"] || {};

    // Environment variables
    window["env"]["apiUrl"] = `${environment.apiUrl}`;
    window["env"]["debug"] = true;
})(this);