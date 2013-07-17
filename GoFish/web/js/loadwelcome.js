
function handleResponseListPlayerName(playerNames) {
    if (playerNames.length === 0) {
        $("#message").addClass("alert alert-info");
        $("#message").text("The game is full... redirecting you back home");
        window.setInterval(function() {
            window.location = "home";
        }, 3000);
    } else {
        $("#selectnames").empty();
        $.each(playerNames, function(_, val) {
            $("#selectnames").append($('<option>', {
                value: val,
                text: val
            }));
        });
    }
}

function handleError(jqXHR, textStatus, errorThrown) {
    $("#message").addClass("alert alert-error");
    $("#message").text("There was a connection error with the server......\n" + textStatus + "\n" + errorThrown);
}

function refreshPlayersList() {
    $.ajax({
        url: "LoadWelcome?action=get_available_players",
        dataType: "json",
        success: handleResponseListPlayerName,
        error: handleError
    });
}

function setupInterval() {
    window.setInterval(refreshPlayersList, 5000);
}

function handleResponseNameSelection(response) {
    if (response.lastIndexOf('/', 0) === 0) { // startswith('/')
        $("#message").addClass("alert alert-success");
        $("#message").text("redirecting you to the lobby...");
        window.setInterval(function() {
            window.location = "lobby";
        }, 3000);
    }
    else {
        $("#message").addClass("alert alert-error");
        $("#message").text(response);
    }
}

function setUpSubmitBtn() {
    $("#submitbutton").click(function() {
        
        var name = $("#selectnames").find(":selected").text();
        $.ajax({
            url: "LoadWelcome?selectnames=" + name,
            dataType: "json",
            success: handleResponseNameSelection,
            error: handleError
        });
    });
}

$(setUpSubmitBtn);
$(refreshPlayersList);
$(setupInterval);
