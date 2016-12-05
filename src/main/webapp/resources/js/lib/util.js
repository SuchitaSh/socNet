/**
 * Turn scalar element into an array; Do nothing with arrays
 * 1 => [1]
 * [1] => [1]
 * [1, 2] => [1, 2]
 */
function makeArray(maybeArray) {
    if (!(maybeArray instanceof Array)) {
        return [maybeArray];
    }

    return maybeArray;
}

/**
 * Jquery AJAX query shortcut for doing JSON queries;
 *
 * jQuery.getJson is not suitable because it does not set the contentType property of the
 *  request
 */
function ajaxJson(url, method, data) {
    return $.ajax(url, {
            method: method,
            data: data,
            dataType: 'json',
            contentType: 'application/json',
    });
}

function getJson(url, data) {
    return ajaxJson(url, 'GET', data);
}

/**
 * There is no analog in JQuery that do a JSON POST request
 * data should be an object. It will be automatically serialized
 */
function postJson(url, data) {
    return ajaxJson(url, 'POST', JSON.stringify(data));
}

function deleteJson(url, data) {
    return ajaxJson(url, 'DELETE', data);
}

/**
 * Instantiate all template acquired by getTemplatesSources
 * Values would be JQuery DOM objects
 */
function getTemplates(remove) {
    var templateSources = getTemplatesSources(remove);

    var templates = {};
    Object.keys(templateSources).forEach(function (templateName) {
        templates[templateName] = $(templateSources[templateName]);
    });

    return templates;
}

/**
 * Scans the HTML page for <script> tags marked with [type=template]
 * if remove === true then removes that scripts from the page
 * returns a map with keys corresponding to template names([data-template-name=<name>])
 *   and values representing the text content of the script tags
 *
 * Example:
 *  <script type="template" data-template-name="x">
 *    <i>x</i>
 *  </script>
 *
 * getTemplatesSources() => { x: "<i>x</i>" }
 */
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

