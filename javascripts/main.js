jQuery(document).ready(function() {
  jQuery(".help").wrap('<div></div>')
  jQuery(".help").before('<button  type="button" class = "teaser">Aide supplémentaire</button>')
  jQuery(".help").hide();
  jQuery(document).on('click', '.teaser', function() {
    jQuery(this).next(".help").slideToggle(500);
  });
});
