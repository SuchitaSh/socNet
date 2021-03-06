$(function () {
    // Cache
    var $posts = $('#posts');
    var $postForm = $('#post-form');
    var $formPostTitle = $('#form-post-title');
    var $formPostText = $('#form-post-text');
    var $confirmRemove = $('#confirm-remove');
    var $modalRemovePost = $('#modal-remove-post');
    var $modalRemoveFailed = $('#modal-remove-failed');

    var userId = $('#user-id').val();
    var currentUserId = $('#current-user-id').val();
    var firstName = $('#current-user-first-name').val();
    var lastName = $('#current-user-last-name').val();
    var username = $('#current-user-username').val();
    var templates = getTemplates(true);

    // Logic

    registerEventHandlers();

    createInfiScrollPosts();

    // Functions

    function addPosts(posts, invOrder) {

        posts = makeArray(posts);

        posts.forEach(function (post) {
            post.author.firstName = post.author.firstName;
            post.author.lastName = post.author.lastName;
            post.author.username = post.author.username;

            $post = templates['post'].clone();
            $post.attr('id', '__post_' + post.id);
            var editLink = '<a href="/posts/edit/' + post.id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
            var postLink = '<a href="/posts/' + post.id + '">' + post.title + '</a>';
            var authorLink = '<a href="/home/' + post.author.username + '">' +
                post.author.firstName + " " + post.author.lastName +
                '</a>';

            $post.find('.placeholder-title').html(postLink);
            $post.find('.placeholder-post').html(post.text);
            $post.find('.placeholder-author').html(authorLink);

            retrieveAllCommentsOfPost($post, post);     /*ADDING COMMENTS*/

            if (post.author.id === +currentUserId || post.user.id === +currentUserId) {
                $post.find('.remove').click(function () {
                    removePost(post.id);
                });
                $post.find('.edit').html(editLink);
            } else {
                $post.find('.remove').remove();
                $post.find('edit').remove();
            }


            if (invOrder) {
                $posts.prepend($post);
            } else {
                $posts.append($post);
            }


            // TODO: add all posts in one batch
        });
    }

    // TODO: Separate into class and methods for clarity
    // TODO: Alter to be suitable for general purpose infinity scrolling
    function createInfiScrollPosts() {
        var enabled = true;
        var lastScrollTop = 0;
        var loadingThreshold = 0.9;
        var loading = false;
        var firstFired = false;
        var lastPost;

        function _tryLoad() {
            if (loading)
                return;

            var scrollTop = $(window).scrollTop();
            var scrollDelta = scrollTop - lastScrollTop;
            lastScrollTop = scrollTop;

            if (scrollDelta < 0 && firstFired)
                return;

            var scrollHeight = $(window).height() - window.innerHeight;
            var relativeScroll = scrollTop / scrollHeight;

            if (relativeScroll < loadingThreshold && firstFired)
                return;

            firstFired = true;

            loading = true;
            retrievePosts(function (posts) {
                if (posts.length == 0) {
                    enabled = false;
                    return;
                }

                loading = false;
                lastPost = posts[posts.length - 1].id;
            }, lastPost);
        }

        _tryLoad();

        $(window).scroll(_tryLoad);
    }

    function retrievePosts(cb, fromPost) {
        var filterStr = '';
        if (fromPost) {
            filterStr += '?from=' + fromPost;
        }
        getJson('/api/users/' + userId + '/posts' + filterStr)
            .success(function (posts) {
                addPosts(posts);

                if (cb) {
                    cb(posts);
                }
            });
    }

    function removePost(id) {
        var $post = $('#__post_' + id);
        $modalRemovePost.unbind('show.bs.modal');

        $modalRemovePost.on('show.bs.modal', function (e) {
            $confirmRemove.unbind('click');
            $confirmRemove.click(function () {
                $post.hide();
                deleteJson('/api/posts/' + id)
                    .fail(function () {
                        $modalRemoveFailed.modal();
                        $post.show();
                    })
                    .done(function () {
                        $post.remove();
                    });
            });
        });

        $modalRemovePost.modal();
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
                },
                user: {
                    id: userId
                }
            });
            $postForm.find('*').val('');
        });
    }

    /*COMMENTS SECTION*/

    function addComments(comments, $post) {

        comments = makeArray(comments);
        var $comments = $post.find('.comments');
        comments.forEach(function (comment) {
            comment.user = comment.user || {};
            var authorLink = link("/home/" + comment.user.username,
                comment.user.firstName + " " + comment.user.lastName);

            $comment = templates['comment'].clone();
            $comment.find('.placeholder-comment').html(comment.text);
            $comment.find('.placeholder-author').html(authorLink);

            $comments.prepend($comment);
        });
    }

    function retrieveAllCommentsOfPost($post, post) {
        $post.find('.btn-success').click(function () {
            addComment(post.id);
        });
        getJson('/api/posts/' + post.id + '/comments')
            .success(function (comments) {
                addComments(comments, $post);
            });
    }

    function addComment(postId) {
        var $post = $('#__post_' + postId);
        var $commentsForm = $post.find('#comments-form');
        var $formCommentText = $post.find('#form-comment-text');

        var comment = {
            text: $formCommentText.val(),
            user: {
                username: username,
                firstName: firstName,
                lastName: lastName
            }
        };
        sendComment(postId, comment);
        addComments(comment, $post);
        $commentsForm.find('*').val('');
    }


    function sendComment(postId, comment) {
        postJson('/api/posts/' + postId + '/comments', comment);
    }


    function link(href, text) {
        return '<a href="' + href + '">' +
            text +
            '</a>';
    }

});

