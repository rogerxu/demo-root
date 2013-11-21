(function(jQuery, window, undefined) {

function tlf() {

    var titles = jQuery("table.datatable").find("tr > th.subject").map(function(item) {
        var link = jQuery(this);
        return link.text().trim();
    }).toArray();

    return titles;
};

var titles = tlf();
titles.join("\n");

})(jQuery, window);