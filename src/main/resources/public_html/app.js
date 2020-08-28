var buttonName = document.getElementById('buttonName');
buttonName.addEventListener('click', function () {
    $("#hello").empty();
    var name = document.getElementById('inputname');
    $("#hello").append("hi "+name.value);
});


