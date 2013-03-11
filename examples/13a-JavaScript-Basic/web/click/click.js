//declaration of a function that will be called on mouse clicks
myclick = function (event, row, col) {
    document.getElementById('row').value = row;
    document.getElementById('col').value = col;
    document.getElementById('button').value = event.button;
    document.forms['clickform'].submit();
}