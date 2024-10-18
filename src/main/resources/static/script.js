document.addEventListener('DOMContentLoaded', function () {
    const heartButtons = document.querySelectorAll('form[action="/toggle-favorite"]');
    heartButtons.forEach(button => {
        button.addEventListener('submit', function (event) {
            event.preventDefault();

            const heartIcon = button.querySelector('i');
            const favoriteCountBadge = button.closest('.book-card').querySelector('.badge');

            if (heartIcon.classList.contains('text-muted')) {
                heartIcon.classList.remove('text-muted');
                heartIcon.classList.add('text-danger');
            } else {
                heartIcon.classList.remove('text-danger');
                heartIcon.classList.add('text-muted');
            }

            let count = parseInt(favoriteCountBadge.innerText) || 0;
            count = heartIcon.classList.contains('text-danger') ? count + 1 : count - 1;
            favoriteCountBadge.innerText = count;
        });
    });

    document.getElementById('searchInput').addEventListener('input', function () {
        const query = this.value.toLowerCase();
        const suggestions = document.getElementById('suggestions');
        suggestions.innerHTML = '';
        suggestions.style.display = 'none';

        if (query.length > 0) {
            fetch('/search/suggestions?query=' + query)
                .then(response => response.json())
                .then(data => {
                    if (data.length > 0) {
                        suggestions.style.display = 'block';
                        data.forEach(book => {
                            const suggestionItem = document.createElement('a');
                            suggestionItem.className = 'list-group-item list-group-item-action';
                            suggestionItem.href = '/books/' + book.id;
                            suggestionItem.textContent = book.title;
                            suggestions.appendChild(suggestionItem);
                        });
                    }
                })
                .catch(error => console.error('Error fetching suggestions:', error));
        }
    });
});

function toggleFavorite(button, bookId) {
    $.post('/toggle-favorite', { bookId: bookId })
        .done(function () {
            const heartIcon = $(button).find('i');
            const favoriteCountBadge = $(button).closest('.book-card').find('.badge');

            heartIcon.toggleClass('text-danger text-muted');

            let count = parseInt(favoriteCountBadge.text()) || 0;
            count = heartIcon.hasClass('text-danger') ? count + 1 : count - 1;
            favoriteCountBadge.text(count);
        })
        .fail(function () {
            console.error('Error toggling favorite status.');
        });
}





