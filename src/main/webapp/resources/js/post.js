$(function () {
    // Cache

    var $posts = $('#posts');
    var $postForm = $('#post-form');
    var $formPostTitle = $('#form-post-title');
    var $formPostText = $('#form-post-text')
    var templates = getTemplates(true);
    var userId = $('#user-id').val();


//     Logic

    retrievePosts();

    registerEventHandlers();

    // Functions

    function addPosts(posts) {

        posts = makeArray(posts);

        posts.forEach(function (post) {
            post.user = post.user || {};

            $post = templates['post'].clone();
            var postLink = '<a href="posts/' + post.id + '">' + post.title + '</a>';

            $post.find('.placeholder-title').html(postLink);
            $post.find('.placeholder-post').html(post.text);
            $posts.prepend($post);
            // TODO: add all posts in one batch
        });
    }

    function retrievePosts() {
        getJson('/socNet/api/users/' + userId + '/posts')
            .success(function (posts) {
                addPosts(posts);
            });
    }

    function sendPost(post) {
        addPosts(post);
        postJson('/socNet/api/users/' + userId + '/posts', post);
    }

    function registerEventHandlers() {

        $postForm.submit(function (e) {
            e.preventDefault();
            sendPost({
                title: $formPostTitle.val(),
                text: $formPostText.val()
            });
            $postForm.find('*').val('');
        });
    }

});

function getJson(url, data) {
    return $.ajax(url, {
        method: 'GET',
        data: data,
        dataType: 'json',
        contentType: 'application/json',
    });
}

function postJson(url, data) {
    return $.ajax(url, {
        method: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
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