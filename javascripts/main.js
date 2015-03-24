jQuery(document).ready(function() {
  jQuery(".help").wrap('<div></div>')
  jQuery(".help").append('<Strong>Si vous êtes toujours bloqué vous pouvez allez regarder sur la branche solution:</strong> <br/> <code>git checkout step*-solution</code>')
  jQuery(".help").before('<button  type="button" class = "teaser">Aide</button>')
  jQuery(".help").hide();
  jQuery(document).on('click', '.teaser', function() {
    jQuery(this).next(".help").slideToggle(500);
  });
});
