$(document).ready(function() {
  var form = $('.ui.form'),
      button = $('.ui.button'),
      message = $('.ui.message');
  
  // valid form and close keyboardShortcuts.
  form.form({
    keyboardShortcuts: false,
    fields: {
      username: 'empty',
      password: 'empty',
    }
  });
  
  // submit form
  form.on('submit', function() {
    if (form.form('is valid')) {
      message.hide();
      hutter.loader(button);
      $.post("/oauth/login", form.serialize()).done(function(data) {
	    if (data.success) {
		  hutter.render(data.result);
		} else {
		  hutter.error(message, data.message);
		  hutter.unloader(button);
		}
      });
	}
  });
  
});