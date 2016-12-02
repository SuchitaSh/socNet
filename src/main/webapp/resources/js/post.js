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

//    retrievePosts();

    registerEventHandlers();

    createInfiScroll();

    // Functions

    function addPosts(posts, invOrder) {

        posts = makeArray(posts);

        posts.forEach(function (post) {
            post.author.firstName = post.author.firstName || firstName;
            post.author.lastName = post.author.lastName || lastName;
            post.author.username = post.author.username || username;

            $post = templates['post'].clone();  //todo remove socNet
            var editLink = '<a href="/posts/edit/' + post.id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
            var deleteLink = '<a href="/posts/delete/' + post.id + '"><i class="glyphicon glyphicon-remove"></i></a>';
            var postLink = '<a href="/posts/' + post.id + '">' + post.title + '</a>';
            var authorLink = '<a href="/home/' + post.author.username + '">' +
                                 post.author.firstName + " " + post.author.lastName +
                             '</a>'

            $post.find('.placeholder-title').html(postLink);
            $post.find('.placeholder-post').html(post.text);
            $post.find('.placeholder-author').html(authorLink);
            $post.find('.placeholder-remove').html(deleteLink);
            $post.find('.placeholder-edit').html(editLink);

            if(invOrder) {
                $posts.prepend($post);
            } else {
                $posts.append($post);
            }
            // TODO: add all posts in one batch
        });
    }

    function createInfiScroll() {
        var enabled = true;
        var lastScrollTop = 0;
        var loadingThreshold = 0.9;
        var loading = false;
        var firstFired = false;
        var lastPost;

        function _tryLoad() {
            if(loading)
                return;

            var scrollTop = $(window).scrollTop();
            var scrollDelta = scrollTop - lastScrollTop;
            lastScrollTop = scrollTop;

            if (scrollDelta < 0 && firstFired)
                return;

            var scrollHeight = $(window).height() - window.innerHeight;
            var relativeScroll = scrollTop / scrollHeight;

            if(relativeScroll < loadingThreshold && firstFired)
                return;

             firstFired = true;

            loading = true;
            retrievePosts(function (posts) {
                if(posts.length == 0) {
                    enabled = false;
                    return;
                }

                loading = false;
                lastPost = posts[posts.length - 1].id;
            }, lastPost);
        };

        _tryLoad();

        $(window).scroll(_tryLoad);
    }

    function retrievePosts(cb, fromPost) {
        var filterStr = '';
        if(fromPost) {
            filterStr += '?from=' + fromPost;
        }
        getJson('/api/users/' + userId + '/posts' + filterStr)
            .success(function (posts) {
                addPosts(posts);

                if(cb) {
                    cb(posts);
                }
            });
    }

    function sendPost(post) {
        addPosts(post, true);
        postJson('/api/users/' + userId + '/posts', post);
    }

    function registerEventHandlers() {

        $postForm.submit(function (e) {
            e.preventDefault();
            sendPost({
                title: $formPostTitle.val(),
                text: $formPostText.val(),
                author: {
                    id: currentUserId,
                    username: username,
                    lastName: lastName,
                    firstName: firstName
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

function removePost() {
    alert("helo");
}