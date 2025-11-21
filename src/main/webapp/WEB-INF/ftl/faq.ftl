<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ - StudBet</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #1e3a8a;
            --accent-color: #ff8c00;
            --success-color: #10b981;
            --error-color: #ef4444;
            --text-primary: #1f2937;
            --text-secondary: #6b7280;
            --border-color: #e5e7eb;
            --bg-light: #f3f4f6;
            --bg-white: #ffffff;
        }

        * {
            box-sizing: border-box;
        }

        html {
            font-family: 'Inter', 'Segoe UI', sans-serif;
        }

        body {
            background-color: var(--bg-light);
            color: var(--text-primary);
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: var(--primary-color);
            height: 64px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            color: #ffffff;
        }

        .header-logo {
            font-size: 24px;
            font-weight: 700;
            color: #ffffff;
            text-decoration: none;
        }

        .header-title {
            flex: 1;
            margin-left: 32px;
            font-size: 18px;
            font-weight: 600;
        }

        .header-nav a {
            color: #ffffff;
            text-decoration: none;
            font-weight: 500;
            transition: opacity 0.3s;
        }

        .header-nav a:hover {
            opacity: 0.8;
        }

        .container-main {
            max-width: 900px;
            margin: 32px auto;
            padding: 0 24px;
        }

        .hero {
            background: linear-gradient(135deg, var(--primary-color) 0%, #2563eb 100%);
            color: white;
            padding: 60px 24px;
            border-radius: 12px;
            margin-bottom: 40px;
            text-align: center;
        }

        .hero h1 {
            font-size: 40px;
            font-weight: 700;
            margin: 0 0 16px 0;
        }

        .hero p {
            font-size: 18px;
            opacity: 0.95;
            margin: 0;
        }

        .faq-item {
            background-color: var(--bg-white);
            border: 1px solid var(--border-color);
            border-radius: 8px;
            padding: 24px;
            margin-bottom: 20px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .faq-question {
            font-size: 18px;
            font-weight: 600;
            color: var(--accent-color);
            margin: 0 0 16px 0;
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .faq-question i {
            font-size: 20px;
        }

        .faq-answer {
            font-size: 16px;
            color: var(--text-secondary);
            line-height: 1.8;
            margin: 0;
        }

        .faq-answer strong {
            color: var(--text-primary);
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 12px 24px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
            gap: 8px;
        }

        .btn-secondary {
            background-color: var(--bg-light);
            color: var(--text-primary);
            border: 1px solid var(--border-color);
        }

        .btn-secondary:hover {
            background-color: #e5e7eb;
        }

        .footer-nav {
            text-align: center;
            margin-top: 40px;
            padding-top: 24px;
            border-top: 1px solid var(--border-color);
        }

        @media (max-width: 768px) {
            .header {
                height: 56px;
                padding: 0 16px;
            }

            .header-title {
                display: none;
            }

            .container-main {
                margin: 16px auto;
                padding: 0 16px;
            }

            .hero {
                padding: 40px 20px;
            }

            .hero h1 {
                font-size: 28px;
            }

            .faq-item {
                padding: 16px;
            }

            .faq-question {
                font-size: 16px;
            }

            .faq-answer {
                font-size: 15px;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <a href="." class="header-logo">StudBet</a>
    <div class="header-title">FAQ</div>
    <nav class="header-nav">
        <a href="." title="На главную"><i class="fas fa-home"></i></a>
    </nav>
</header>

<div class="container-main">
    <div class="hero">
        <h1>Часто задаваемые вопросы</h1>
        <p>Найди ответы на вопросы о StudBet</p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-question-circle"></i> Что такое StudBet?
        </h2>
        <p class="faq-answer">
            <strong>StudBet</strong> — это букмекерская платформа для студентов, где вы можете делать ставки на академические результаты с использованием виртуальной валюты (StudCoins). Все ставки производятся виртуальной валютой, которая не имеет реальной денежной стоимости.
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-coins"></i> Сколько StudCoins я получу при регистрации?
        </h2>
        <p class="faq-answer">
            При регистрации все новые пользователи получают <strong>1000 StudCoins</strong>. Это стартовый баланс для начала игры на платформе. Вы можете получить дополнительные StudCoins за выигранные ставки.
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-dice"></i> Какая минимальная ставка?
        </h2>
        <p class="faq-answer">
            Минимальная ставка составляет <strong>1 StudCoin</strong>. Максимальная ставка — <strong>ваш текущий баланс</strong>. После размещения ставка не может быть отменена вами.
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-shield-alt"></i> Можно ли ставить на свои события?
        </h2>
        <p class="faq-answer">
            <strong>Да, можно</strong>
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-clock"></i> Когда я узнаю результат ставки?
        </h2>
        <p class="faq-answer">
            Результат ставки определяется в течение <strong>24 часов</strong> после того, как администрация сайта получит достоверную информацию об итогах события ставки.
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-money-bill"></i> Можно ли вывести StudCoins?
        </h2>
        <p class="faq-answer">
            <strong>Нет, вывести StudCoins НЕЛЬЗЯ.</strong> Это виртуальная валюта только для платформы. Она не имеет реальной стоимости и не может быть обменена на деньги. StudCoins можно использовать только для ставок на StudBet.
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-user"></i> Я забыл пароль. Что делать?
        </h2>
        <p class="faq-answer">
            Плакать ):
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-ban"></i> Что произойдёт, если я нарушу правила?
        </h2>
        <p class="faq-answer">
            За нарушение правил вы можете получить: <strong>предупреждение</strong>, <strong>штраф StudCoins</strong>, <strong>постоянную блокировку</strong> за серьёзные нарушения (попытка взлома, мошенничество).
        </p>
    </div>

    <div class="faq-item">
        <h2 class="faq-question">
            <i class="fas fa-headset"></i> Как связаться с поддержкой?
        </h2>
        <p class="faq-answer">
            Вы можете задать вопрос через:
        <ul style="margin-top: 10px; padding-left: 20px;">
            <li><strong>Telegram:</strong> @studbetbot</li>
        </ul>
        Время ответа: обычно <strong>24-48 часов</strong>.
        </p>
    </div>

    <div class="footer-nav">
        <a href="." class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> На главную
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
