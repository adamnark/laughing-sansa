function addHidden(theForm, key, value) {
    // Create a hidden input element, and append it to the form:
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = key;
    input.value = value;
    theForm.appendChild(input);
}

$(".hand").click(function() {
    /*$(this).toggleClass("clicked");*/
    var form = $("[name='clickform']").get(0);
    var key = 'card';
    var value = $(this).attr('card');
    addHidden(form, key, value);
    form.submit();
});