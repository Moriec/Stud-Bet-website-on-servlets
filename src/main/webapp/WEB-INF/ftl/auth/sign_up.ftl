<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация - StudBet</title>
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

        .signup-container {
            display: flex;
            min-height: 100vh;
        }

        .signup-left {
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

        .signup-left::before {
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

        .signup-left-content {
            position: relative;
            z-index: 1;
            text-align: center;
        }

        .signup-logo {
            font-size: 48px;
            font-weight: 700;
            margin-bottom: 20px;
            letter-spacing: 1px;
        }

        .signup-tagline {
            font-size: 28px;
            font-weight: 600;
            margin-bottom: 16px;
            line-height: 1.3;
        }

        .signup-description {
            font-size: 16px;
            font-weight: 400;
            line-height: 1.6;
            opacity: 0.95;
            max-width: 400px;
        }

        .signup-right {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 50%;
            padding: 40px 48px;
            background-color: #ffffff;
        }

        .signup-form-container {
            max-width: 400px;
            width: 100%;
            margin: 0 auto;
        }

        .signup-form-title {
            font-size: 32px;
            font-weight: 700;
            color: var(--text-dark);
            margin-bottom: 8px;
            line-height: 1.2;
        }

        .signup-form-subtitle {
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
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(30, 58, 138, 0.1);
        }

        .form-control::placeholder {
            color: var(--text-secondary);
        }

        .alert {
            padding: 12px 16px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 500;
            margin-bottom: 20px;
            border: 1px solid;
        }

        .alert-danger {
            background-color: rgba(239, 68, 68, 0.1);
            color: #991b1b;
            border-color: rgba(239, 68, 68, 0.3);
            display: flex;
            align-items: flex-start;
            gap: 12px;
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

        .signup-login-link {
            text-align: center;
            font-size: 14px;
            color: var(--text-secondary);
            margin-top: 20px;
        }

        .signup-login-link a {
            color: var(--primary);
            text-decoration: none;
            font-weight: 600;
            transition: opacity 0.3s ease;
        }

        .signup-login-link a:hover {
            opacity: 0.8;
        }

        @media (max-width: 991px) {
            .signup-left {
                display: none;
            }

            .signup-right {
                width: 100%;
                padding: 40px 24px;
            }

            .signup-form-container {
                max-width: 100%;
            }
        }

        @media (max-width: 576px) {
            .signup-right {
                padding: 24px 16px;
            }

            .signup-form-title {
                font-size: 28px;
            }

            .signup-form-subtitle {
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
    <div class="signup-container">
        <div class="signup-left">
            <div class="signup-left-content">
                <div class="signup-logo">StudBet</div>
                <h1 class="signup-tagline">Делай ставки на учёбу</h1>
                <p class="signup-description">
                    Присоединись к букмекерской платформе для студентов. 
                    Делай ставки на результаты, выигрывай виртуальную валюту и взлетай в рейтингах!
                </p>
            </div>
        </div>

        <div class="signup-right">
            <div class="signup-form-container">
                <h2 class="signup-form-title">Присоединись к StudBet</h2>
                <p class="signup-form-subtitle">Создай свой аккаунт за несколько секунд</p>

                <#if errors??>
                    <div class="alert alert-danger">
                        <div class="alert-icon">
                            <svg width="20" height="20" viewBox="0 0 20 20" fill="currentColor">
                                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd"/>
                            </svg>
                        </div>
                        <div class="alert-content">
                            <#if errors.emailExists??>
                                <strong>Ошибка регистрации:</strong>
                                <ul>
                                    <li>${errors.emailExists}</li>
                                </ul>
                            </#if>
                            <#if errors.usernameExists??>
                                <strong>Ошибка регистрации:</strong>
                                <ul>
                                    <li>${errors.usernameExists}</li>
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

                <form method="POST" action="" name="signupForm" id="signupForm" novalidate>

                    <div class="form-group">
                        <label for="username">Имя пользователя</label>
                        <input 
                            type="text" 
                            class="form-control" 
                            id="username" 
                            name="username" 
                            placeholder="example_user"
                            value="${username!''}"
                            required
                            minlength="3"
                            maxlength="30"
                            pattern="[a-zA-Z0-9_-]+"
                        >
                        <small class="text-secondary" style="font-size: 12px; color: var(--text-secondary); display: block; margin-top: 4px;">
                            Только буквы, цифры, подчёркивание и дефис (3-30 символов)
                        </small>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input 
                            type="email" 
                            class="form-control" 
                            id="email" 
                            name="email" 
                            placeholder="your@email.com"
                            value="${email!''}"
                            required
                        >
                        <small class="text-secondary" style="font-size: 12px; color: var(--text-secondary); display: block; margin-top: 4px;">
                            Мы никогда не делимся вашим email с третьими лицами
                        </small>
                    </div>

                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input 
                            type="password" 
                            class="form-control" 
                            id="password" 
                            name="password" 
                            placeholder="••••••••"
                            required
                            minlength="8"
                            maxlength="50"
                        >
                        <small class="text-secondary" style="font-size: 12px; color: var(--text-secondary); display: block; margin-top: 4px;">
                            Минимум 8 символов для безопасности
                        </small>
                    </div>

                    <button type="submit" class="btn-submit">Зарегистрироваться</button>

                    <div class="signup-login-link">
                        Уже есть аккаунт? <a href="login">Войти</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('signupForm').addEventListener('submit', function(e) {
            const username = document.getElementById('username').value.trim();
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value;

            if (username.length < 3) {
                alert('Имя пользователя должно содержать минимум 3 символа');
                e.preventDefault();
                return false;
            }

            if (!/^[a-zA-Z0-9_-]+$/.test(username)) {
                alert('Имя пользователя может содержать только буквы, цифры, подчёркивание и дефис');
                e.preventDefault();
                return false;
            }

            if (!email.includes('@')) {
                alert('Пожалуйста, введите корректный email');
                e.preventDefault();
                return false;
            }

            if (password.length < 8) {
                alert('Пароль должен содержать минимум 8 символов');
                e.preventDefault();
                return false;
            }

            return true;
        });
    </script>
</body>
</html>
