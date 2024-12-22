// --- Получение данных о текущем пользователе и настройка элементов навигации на основе его ролей ---
document.addEventListener("DOMContentLoaded", function () {
    // Запрос данных о пользователе через API
    fetch("api/user")
        .then(response => response.json())
        .then(user => {
            // Отображение полей в таблице пользователя
            document.getElementById("id").textContent = user.id;
            document.getElementById("username").textContent = user.username;
            document.getElementById("surname").textContent = user.surname;
            document.getElementById("age").textContent = user.age;

            // Обработка ролей (если есть несколько)
            let roles = user.roles.map(role => role.name.substring(5)).join(", ");
            document.getElementById("roleIds").textContent = roles;

            // Отображение роли пользователя в навигации
            document.getElementById("navbarUsername").textContent = user.username;
            document.getElementById("navbarUserRoles").textContent = roles;

            // Скрываем элемент навигации для админа, если у пользователя нет роли ADMIN
            const adminNavItem = document.getElementById('adminNavItem');
            if (!user.roles.some(role => role.name === 'ROLE_ADMIN')) {
                adminNavItem.style.display = 'none';
            }
        })
        .catch(error => console.error("Error fetching user data:", error));
});