$(function () {
    'use strict'

    $('[data-toggle="offcanvas"]').on('click', function () {
        $('.offcanvas-collapse').toggleClass('open')
    })
})

$( ".time" ).each(function( index ) {
    if( parseInt($(this).text()) > 60*24-1 ){
           $(this).text(
               (parseInt($(this).text())/(60*24)).toFixed(0).concat(" dni temu")
           );

    } else if ( parseInt($(this).text()) > 60 ){
        $(this).text(
            (parseInt($(this).text())/(60)).toFixed(0).concat(" godz. temu")
        );
    } else {
        $(this).text($(this).text().concat(" min. temu"));
    }
});

function showReplyForm(where){

    var all = document.getElementsByClassName("replyform");
var i;
    for(i=0;i<all.length;i++){
        all[i].style.display = "none";
    }
    console.log(where.parentNode);
    where.parentNode.getElementsByClassName("replyform")[0].style.display = "inline";

}

function switchto(zobacz){
    if(zobacz == 'pass'){
        document.getElementById(zobacz).style.display = 'block';
        document.getElementById('profil').style.display='none';
    } else{
        document.getElementById(zobacz).style.display = 'block';
        document.getElementById('pass').style.display='none';
    }
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        if(!input.files[0].name.slice(input.files[0].name.length-4,input.files[0].name.length).localeCompare(".jpg") ||
            !input.files[0].name.slice(input.files[0].name.length-4,input.files[0].name.length).localeCompare(".png")){

        reader.onload = function (e) {
            $('#imgpreview').attr('src', e.target.result);
        };


        reader.readAsDataURL(input.files[0]);
        } else {
            $("#avatar").val("");
            alert("Wybrano nie prawidłowy format pliku!");
        }
    }
}

$("#avatar").change(function(){
    readURL(this);
});