function postComment() {
    let questionId = $("#questionId").val();
    let content = $("#commentContent").val();

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
            "questionId": questionId,
            "type": 0,
            "content": content
        }),
        success: function (response) {
            if(response.errno == 0){
                window.location.reload();
            }else {
                alert(response.errmsg);
            }
            console.log(response);
        },
        dataType: "json"
    });
    console.log(questionId);
    console.log(content);
}