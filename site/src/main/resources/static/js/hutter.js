var hutter = hutter || {};

hutter.el = {
  id: $("meta[property='hutter:id']"),
  user: $("meta[property='hutter:user']"),
  oauthDropdown : $("#oauth-dropdown")
}

hutter.render = function(path) {
	window.location.href = path;
}

hutter.info = function(selector, message) {
	selector.addClass('info').text(message).show();
}

hutter.error = function(selector, message) {
	selector.addClass('error').text(message).show();
}

hutter.warn = function(selector, message) {
	selector.addClass('warning').text(message).show();
}

hutter.loader = function(selector) {
	selector.addClass('loading disabled');
}

hutter.unloader = function(selector) {
	selector.removeClass('loading disabled');
}

// Get user info
hutter.user = {
  id: hutter.el.id.attr('content'),
  name: hutter.el.user.attr('content'),
  isLogin: function() {
    return hutter.el.id.length==1;
  }
}

$(function() {
	hutter.el.oauthDropdown.dropdown();
});