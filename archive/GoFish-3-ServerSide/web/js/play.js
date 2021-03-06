function addHidden(theForm, key, value) {
    // Create a hidden input element, and append it to the form:
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = key;
    input.value = value;
    theForm.appendChild(input);
}

$("div.hand").click(function() {
    /*$(this).toggleClass("clicked");*/
    var form = $("[name='playerform']").get(0);
    var key = 'card';
    var value = $(this).attr('card');
    addHidden(form, key, value);
    form.submit();
});

$("#skip").click(function() {
    var form = $("[name='playerform']").get(0);
    addHidden(form, 'action', 'skip');
    form.submit();
});

$("#throw").click(function() {
    var form = $("[name='playerform']").get(0);
    addHidden(form, 'action', 'throw');
    form.submit();
});

$("#request").click(function() {
    var form = $("[name='playerform']").get(0);
    addHidden(form, 'action', 'request');
    form.submit();
});

$("#aiturn").click(function() {
    var form = $("[name='playerform']").get(0);
    addHidden(form, 'action', 'aiturn');
    form.submit();
});

$("#replay").click(function() {
    var form = $("[name='replayform']").get(0);
    addHidden(form, 'action', 'start');
    form.submit();
});