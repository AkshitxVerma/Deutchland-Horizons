
        // Get the select element and the input field
        const destinationSelect = document.getElementById('destinationSelect');
        const otherInput = document.getElementById('otherInput');

        // Add an event listener to the select element
        destinationSelect.addEventListener('change', function() {
            if (destinationSelect.value === '3') {
                otherInput.style.display = 'block'; // Show the input field
            } else {
                otherInput.style.display = 'none'; // Hide the input field
            }
        });