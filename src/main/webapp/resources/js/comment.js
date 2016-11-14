$(function () {
    // Cache

    var $comments = $('#comments');
    var $commentsForm = $('#comments-form');
    // var $formPostTitle = $('#form-post-title');
    var $formCommentText = $('#form-comment-text')
    var templates = getTemplates(true);
    var userId = $('#user-id').val();
    var postId = $('#post-id').val();


//     Logic

    retrieveComments();

    registerEventHandlers();

    // Functions

    function addComments(comments) {

        comments = makeArray(comments);

        comments.forEach(function (comment) {
            comment.user = comment.user || {};

            $comment = templates['comment'].clone();
            $comment.find('.placeholder-comment').html(comment.text);
            $comments.prepend($comment);
        });
    }

    function retrieveComments() {
        getJson('/socNet/api/posts/' + postId + '/comments')
            .success(function (comments) {
                addComments(comments);
            });
    }

    function sendComment(comment) {
        addComments(comment);
        postJson('/socNet/api/posts/' + postId + '/comments', comment);
    }

    function registerEventHandlers() {

        $commentsForm.submit(function (e) {
            e.preventDefault();
            sendComment({
                text: $formCommentText.val()
            });
            $commentsForm.find('*').val('');
        });
    }

});

function getJson(url, data) {
    return $.ajax(url, {
        method: 'GET',
        data: data,
        dataType: 'json',
        contentType: 'application/json'
    });
}

function postJson(url, data) {
    return $.ajax(url, {
        method: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
    });
}

function getTemplates(remove) {
    var templateSources = getTemplatesSources(remove);

    var templates = {};
    Object.keys(templateSources).forEach(function (templateName) {
        templates[templateName] = $(templateSources[templateName]);
    });

    return templates;
}

function getTemplatesSources(remove) {
    var templates = {};
    var templateScripts = [];

    $('script').each(function (i, el) {
        var $el = $(el);
        var templateName = $el.data('template-name');

        if (templateName === undefined) {
            return;
        }

        templates[templateName] = $el.html();
        templateScripts.push($el);
    })

    if (remove === true) {
        templateScripts.forEach(function (el) {
            el.remove();
        });
    }

    return templates;
}

function makeArray(maybeArray) {
    if (!(maybeArray instanceof Array)) {
        return [maybeArray];
    }

    return maybeArray;
}

