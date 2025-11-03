<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход - StudBet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        * {
            transition: background-color 0.3s ease, color 0.3s ease, transform 0.2s ease;
        }

        :root {
            --primary: #1e3a8a;
            --primary-light: #2563eb;
            --accent: #ff8c00;
            --success: #10b981;
            --error: #ef4444;
            --warning: #fbbf24;
            --bg-light: #f3f4f6;
            --text-dark: #1f2937;
            --text-secondary: #6b7280;
            --border: #e5e7eb;
        }

        body {
            font-family: 'Inter', 'Segoe UI', sans-serif;
            background-color: #ffffff;
            color: var(--text-dark);
            margin: 0;
            padding: 0;
        }

        .login-container {
            display: flex;
            min-height: 100vh;
        }

        /* Левая часть - информационная */
        .login-left {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 50%;
            background: linear-gradient(135deg, var(--primary) 0%, var(--primary-light) 100%);
            color: #ffffff;
            padding: 40px 24px;
            position: relative;
            overflow: hidden;
        }

        .login-left::before {
            content: '';
            position: absolute;
            top: -50%;
            right: -50%;
            width: 200%;
            height: 200%;
            background-image: 
                radial-gradient(circle at 20% 50%, rgba(255, 140, 0, 0.1) 0%, transparent 50%),
                radial-gradient(circle at 80% 80%, rgba(37, 99, 235, 0.1) 0%, transparent 50%);
            animation: float 15s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translate(0, 0); }
            50% { transform: translate(30px, -30px); }
        }

        .login-left-content {
            position: relative;
            z-index: 1;
            text-align: center;
        }

        .login-logo {
            font-size: 48px;
            font-weight: 700;
            margin-bottom: 20px;
            letter-spacing: 1px;
        }

        .login-tagline {
            font-size: 28px;
            font-weight: 600;
            margin-bottom: 16px;
            line-height: 1.3;
        }

        .login-description {
            font-size: 16px;
            font-weight: 400;
            line-height: 1.6;
            opacity: 0.95;
            max-width: 400px;
        }

        /* Правая часть - форма */
        .login-right {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 50%;
            padding: 40px 48px;
            background-color: #ffffff;
        }

        .login-form-container {
            max-width: 400px;
            width: 100%;
            margin: 0 auto;
        }

        .login-form-title {
            font-size: 32px;
            font-weight: 700;
            color: var(--text-dark);
            margin-bottom: 8px;
            line-height: 1.2;
        }

        .login-form-subtitle {
            font-size: 16px;
            font-weight: 400;
            color: var(--text-secondary);
            margin-bottom: 32px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            font-weight: 500;
            color: var(--text-dark);
            margin-bottom: 8px;
        }

        .form-control {
            width: 100%;
            padding: 12px 16px;
            font-size: 16px;
            font-weight: 400;
            border: 1px solid var(--border);
            border-radius: 8px;
            background-color: #ffffff;
            color: var(--text-dark);
            transition: all 0.3s ease;
            font-family: 'Inter', sans-serif;
            box-sizing: border-box;
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(30, 58, 138, 0.1);
        }

        .form-control::placeholder {
            color: var(--text-secondary);
        }

        /* Сообщения об ошибках */
        .alert {
            padding: 12px 16px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 500;
            margin-bottom: 20px;
            border: 1px solid;
            display: flex;
            align-items: flex-start;
            gap: 12px;
        }

        .alert-danger {
            background-color: rgba(239, 68, 68, 0.1);
            color: #991b1b;
            border-color: rgba(239, 68, 68, 0.3);
        }

        .alert-icon {
            flex-shrink: 0;
            width: 20px;
            height: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-top: 2px;
        }

        .alert-content {
            flex: 1;
        }

        .alert-content ul {
            margin: 0;
            padding-left: 20px;
        }

        .alert-content li {
            margin-bottom: 4px;
        }

        .alert-content li:last-child {
            margin-bottom: 0;
        }

        /* Опция "Запомнить меня" */
        .form-check {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        .form-check-input {
            width: 18px;
            height: 18px;
            margin-right: 8px;
            cursor: pointer;
            accent-color: var(--primary);
            border: 1px solid var(--border);
            border-radius: 4px;
        }

        .form-check-label {
            font-size: 14px;
            font-weight: 400;
            color: var(--text-dark);
            cursor: pointer;
            margin-bottom: 0;
        }

        .form-check-input:checked {
            background-color: var(--primary);
            border-color: var(--primary);
        }

        /* Кнопка отправки */
        .btn-submit {
            width: 100%;
            padding: 14px 16px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 8px;
            background-color: var(--accent);
            color: #ffffff;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 8px;
        }

        .btn-submit:hover {
            background-color: #e67e00;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(255, 140, 0, 0.3);
        }

        .btn-submit:active {
            transform: translateY(0);
        }

        .btn-submit:disabled {
            background-color: var(--text-secondary);
            cursor: not-allowed;
            transform: none;
        }

        /* Ссылки внизу */
        .login-footer-links {
            text-align: center;
            font-size: 14px;
            color: var(--text-secondary);
            margin-top: 20px;
        }

        .login-footer-links a {
            color: var(--primary);
            text-decoration: none;
            font-weight: 600;
            transition: opacity 0.3s ease;
            margin: 0 4px;
        }

        .login-footer-links a:hover {
            opacity: 0.8;
        }

        .login-divider {
            text-align: center;
            margin: 12px 0;
            color: var(--text-secondary);
        }

        /* Адаптивность */
        @media (max-width: 991px) {
            .login-left {
                display: none;
            }

            .login-right {
                width: 100%;
                padding: 40px 24px;
            }

            .login-form-container {
                max-width: 100%;
            }
        }

        @media (max-width: 576px) {
            .login-right {
                padding: 24px 16px;
            }

            .login-form-title {
                font-size: 28px;
            }

            .login-form-subtitle {
                margin-bottom: 24px;
            }

            .form-group {
                margin-bottom: 16px;
            }

            .btn-submit {
                padding: 12px 16px;
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <!-- Левая часть (скрыта на мобиле) -->
        <div class="login-left">
            <div class="login-left-content">
                <div class="login-logo">StudBet</div>
                <h1 class="login-tagline">Добро пожаловать!</h1>
                <p class="login-description">
                    Войди в свой аккаунт, чтобы продолжить делать ставки на результаты. 
                    Выигрывай виртуальную валюту и занимай топовые места в рейтингах!
                </p>
            </div>
        </div>

        <!-- Правая часть - форма входа -->
        <div class="login-right">
            <div class="login-form-container">
                <h2 class="login-form-title">Вход в StudBet</h2>
                <p class="login-form-subtitle">Введи свои учётные данные ниже</p>

                <!-- Блок с ошибками от сервера -->
                <#if errors??>
                    <div class="alert alert-danger">
                        <div class="alert-icon">
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="currentColor">
                                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd"/>
                            </svg>
                        </div>
                        <div class="alert-content">
                            <#if errors.invalidCredentials??>
                                <strong>Ошибка входа:</strong>
                                <ul>
                                    <li>${errors.invalidCredentials}</li>
                                </ul>
                            </#if>
                            <#if errors.userNotFound??>
                                <strong>Ошибка входа:</strong>
                                <ul>
                                    <li>${errors.userNotFound}</li>
                                </ul>
                            </#if>
                            <#if errors.accountDisabled??>
                                <strong>Ошибка входа:</strong>
                                <ul>
                                    <li>${errors.accountDisabled}</li>
                                </ul>
                            </#if>
                            <#if errors.general??>
                                <strong>Ошибка сервера:</strong>
                                <ul>
                                    <li>${errors.general}</li>
                                </ul>
                            </#if>
                        </div>
                    </div>
                </#if>

                <!-- Форма входа -->
                <form method="POST" name="loginForm" id="loginForm" novalidate>
                    
                    <!-- Поле Username или Email -->
                    <div class="form-group">
                        <label for="login">Имя пользователя или Email</label>
                        <input 
                            type="text" 
                            class="form-control" 
                            id="login" 
                            name="login" 
                            placeholder="username или email@example.com"
                            value="${login!''}"
                            required
                        >
                        <small style="font-size: 12px; color: var(--text-secondary); display: block; margin-top: 4px;">
                            Введи своё имя пользователя или электронную почту
                        </small>
                    </div>

                    <!-- Поле Password -->
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input 
                            type="password" 
                            class="form-control" 
                            id="password" 
                            name="password" 
                            placeholder="••••••••"
                            required
                        >
                    </div>

                    <!-- Опция "Запомнить меня" -->
                    <div class="form-check" hidden="hidden">
                        <input 
                            class="form-check-input" 
                            type="checkbox" 
                            id="rememberMe" 
                            name="rememberMe" 
                            value="on"
                        >
                        <label class="form-check-label" for="rememberMe">
                            Запомнить меня
                        </label>
                    </div>

                    <!-- Кнопка отправки -->
                    <button type="submit" class="btn-submit">Войти</button>

                    <!-- Ссылки внизу -->
                    <div class="login-footer-links">
                        <a href="forgot-password">Забыл пароль?</a>
                        <span class="login-divider">•</span>
                        <a href="sign_up">Зарегистрироваться</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Валидация на клиенте
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            const login = document.getElementById('login').value.trim();
            const password = document.getElementById('password').value;

            // Проверка login (username или email)
            if (login.length === 0) {
                alert('Пожалуйста, введи имя пользователя или email');
                e.preventDefault();
                return false;
            }

            // Если выглядит как email, проверяем формат
            if (login.includes('@')) {
                if (!login.includes('@') || !login.includes('.')) {
                    alert('Пожалуйста, введи корректный email');
                    e.preventDefault();
                    return false;
                }
            }

            // Проверка пароля
            if (password.length === 0) {
                alert('Пожалуйста, введи пароль');
                e.preventDefault();
                return false;
            }

            if (password.length < 3) {
                alert('Пароль не может быть таким коротким');
                e.preventDefault();
                return false;
            }

            return true;
        });
    </script>
</body>
</html>
