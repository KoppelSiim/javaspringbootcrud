$(document).ready(function() {
 // Validate form fields
    function validateForm() {
      var nameInput = $('#name').val();
      var selectedOptions = $('#sectors').val();
      var agreedToTerms = $('#agree').prop('checked');

      if (nameInput.trim() === '') {
        $('#name-error').text('Please enter your name');
        return false;
      }

      if (!selectedOptions || selectedOptions.length === 0) {
        $('#sectors-error').text('Please select at least one option');
        return false;
      }

      if (!agreedToTerms) {
        $('#agree-error').text('Please agree to the terms and conditions');
        return false;
      }

      return true; // Form is valid
    }

    // Reset error messages when inputs change
    $('#name, #sectors, #agree').on('input', function() {
      var errorId = $(this).attr('id') + '-error';
      $('#' + errorId).text('');
    });
// get all the data from db and insert into the selectbox
    $.ajax({
        url: '/api/all',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            var sectorsSelect = $('#sectors');
            sectorsSelect.empty();
            // Loop through the data and create option elements
            $.each(data, function(index, sector) {
                var option = $('<option>')
                    .val(sector.optionValue)
                    .text(sector.optionText);
                if (sector.indentation === 1) {
                    option.addClass('indent-1');
                } else if (sector.indentation === 2) {
                    option.addClass('indent-2');
                } else if (sector.indentation === 3) {
                    option.addClass('indent-3');
                }
                sectorsSelect.append(option);
            });
        },
        error: function(xhr, status, error) {
            console.error(status + ': ' + error);
        }
    });

    // Submit form using AJAX
    $('#inputForm').submit(function(e) {
       e.preventDefault(); // Prevent default form submission
        if (!validateForm()) {
            return;
          }
       var formData = {
         name: $('#name').val(),
         selectedOptions: $('#sectors').val(),
         agreedToTerms: $('#agree').prop('checked'),

       };

       $.ajax({
         type: 'POST',
         url: '/api/submit',
         contentType: 'application/json',
         data: JSON.stringify(formData),
         success: function(response) {
           // Handle success response
           console.log(response);
           $('#user-info').html(response);
         },
         error: function(error) {
           // Handle error response
           console.error(error);
         }
       });
    });

    // Update form using AJAX
    // Update button click event
      $('#updateButton').click(function() {
        var formData = {
          name: $('#name').val(),
          selectedOptions: $('#sectors').val(),
          agreedToTerms: $('#agree').prop('checked')

        };

        $.ajax({
          type: 'PUT',
          url: '/api/edit',
          contentType: 'application/json',
          data: JSON.stringify(formData),
          success: function(response) {
            // Handle success response
            console.log(response);
          },
          error: function(error) {
            // Handle error response
            console.error(error);
          }
        });
      });

    // allows to select multiple options without ctrl or shift key
    $("select").mousedown(function(e){
        e.preventDefault();

        var select = this;
        var scroll = select .scrollTop;

        e.target.selected = !e.target.selected;

        setTimeout(function(){select.scrollTop = scroll;}, 0);

        $(select ).focus();
    }).mousemove(function(e){e.preventDefault()});



});