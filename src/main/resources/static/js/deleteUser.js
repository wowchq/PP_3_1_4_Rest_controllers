// ----------------------------- Удаление пользователя --------------------------------------
function openDeleteUserPopup(id) {
    // Получаем данные пользователя
    fetch(`/api/admin/${id}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Не удалось загрузить данные пользователя');
            }
        })
        .then(user => {
            // Заполняем поля формы данными пользователя
            document.getElementById('deleteUserId').value = user.id;
            document.getElementById('deleteUsername').value = user.username;
            document.getElementById('deleteSurname').value = user.surname;
            document.getElementById('deleteAge').value = user.age;
            const editRolesSelect = document.getElementById('deleteRoles');
            Array.from(editRolesSelect.options).forEach(option => {
                option.selected = user.roles.some(role => role.id === parseInt(option.value, 10));
            });

            // Открываем модальное окно
            openModal('deleteUserModal');
        })
        .catch(error => {
            console.error('Ошибка при загрузке данных пользователя:', error);
            // alert('Ошибка при загрузке данных пользователя');
        });
}

document.getElementById('deleteUserForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const id = document.getElementById('deleteUserId').value;
    fetch(`/api/admin/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                fetchUsers();
                closeModal('deleteUserModal');
            } else {
                return response.json().then(data => {
                    throw new Error(data.message || 'Не удалось удалить пользователя');
                });
            }
        })
        .catch(error => {
            console.error('Ошибка при удалении пользователя:', error);
        });
});
