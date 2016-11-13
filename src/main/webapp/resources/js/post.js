$(function() {
    // Cache

    var $posts = $('#posts');
    var templates = getTemplates(true);


    // Logic

//    retrievePosts();
    addPosts([{
        title: 'Hello, World',
        text: 'Lorem ipsum dolor sit amet, vim tempor denique singulis' +
              'eu, an has intellegam disputando, esse novum omnium at has. Sit ut dolorum dolores',
        user: {
            firstName: "Jane",
            lastName: "Doe"
        }
    }, {
          title: '42',
          text: 'eu, an has intellegam disputando, esse novum omnium at has. Sit ut dolorum dolores',
          user: {
              firstName: "John",
              lastName: "Doe"
          }
     } ]);

    registerEventHandlers();

    // Functions

    function addPosts(posts) {

        posts = makeArray(posts);

        posts.forEach(function(post) {
            post.user = post.user || {};

            $post = templates['post'].clone();
            $post.find('.placeholder-username').html(post.user.firstName + ' ' + post.user.lastName);
            $post.find('.placeholder-post').html(post.text);
            $posts.prepend($post);
            // TODO: add all posts in one batch
        });
    }

    function retrievePosts() {
        getJson("/socNet/api/users/1/posts")
         .success(function(posts) {
            addPosts(posts);
         });
    }

    function registerEventHandlers() {
//        $('#postForm').submit(function(event) {
//
//            var text = $('#text').val();
//            var title = $('#title').val();
//
//            var json = { "text" : text,
//                "title" : title};
//
//            $.post(url, json);
//            document.getElementById("postForm").reset();

            /* $.ajax({
             url: "addPost",
             data: JSON.stringify(json),
             type: "POST",
             beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
             },
             success: function(message) {
             var respContent = "";
             respContent += message.text;
             $("#demo").html(respContent);
             }
             });*/

//            event.preventDefault();
//        });
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

function getTemplates(remove) {
    var templateSources = getTemplatesSources(remove);

    var templates = {};
        Object.keys(templateSources).forEach(function(templateName) {
        templates[templateName] = $(templateSources[templateName]);
    });

    return templates;
}

function getTemplatesSources(remove) {
    var templates = {};
    var templateScripts = [];

    $('script').each(function(i, el) {
        var $el = $(el);
        var templateName = $el.data('template-name');

        if(templateName === undefined) {
            return;
        }

        templates[templateName] = $el.html();
        templateScripts.push($el);
    })

    if(remove === true) {
        templateScripts.forEach(function(el) {
            el.remove();
        }) ;
    }

    return templates;
}

function makeArray(maybeArray) {
    if(!(maybeArray instanceof Array)) {
        return [maybeArray];
    }

    return maybeArray;
}

