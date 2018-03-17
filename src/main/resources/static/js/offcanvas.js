$(function () {
    'use strict'

    $('[data-toggle="offcanvas"]').on('click', function () {
        $('.offcanvas-collapse').toggleClass('open')
    })
})

$( ".time" ).each(function(){
    convertTime($(this));
});


function convertTime(date){
        if( parseInt(date.text()) > 60*24-1 ){
            date.text(
                (parseInt(date.text())/(60*24)).toFixed(0).concat(" dni temu")
            );

        } else if ( parseInt(date.text()) > 60 ){
            date.text(
                (parseInt(date.text())/(60)).toFixed(0).concat(" godz. temu")
            );
        } else {
            date.text(date.text().concat(" min. temu"));
        }
}


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

function loadMore(ident){

    $.ajax({url:("/getchunk?id="+ident),success:
            function(res){
                // noinspection JSJQueryEfficiency
                $("#chunk_").find(".username")[0].innerText = res.username;
                $("#chunk_").find("img")[0].setAttribute("src","/avatars/"+res.username+".jpg");
                var x = document.createTextNode("xddd");
                $("#chunk_text").text(res.content);
                $($("#chunk_").find(".time")[0]).text(durationBetween(res.postdate));
                convertTime($($("#chunk_").find(".time")[0]));

                var $repli = ($(".reply-modal")[0]).cloneNode(true);
                $(".reply-modal").remove();
                console.log(res.replies);

                $.each(res.replies,
                    function (index, rpl) {
                        var $tmp = $repli.cloneNode(true);
                        $tmp.getElementsByClassName("username")[0].innerText = rpl.username;
                        $tmp.getElementsByClassName("reply_text")[0].innerText = rpl.content;
                        $tmp.getElementsByClassName("time")[0].innerText = durationBetween(rpl.postdate);
                        convertTime($($tmp.getElementsByClassName("time")));
                        $tmp.getElementsByTagName("img")[0].setAttribute("src","/avatars/"+rpl.username+".jpg");
                        $(".complete_chunk")[0].append($tmp);
                    }
                );

                    $("#myModal").modal();
            }
    });

}

function durationBetween(date){
    var now = new Date($.now());
    var post = new Date(date.year,date.monthValue-1, date.dayOfMonth, date.hour,date.minute,0,0);
    return parseInt((now-post)/60000);
}
