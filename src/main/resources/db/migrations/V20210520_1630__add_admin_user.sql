INSERT INTO users (username, email, password)
VALUES (
        'admin',
        'admin@admin.com',
        '$2a$10$mIWa9w7tk0tFZdDs3kETUOz8WLpJ2ITbs8tuzYuzVwBAfI3KRhtzW'
    );
INSERT INTO users_roles (roles_id, user_id)
VALUES (
        (
            SELECT id
            FROM roles
            where name = 'ROLE_ADMIN'
        ),
        (
            SELECT id
            FROM users
            where email = 'admin@admin.com'
        )
    );
alter table users
add column office_id BIGINT references offices(id) default null;