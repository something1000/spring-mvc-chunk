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