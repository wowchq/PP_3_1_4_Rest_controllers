// --------------------------- Получение и отображение текущего пользователя ------------------------------
function fetchCurrentUser() {
    console.log('Fetching current user info...');
    fetch('/api/admin/user')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch current user info');
            }
            return response.json();
        })
        .then(user => {
            console.log('Current user fetched:', user);
            const usernameSpan = document.getElementById('currentUsername');
            const roleSpan = document.getElementById('currentUserRole');

            usernameSpan.textContent = user.username;
            roleSpan.textContent = user.roles.map(role => role.name.replace('ROLE_', '')).join(', ');
        })
        .catch(error => {
            console.error('Error fetching current user info:', error);
        });
}