function refreshListOfConnectedPlayers() {
    $.ajax({
        url: "lobby?action=list",
        dataType: "json",
        success: function(listOfNames) {
            $("#players").empty();
            $.each(listOfNames, function(_, val) {
                var txt = val + " connected successfuly";
                $("#players").append($("<li>", {text: txt}));
            });
        },
        error: function() {
            $("#message").text("we encountered an error when contacting the servar.");
        }
    });
}


function handleResponseIsStarted(isStarted) {
    if (isStarted) {
        $("#message").text("All players connected! redirecting you to the game in 1 second...");
        $("#message").addClass("alert-success").removeClass("alert-error");
        window.setInterval(function() {
            window.location = "board";
        }, 1000);
    }
}

function showError() {
    $("#message").text("There was an error contacting the server.!..");
    $("#message").addClass("alert-error").removeClass("alert-info");
}

function checkIfGameHasStarted() {
    $.ajax({
        dataType: "json",
        url: "lobby?action=is_started",
        success: handleResponseIsStarted,
        error: showError
    });
}


function setupInterval() {
    window.setInterval(refreshListOfConnectedPlayers, 1000);
    window.setInterval(checkIfGameHasStarted, 1000);
}

$(setupInterval);

