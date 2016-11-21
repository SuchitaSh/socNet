$(function () {
    // Cache

    var $posts = $('#posts');
    var $postForm = $('#post-form');
    var $formPostTitle = $('#form-post-title');
    var $formPostText = $('#form-post-text');

    var userId = $('#user-id').val();
    var currentUserId = $('#current-user-id').val();
    var firstName = $('#current-user-first-name').val();
    var lastName = $('#current-user-last-name').val();
    var username = $('#current-user-username').val();
    var templates = getTemplates(true);

    // Logic

    retrievePosts();

    registerEventHandlers();

    // Functions

    function addPosts(posts) {

        posts = makeArray(posts);

        posts.forEach(function (post) {
            post.author.firstName = post.author.firstName || firstName;
            post.author.lastName = post.author.lastName || lastName;
            post.author.username = post.author.username || username;

            $post = templates['post'].clone();  //todo remove socNet
            var postLink = '<a href="/posts/' + post.id + '">' + post.title + '</a>';
            var authorLink = '<a href="/home/' + post.author.username + '">' +
                                 post.author.firstName + " " + post.author.lastName +
                             '</a>'

            $post.find('.placeholder-title').html(postLink);
            $post.find('.placeholder-post').html(post.text);
            $post.find('.placeholder-author').html(authorLink);
            $posts.prepend($post);
            // TODO: add all posts in one batch
        });
    }

    function retrievePosts() {
        getJson('/api/users/' + userId + '/posts')
            .success(function (posts) {
                addPosts(posts);
            });
    }

    function sendPost(post) {
        addPosts(post);
        postJson('/api/users/' + userId + '/posts', post).
        success(console.log);
    }

    function registerEventHandlers() {

        $postForm.submit(function (e) {
            e.preventDefault();
            sendPost({
                title: $formPostTitle.val(),
                text: $formPostText.val(),
                author: {
                    id: currentUserId,
                }
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