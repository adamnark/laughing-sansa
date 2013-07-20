function initReplayLink() {
    $("#replay").click(function() {
        $.post("gameover", {replay: "y"}, function(response) {
            window.location = "newgame";
        });
    });
}

function initKillLink() {
    $("#kill").click(function() {
        $.post("gameover", {replay: "n"}, function(response) {
            window.location = "newgame";
        });
    });
}

$(initReplayLink);
$(initKillLink);