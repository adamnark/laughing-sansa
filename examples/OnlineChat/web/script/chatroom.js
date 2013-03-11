var usertListVersion;
var chatVersion = 0;
var refreshRate = 1000; //miliseconds

//users = a list of usernames
function refreshUsersList(users) {
    //clear all current users
    currentSelectedOption = $('#userslist>option:selected').text();
    console.log("current selection: " + currentSelectedOption);
    $("#userslist").empty();
    $.each(users, function(index, val) {
        console.log("Adding user #" + index + ": " + val);
        //create a new <option> tag with a value in it and
        //appeand it to the #userslist (div with id=userslist) element
        $('<option>' + val + '</option>').appendTo($("#userslist"));
    });
}

//chat = the added chat strings represnted as a single string
function appendToChatArea(chat) {
    $("#chatarea").append(chat);
}

//the "&rand=+Math.random()" is meant to overcome a bug in IE9 which causes
//it to cache AJAX calls. Adding a random number on each call make the AJAX call
//unique thus preventing caching
function ajaxUsersList() {
    $.ajax({
        url: "userslist",//?rand=" + Math.random(),
        dataType: 'json',
        success: function(data) {
            if (data.version != usertListVersion) {
                console.log("Server users list version is: " + data.version + ", Current is: " + usertListVersion);
                usertListVersion = data.version;
                refreshUsersList(data.usersSet);
            }
        }
    });
}

//call the server and get the chat version
//we also send it the current chat version so in case there was a change
//in the chat content, we will get the new string as well
//the "&rand=+Math.random()" is meant to overcome a bug in IE9 which causes
//it to cache AJAX calls. Adding a random number on each call make the AJAX call
//unique thus preventing caching
function ajaxChatContent() {
    $.ajax({
        url: "chat?chatversion=" + chatVersion,// + "&rand="+Math.random(),
        dataType: 'json',
        success: function(data) {
            console.log("Server chat version: " + data.version + ", Current chat version: " + chatVersion);
            if (data.version != chatVersion) {
                chatVersion = data.version;
                appendToChatArea(data.chat);
            }
            triggerAjaxChatContent();
        },
        error: function(error) {
            triggerAjaxChatContent();
        }
    });
}

//add a method to the button in order to make that form use AJAX
//and not actually aubmit the form
$(function() { // onload...do
    //add a function to the submit event
    $("#chatform").submit(function() {
        // now we're going to capture *all* the fields in the
        // form and submit it via ajax.

        // :input is a macro that grabs all input types, select boxes
        // textarea, etc.  Then using the context of the form from
        // the initial '#contactForm' to narrow down our selector
        var inputs = [];
        $(":input", this).each(function() {
            inputs.push(this.name + '=' + escape(this.value));
        })

        // now if I join our inputs using '&' we'll have a query string
        jQuery.ajax({
            data: inputs.join('&'),
            url: this.action,
            timeout: 2000,
            error: function() {
                console.log("Failed to submit");
            },
            success: function(r) {
                //do not add the user string to the chat area
                //since it's going to be retrieved from the server
                //$("#result h1").text(r);
            }
        })

        $("#userstring").val("");

        // by default - we'll always return false so it doesn't redirect the user.
        return false;
    })
})

function triggerAjaxChatContent() {
    setTimeout(ajaxChatContent, refreshRate);
}

//activate the timer calls after the page is loaded
$(function() {
    //The users list is refreshed automatically every second
    setInterval(ajaxUsersList, refreshRate);
    //The chat content is refreshed only once (using a timeout) but
    //on each call it triggers another execution of itself later (1 second later)
    triggerAjaxChatContent();
});