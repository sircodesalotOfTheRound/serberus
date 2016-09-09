$(document).ready(function() {
    var setComment = function () {
            var $newComment = $("<p>"),
                $commentBox = $(".comment-input input"),
                $commentValue = $commentBox.val(),
                $commentSection = $("#comment-section");

            if ($commentValue !== "") {
                $newComment.text("this is a new comment: " + $commentValue);
                $commentSection.append($newComment);

                $commentBox.val("");
            }
    };

    $('.comment-input button').on("click", setComment);
    $('.comment-input').on("keypress", function(event) {
        var enter = 13;

        if (event.keypress == enter) {
            setComment();
        }
    });
});