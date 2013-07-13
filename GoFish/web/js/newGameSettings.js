
function addValuesToSelect() {
    var totalPlayers = $("#selectnumofplayers").val();
    $("#selectnumofcomps").empty();
    for (i = 0; i < totalPlayers; i++) {
        $("#selectnumofcomps").append($('<option>', {
            value: i,
            text: i + " computer players "
        }));
    }

}

function setupComputerPlayersSelect() {
    $("#selectnumofplayers").change(function() {
        addValuesToSelect();
    });
}

function handleResponse(isStarted) {
//    console.log(isStarted);
    if (isStarted) {
        $("#message").text("Game already started! redirecting you in 3 seconds............");
        $("#message").addClass("alert alert-success");
        window.setInterval(function() {
            window.location = "NewWelcomeServlet";
        }, 3000);
    }
    else {
        $("#message").text("Game hasn't started");
        $("#message").addClass("alert alert-success");
    }
}

function showError() {
    $("#message").text("There was an error contacting the server.!..");
    $("#message").addClass("alert alert-error");
}

function checkIfGameHasStarted() {
    $.ajax({
        dataType: "json",
        url: "newgame?action=is_started",
        success: handleResponse,
        error: showError
    });
}


$(checkIfGameHasStarted);
$(addValuesToSelect);
$(setupComputerPlayersSelect);
