var buttonimg = document.getElementById('btn1');
console.log(buttonimg);
buttonimg.addEventListener('click', function () {
    $('#myImg').toggle('slow');
});

var buttonName = document.getElementById('buttonName');
buttonName.addEventListener('click', function () {
    $("#hello").empty();
    var name = document.getElementById('inputname');
    $("#hello").append("hi "+name.value);
});


