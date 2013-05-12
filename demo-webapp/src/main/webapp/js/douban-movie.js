(function(jQuery, window, undefined) {
    var storage = window.localStorage;
    var key = "items";
    var str = storage.getItem(key) || JSON.stringify([]);
    var items = JSON.parse(str);

    // list view
    var listItems = jQuery("ul.list-view").find("li.item");
    var newItems = listItems.map(function(index, node) {
        var item = {};
        var jqItem = jQuery(this);

        var allTitle = jqItem.find("div.item-show > div.title > a").text().trim();
        var titles = allTitle.split(" / ");
        item.chsTitle = titles[0];
        item.rawTitle = titles[1] || item.chsTitle;

        return item;
    });

    jQuery.merge(items, newItems);
    storage.setItem(key, JSON.stringify(items));

    var str = storage.getItem(key) || JSON.stringify([]);
    var items = JSON.parse(str);
    var titles = items.map(function(item) {
        return item.chsTitle;
    });
    var content = titles.join("\n");
    copy(content);

    // grid view
    var gridItems = jQuery("div.grid-view").find("div.item");
    var list = gridItems.map(function(index, node) {
        var item = {};
        var jqItem = jQuery(this);

        var rawTitle = jqItem.find("div.pic > a.nbg").attr("title");
        item.rawTitle = rawTitle;

        var title = jqItem.find("div.info > ul > li.title em").text();
        var chsTitle = title.split(" / ")[0];
        item.chsTitle = chsTitle;

        return item;
    });

    // IMDb top 250
    var rows = jQuery("#main").find("table[border=1]").find("tr:nth-child(n+2)").toArray();
    var items = rows.map(function(node) {
        var item = {};
        var jqItem = jQuery(node);

        var rank = jqItem.find("td:first-child").text().trim();
        rank = rank.replace(".", ""); // "123." => "123"
        item.rank = Math.floor(rank);

        var title = jqItem.find("td:nth-child(3) a").text().trim();
        item.title = title;

        var year = jqItem.find("td:nth-child(3) > *").contents().filter(function() {
            return this.nodeType === 3; //Node.TEXT_NODE
        }).text().trim();
        yearMatches = year.match(/\((.+)\)/);
        item.year = yearMatches[1];

        return item;
    });

    copy(JSON.stringify(items, null, "    "));
})(jQuery, window);


localStorage.setItem("imdb", JSON.stringify(imdbData));
localStorage.setItem("douban", JSON.stringify(doubanData));

// get data from local storage
var imdbData = JSON.parse(localStorage.getItem("imdb") || "[]");
var doubanData = JSON.parse(localStorage.getItem("douban") || "[]");

var imdbMap = {};
imdbData.forEach(function(item) {
    var title = item.title;
    imdbMap[title] = item;
});

var doubanMap = {};
doubanData.forEach(function(item) {
    var title = item.rawTitle;
    doubanMap[title] = item;
});

var matches = doubanData.filter(function(item) {
    var title = item.rawTitle;
    if (imdbMap[title]) {
        item.imdbRank = imdbMap[title].rank;
    }

    return item.imdbRank > 0;
});

matches.sort(function(a, b) {
    return a.imdbRank - b.imdbRank;
});

localStorage.setItem("matches", JSON.stringify(matches));

// matches
var matches_imdb = [
    {
        "chsTitle": "教父",
        "rawTitle": "The Godfather",
        "imdbRank": 2
    },
    {
        "chsTitle": "生活多美好",
        "rawTitle": "It's a Wonderful Life",
        "imdbRank": 29
    },
    {
        "chsTitle": "被解救的姜戈",
        "rawTitle": "Django Unchained",
        "imdbRank": 45
    },
    {
        "chsTitle": "钢琴家",
        "rawTitle": "The Pianist",
        "imdbRank": 48
    },
    {
        "chsTitle": "暖暖内含光",
        "rawTitle": "Eternal Sunshine of the Spotless Mind",
        "imdbRank": 78
    },
    {
        "chsTitle": "莫扎特传",
        "rawTitle": "Amadeus",
        "imdbRank": 91
    },
    {
        "chsTitle": "两杆大烟枪",
        "rawTitle": "Lock, Stock and Two Smoking Barrels",
        "imdbRank": 136
    },
    {
        "chsTitle": "勇士",
        "rawTitle": "Warrior",
        "imdbRank": 154
    },
    {
        "chsTitle": "荒野生存",
        "rawTitle": "Into the Wild",
        "imdbRank": 162
    },
    {
        "chsTitle": "国王的演讲",
        "rawTitle": "The King's Speech",
        "imdbRank": 167
    },
    {
        "chsTitle": "美丽心灵",
        "rawTitle": "A Beautiful Mind",
        "imdbRank": 191
    },
    {
        "chsTitle": "艺术家",
        "rawTitle": "The Artist",
        "imdbRank": 210
    },
    {
        "chsTitle": "楚门的世界",
        "rawTitle": "The Truman Show",
        "imdbRank": 216
    }
];