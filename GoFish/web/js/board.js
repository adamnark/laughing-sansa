
/* GLOBAL VARIABLES, VERY USEFUL */
var currentPlayerName = "";
var selectedCardNames = [];
var REFRESH_INTERVAL = 5000;

/* HELPER FUNCTIONS */
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

function generateCardTag(card) {
    var cardTag = $("<div>", {
        card: card.name,
        class: "card"
    });

    cardTag.append($("<strong>").text(card.name));
    $.each(card.series, function(_, ser) {
        cardTag.append($("<p>", {
            class: ser.id,
            text: ser.name
        }));
    });

    return cardTag;
}

/* JSON WRAPPERS */
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

function getLastLogs() {
    $.getJSON("status", {q: "relog"}, function(logs) {
        $.each(logs, function(_, line) {
            $("#log").prepend($("<li>", {text: line}));
        });
    });
}



function getHand() {
    $.getJSON("status", {q: "hand"}, function(cards) {
        $("#hand").empty();
        $.each(cards, function(_, card) {
            var cardTag = generateCardTag(card);
            cardTag.addClass("hand");

            if ($.inArray(card.name, selectedCardNames) > -1) {
                cardTag.addClass("clicked");
            }

            cardTag.click(function() {
                toggleCard(this);
            });

            $("#hand").append(cardTag);
        });
        if ($("#hand").children().length === 0) {
            $.get("do", {
                a: "skip"
            });
            refresh();
        }
    });
}

function getGraveyard() {
    $.getJSON("status", {q: "graveyard"}, function(cards) {
        $("#graveyard").empty();
        $.each(cards, function(_, card) {
            var cardTag = generateCardTag(card).css('background-color', 'lightgrey');
            $("#graveyard").append(cardTag);
        });
    });
}

function addCommandClickEvent(commandButton) {
    var cmd = commandButton.attr("command");

    if (cmd === "skip") {
        commandButton.click(function() {
            $.get("do", {
                a: cmd
            });
            refresh();
        });
    } else if (cmd === "quit") {
        commandButton.click(function() {
            $.get("do", {
                a: cmd
            }, function() {
                window.location = "home";
            });
        });
    } else if (cmd === "throw") {
        commandButton.click(function() {
            $.post("do", {
                a: cmd,
                cards: selectedCardNames}
            , function(response) {
                $("#message").text(response);
                selectedCardNames = [];
                refresh();
            });
        });

    } else if (cmd === "request") {
        commandButton.click(function() {
            window.location = "request";
        });
    }
}

function getCommands() {
    $.getJSON("status", {q: "commands"}, function(commands) {
        $("#commands").empty();
        $.each(commands, function(_, command) {
            var commandButton = $("<button>", {
                class: "btn",
                command: command,
                text: command
            });
            addCommandClickEvent(commandButton);
            $("#commands").append(commandButton);
        });
    });
}

function isGameOver() {
    $.getJSON("status", {q: "isover"}, function(isover) {
        if (isover) {
            window.location = "gameover";
        }
    });
}


/* MAIN */
function refresh() {
    refreshCurrentPlayer();
    refreshPlayersList();
    getMoreLogs();
    getHand();
    getGraveyard();
    getCommands();
    isGameOver();
}

function setupInterval() {
    refresh();
    window.setInterval(function() {
        refresh();
    }, REFRESH_INTERVAL);
}

$(getLastLogs);
$(setupInterval);
