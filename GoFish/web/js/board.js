

var currentPlayerName = "";

var selectedCardNames = [];

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

function toggleCard(cardTag) {
    cardTag = $(cardTag);
    if (cardTag.hasClass("clicked")) {
        selectedCardNames = $.grep(selectedCardNames, function(cardName) {
            return cardName !== cardTag.attr("card");
        });
        cardTag.removeClass("clicked");
    } else {
        selectedCardNames.push(cardTag.attr("card"));
        cardTag.addClass("clicked");
    }
}

function getHand() {
    $.getJSON("status", {q: "hand"}, function(cards) {
        $("#hand").empty();

        $.each(cards, function(_, card) {
            var cardTag = $("<div>", {
                card: card.name,
                class: "card hand"
            });

            cardTag.append($("<strong>").text(card.name));
            $.each(card.series, function(_, ser) {
                cardTag.append($("<p>", {
                    class: ser.id,
                    text: ser.name
                }));
            });

            if ($.inArray(card.name, selectedCardNames) > -1) {
                cardTag.addClass("clicked");
            }

            cardTag.click(function() {
                toggleCard(this);
            });


            $("#hand").append(cardTag);
        });
    });
}

function refresh() {
    refreshCurrentPlayer();
    refreshPlayersList();
    getMoreLogs();
    getHand();
}

function setupInterval() {
    refresh();
    window.setInterval(function() {
        refresh();
    }, 5000);
}

$(setupInterval);
