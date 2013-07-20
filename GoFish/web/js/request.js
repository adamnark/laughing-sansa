
function getPlayerName() {
    var victimeNameSelection = $("input[type='radio'][name='player']:checked");
    var victimeName;
    if (victimeNameSelection.length > 0) {
        victimeName = victimeNameSelection.val();
        return victimeName;
    } else {
        $("#message").text("you should select a player...");
        return false;
    }
}

function getSelectedSeries() {
    var selection = $("input[name='series']:checked");
    var series = [];
    if (selection.length > 0) {
        $.each(selection, function(_, v) {
            series.push(v.value);
        });
        return series;
    }
    else {
        $("#message").text("you should select a player...");
        return false;
    }

}


function initSubmitButton() {
    $("#requestbtn").click(function() {
        $("#message").empty();
        var victimeName = getPlayerName();
        if (!victimeName) {
            return;
        }
        var series = getSelectedSeries();
        if (!series) {
            return;
        }

        $.getJSON("request", {
            q: "req",
            op: victimeName,
            series: series
        }, function(response) {
            var wasValid = response.success;
            var message = response.message;
            if (wasValid) {
                window.location = "board";
            } else {
                $("#message").text(message);
            }
        });
    });

}


$(initSubmitButton);