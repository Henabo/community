/**
 * 提交一级回答
 */
function postComment() {
    let questionId = $("#questionId").val();
    let content = $("#commentContent").val();

    comment2target(questionId, 0, content);
}
function comment2target(targetId, type, content) {
    if(!content){
        alert("回答内容不能为空哦～～～");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "userId": 1,
            "questionId": targetId,
            "type": type,
            "content": content
        }),
        success: function (response) {
            if(response.errno == 0){
                window.location.reload();
            }else {
                alert(response.errmsg);
            }
        },
        dataType: "json"
    });

}

function comment(e) {
    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    comment2target(commentId, 1, content)
}

/**
 * 展开二级回答
 */
function collapseComments(e){
    let id = e.getAttribute("data-id");
    let comments = $("#comment-" + id);

    //获取一下二级评论的展开状态
    let collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("commentIconActive");
    } else{
        let subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("commentIconActive");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    let mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded img-public",
                        "src": comment.avatorUrl
                    }));

                    let mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "style":"padding-top: 10px",
                        "html": comment.userName
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "style": "margin-top: 5px"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "style": "color: #999",
                        "html": moment(comment.addTime).format('YYYY-MM-DD')
                    })));

                    let mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    let commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 commentList"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("commentIconActive");
            });
        }
        // $.getJSON("/comment/"+id), function(data){
        //     console.log(data);
        //     let commentBody = $("comment-body-"+id);
        //     let items = [];
        //
        //     $.each( data.data, function(comment) {
        //
        //         let c = $("<div/>",{
        //             "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 commentList",
        //             html: comment.content;
        //         });
        //
        //         items.push(c);
        //     });
        //
        //     $("<div/>",{
        //         "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comment",
        //         "id":"comment-"+id,
        //         html: items.join("")
        //     }).appendTo(commentBody);
        //
        //     $( "<ul/>", {
        //         "class": "my-new-list",
        //         html: items.join( "" )
        //     }).appendTo( "body" );

        // //展开二级评论
        // comments.addClass("in");
        // //标记二级评论展开状态
        // e.setAttribute("data-collapse", "in");
        // e.classList.add("commentIconActive");
    }
}

function showSelectTag() {
    $("#select-tag").show()
}

function selectTag(e) {
    let value = e.getAttribute("data-tag")
    let previous = $("#tag").val();
    if(previous.indexOf(value) == -1){
        if(previous){
            $("#tag").val(previous+','+value);
        }else {
            $("#tag").val(value);
        }
    }
}