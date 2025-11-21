<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>История транзакций - StudBet</title>
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

        /* Header */
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
            max-width: 1200px;
            margin: 32px auto;
            padding: 0 24px;
        }

        .card {
            background-color: var(--bg-white);
            border: 1px solid var(--border-color);
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .card-header {
            background-color: var(--bg-light);
            border-bottom: 2px solid var(--border-color);
            padding: 20px;
        }

        .card-header h2 {
            margin: 0;
            font-size: 24px;
            font-weight: 700;
            color: var(--text-primary);
        }

        .card-body {
            padding: 24px;
        }

        .balance-section {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 16px;
            margin-bottom: 32px;
        }

        .balance-card {
            background: linear-gradient(135deg, var(--primary-color) 0%, #2563eb 100%);
            color: white;
            padding: 24px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .balance-card-label {
            font-size: 14px;
            font-weight: 500;
            opacity: 0.9;
            margin-bottom: 8px;
        }

        .balance-card-value {
            font-size: 32px;
            font-weight: 700;
            font-family: 'Courier New', monospace;
        }

        .transactions-wrapper {
            overflow-x: auto;
        }

        .transactions-table {
            width: 100%;
            border-collapse: collapse;
        }

        .transactions-table thead {
            background-color: var(--bg-light);
        }

        .transactions-table th {
            padding: 12px 16px;
            text-align: left;
            font-weight: 700;
            color: var(--text-primary);
            border-bottom: 2px solid var(--border-color);
            font-size: 14px;
        }

        .transactions-table td {
            padding: 12px 16px;
            border-bottom: 1px solid var(--border-color);
            color: var(--text-primary);
            font-size: 15px;
        }

        .transactions-table tbody tr:hover {
            background-color: var(--bg-light);
        }

        .badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 600;
        }

        .badge-deposit {
            background-color: #10b981;
            color: #ffffff;
            border: 1px solid #10b981;
        }

        .badge-withdrawal {
            background-color: #ef4444;
            color: #ffffff;
            border: 1px solid #ef4444;
        }

        .badge-bet {
            background-color: #ff8c00;
            color: #ffffff;
            border: 1px solid #ff8c00;
        }

        .badge-win {
            background-color: #10b981;
            color: #ffffff;
            border: 1px solid #10b981;
        }

        .badge-loss {
            background-color: #ef4444;
            color: #ffffff;
            border: 1px solid #ef4444;
        }

        .badge-default {
            background-color: #6b7280;
            color: #ffffff;
            border: 1px solid #6b7280;
        }

        /* Amount Colors */
        .amount-positive {
            color: var(--success-color);
            font-weight: 700;
        }

        .amount-negative {
            color: var(--error-color);
            font-weight: 700;
        }

        .empty-state {
            text-align: center;
            padding: 60px 24px;
        }

        .empty-state-icon {
            font-size: 64px;
            color: var(--border-color);
            margin-bottom: 24px;
        }

        .empty-state-title {
            font-size: 24px;
            font-weight: 700;
            color: var(--text-primary);
            margin-bottom: 8px;
        }

        .empty-state-text {
            color: var(--text-secondary);
            margin-bottom: 24px;
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

        .btn-back {
            background-color: var(--accent-color);
            color: #ffffff;
        }

        .btn-back:hover {
            background-color: #e67e00;
            transform: translateY(-2px);
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
            margin-top: 32px;
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

            .card-header {
                padding: 16px;
            }

            .card-header h2 {
                font-size: 20px;
            }

            .card-body {
                padding: 16px;
            }

            .balance-section {
                grid-template-columns: 1fr;
                margin-bottom: 24px;
            }

            .balance-card-value {
                font-size: 24px;
            }

            .transactions-table {
                font-size: 12px;
            }

            .transactions-table th,
            .transactions-table td {
                padding: 8px 12px;
            }

            .empty-state-icon {
                font-size: 48px;
            }

            .empty-state-title {
                font-size: 20px;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <a href=".." class="header-logo">StudBet</a>
    <div class="header-title">История транзакций</div>
</header>

<div class="container-main">
    <#if transactions?? && transactions?size gt 0>
        <div class="balance-section">
            <div class="balance-card">
                <div class="balance-card-label">Всего транзакций</div>
                <div class="balance-card-value">${transactions?size}</div>
            </div>
        </div>
    </#if>

    <div class="card">
        <div class="card-header">
            <h2><i class="fas fa-history"></i> Мои транзакции</h2>
        </div>
        <div class="card-body">
            <#if transactions?? && transactions?size gt 0>
                <!-- Transactions Table -->
                <div class="transactions-wrapper">
                    <table class="transactions-table">
                        <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Тип</th>
                            <th>Описание</th>
                            <th>Сумма</th>
                            <th>Баланс было</th>
                            <th>Баланс стало</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list transactions as transaction>
                            <tr>
                                <td>
                                    <#if transaction.createdAt??>
                                        ${transaction.createdAt?substring(0, 10)}
                                    </#if>
                                </td>
                                <td>
                                    <#if transaction.transactionType == "DEPOSIT">
                                        <span class="badge badge-deposit">Пополнение</span>
                                    <#elseif transaction.transactionType == "WITHDRAWAL">
                                        <span class="badge badge-withdrawal">Вывод</span>
                                    <#elseif transaction.transactionType == "BET">
                                        <span class="badge badge-bet">Ставка</span>
                                    <#elseif transaction.transactionType == "WIN">
                                        <span class="badge badge-win">Выигрыш</span>
                                    <#elseif transaction.transactionType == "LOSS">
                                        <span class="badge badge-loss">Проигрыш</span>
                                    <#else>
                                        <span class="badge badge-default">${transaction.transactionType}</span>
                                    </#if>
                                </td>
                                <td>${transaction.description}</td>
                                <td>
                                    <#if transaction.amount gt 0>
                                        <span class="amount-positive">+${transaction.amount}</span>
                                    <#else>
                                        <span class="amount-negative">${transaction.amount}</span>
                                    </#if>
                                </td>
                                <td>${transaction.balanceBefore}</td>
                                <td><strong>${transaction.balanceAfter}</strong></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#else>
                <div class="empty-state">
                    <div class="empty-state-icon">
                        <i class="fas fa-inbox"></i>
                    </div>
                    <div class="empty-state-title">Нет транзакций</div>
                    <p class="empty-state-text">
                        У вас пока нет истории транзакций. Начните делать ставки на StudBet!
                    </p>
                    <a href=".." class="btn btn-back">
                        <i class="fas fa-arrow-left"></i> Перейти на главную
                    </a>
                </div>
            </#if>
        </div>
    </div>

    <div class="footer-nav">
        <a href=".." class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> На главную
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
