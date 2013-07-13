

var currentPlayerName = "";

function refreshCurrentPlayer() {
    $.getJSON("status", {q: "current"}, function(name) {
        currentPlayerName = name;
    });
}

function refreshPlayersList() {
    $.getJSON("status", {q: "list"}, function(list) {
        $("#list").empty();
        $.each(list, function(_, player) {
            var playerType = player.isHuman ? "human" : "computer";
            var image = $("<img>", {src: "img/" + playerType + ".png"});
            var li = $("<li>").append(image).append(" " + player.playerName + " : " + player.score).attr("id", player.playerName);
            if (player.playerName === currentPlayerName) {
                li.addClass("current");
            }
            $("#list").append(li);
        });
    });
}

function getMoreLogs() {
    $.getJSON("status", {q: "log"}, function(logs) {
        $.each(logs, function(_, line) {
            $("#log").prepend($("<li>", {text: line}));
        });
    });
}

function setupInterval() {
    window.setInterval(function() {
        refreshCurrentPlayer();
        refreshPlayersList();
        getMoreLogs();
    }, 5000);
}

$(refreshCurrentPlayer);
$(refreshPlayersList);
$(setupInterval);
