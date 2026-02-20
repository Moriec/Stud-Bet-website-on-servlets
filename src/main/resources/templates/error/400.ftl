<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>400 - Некорректный запрос</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #dc2626 0%, #b91c1c 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #1f2937;
        }

        .error-container {
            text-align: center;
            background-color: white;
            padding: 60px 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 90%;
        }

        .error-code {
            font-size: 120px;
            font-weight: 700;
            color: #dc2626;
            margin-bottom: 20px;
            line-height: 1;
        }

        .error-title {
            font-size: 32px;
            font-weight: 700;
            color: #991b1b;
            margin-bottom: 16px;
        }

        .error-message {
            font-size: 18px;
            color: #6b7280;
            margin-bottom: 32px;
            line-height: 1.6;
        }

        .error-details {
            background-color: #f3f4f6;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 32px;
            text-align: left;
            border-left: 4px solid #dc2626;
        }

        .error-details p {
            font-size: 14px;
            color: #4b5563;
            margin: 8px 0;
            word-break: break-all;
        }

        .error-details strong {
            color: #991b1b;
        }

        .button-group {
            display: flex;
            gap: 12px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn {
            padding: 12px 28px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.3s;
            display: inline-block;
        }

        .btn-primary {
            background-color: #dc2626;
            color: white;
        }

        .btn-primary:hover {
            background-color: #b91c1c;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(220, 38, 38, 0.3);
        }

        .btn-secondary {
            background-color: #e5e7eb;
            color: #1f2937;
        }

        .btn-secondary:hover {
            background-color: #d1d5db;
            transform: translateY(-2px);
        }

        .illustration {
            font-size: 80px;
            margin-bottom: 20px;
        }

        @media (max-width: 768px) {
            .error-container {
                padding: 40px 24px;
            }

            .error-code {
                font-size: 80px;
            }

            .error-title {
                font-size: 24px;
            }

            .error-message {
                font-size: 16px;
            }

            .button-group {
                flex-direction: column;
            }

            .btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="illustration">⚠️</div>

    <div class="error-code">400</div>

    <h1 class="error-title">Некорректный запрос</h1>

    <p class="error-message">
        Сервер не может обработать ваш запрос. Проверьте корректность данных и повторите попытку.
    </p>

    <#if errorMessage??>
        <div class="error-details">
            <p><strong>Подробности ошибки:</strong></p>
            <p>${errorMessage}</p>
        </div>
    </#if>

    <#if requestUri??>
        <div class="error-details">
            <p><strong>URL запроса:</strong></p>
            <p>${requestUri}</p>
        </div>
    </#if>

    <#if errorCode??>
        <div class="error-details">
            <p><strong>Код ошибки:</strong></p>
            <p>sc_bad_request</p>
        </div>
    </#if>

    <div class="button-group">
        <button class="btn btn-secondary" onclick="history.back()">Вернуться назад</button>
    </div>
</div>
</body>
</html>