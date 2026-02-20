<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Разместить ставку — StudBet</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --primary: #1e3a8a;
            --primary-light: #2563eb;
            --accent: #ff8c00;
            --success: #10b981;
            --error: #ef4444;
            --bg-light: #f3f4f6;
            --white: #ffffff;
            --text-dark: #1f2937;
            --text-secondary: #6b7280;
            --border: #e5e7eb;
        }

        body {
            font-family: 'Inter', 'Segoe UI', sans-serif;
            background-color: var(--bg-light);
            color: var(--text-dark);
        }

        .header {
            background-color: var(--primary);
            height: 64px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .header-brand {
            font-size: 28px;
            font-weight: 700;
            color: var(--white);
            text-decoration: none;
            letter-spacing: 1px;
        }

        .header-nav {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .header-nav a {
            color: var(--white);
            text-decoration: none;
            font-weight: 500;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .header-nav a:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        .main-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 32px 24px;
        }

        .page-title {
            font-size: 32px;
            font-weight: 700;
            color: var(--primary);
            margin-bottom: 8px;
        }

        .page-subtitle {
            font-size: 16px;
            color: var(--text-secondary);
            margin-bottom: 32px;
        }

        .bet-card {
            background-color: var(--white);
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            margin-bottom: 24px;
        }

        .bet-card-header {
            margin-bottom: 24px;
            padding-bottom: 16px;
            border-bottom: 1px solid var(--border);
        }

        .bet-card-header h3 {
            font-size: 24px;
            font-weight: 600;
            color: var(--primary);
            margin-bottom: 8px;
        }

        .bet-card-header p {
            font-size: 14px;
            color: var(--text-secondary);
            margin: 4px 0;
        }

        .balance-info {
            background-color: var(--bg-light);
            padding: 16px;
            border-radius: 6px;
            margin-bottom: 24px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .balance-info-label {
            font-size: 14px;
            color: var(--text-secondary);
        }

        .balance-info-value {
            font-size: 24px;
            font-weight: 700;
            color: var(--accent);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            font-size: 14px;
            font-weight: 600;
            color: var(--text-dark);
            margin-bottom: 8px;
        }

        .form-control {
            width: 100%;
            padding: 12px 16px;
            font-size: 16px;
            border: 1px solid var(--border);
            border-radius: 6px;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary-light);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 16px;
        }

        .odds-display {
            background-color: var(--accent);
            color: var(--white);
            padding: 16px;
            border-radius: 6px;
            margin-bottom: 24px;
            text-align: center;
        }

        .odds-display-label {
            font-size: 12px;
            text-transform: uppercase;
            opacity: 0.9;
            margin-bottom: 4px;
        }

        .odds-display-value {
            font-size: 32px;
            font-weight: 700;
            font-family: 'Courier New', monospace;
        }

        .payout-display {
            font-size: 14px;
            margin-top: 8px;
            opacity: 0.9;
        }

        .btn-primary {
            width: 100%;
            background-color: var(--accent);
            color: var(--white);
            border: none;
            padding: 14px 24px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-primary:hover:not(:disabled) {
            background-color: #e67e00;
        }

        .btn-primary:disabled {
            opacity: 0.6;
            cursor: not-allowed;
        }

        .alert {
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .alert-error {
            background-color: #fee2e2;
            color: #991b1b;
            border: 1px solid #fecaca;
        }

        .alert-success {
            background-color: #d1fae5;
            color: #065f46;
            border: 1px solid #a7f3d0;
        }

        .back-link {
            color: var(--primary);
            text-decoration: none;
            font-weight: 500;
            margin-bottom: 16px;
            display: inline-block;
        }

        .back-link:hover {
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .main-container {
                padding: 24px 16px;
            }

            .form-row {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<header class="header">
    <a href="." class="header-brand">StudBet</a>
</header>

<main class="main-container">
    <a href="." class="back-link">Назад</a>

    <h1 class="page-title">Разместить ставку</h1>
    <p class="page-subtitle">Сделай прогноз и выиграй StudCoins!</p>

    <#if event?? && subject??>
        <div class="bet-card">
            <div class="bet-card-header">
                <h3>${event.eventType!""}</h3>
                <p><strong>Предмет:</strong> ${subject.name!""}</p>
                <#if event.description??>
                    <p>${event.description}</p>
                </#if>
                <p>Семестр ${event.semestr!""} • ${event.academicYear!""}</p>
            </div>

            <div class="balance-info">
                <span class="balance-info-label">Твой баланс:</span>
                <span class="balance-info-value" id="userBalance">${userBalance!0} StudCoins</span>
            </div>

            <form id="betForm">
                <input type="hidden" name="eventId" value="${event.id!""}">
                <input type="hidden" name="maxScore" value="${maxScore!100}">

                <div class="form-group">
                    <label class="form-label" for="betAmount">Сумма ставки (StudCoins)</label>
                    <input type="number"
                           class="form-control"
                           id="betAmount"
                           name="betAmount"
                           min="1"
                           max="${userBalance!0}"
                           required
                           placeholder="Введите сумму">
                </div>

                <div class="form-group">
                    <label class="form-label">Диапазон прогноза (баллы)</label>
                    <div class="form-row">
                        <div>
                            <label class="form-label" for="predictedScoreMin">От</label>
                            <input type="number"
                                   class="form-control"
                                   id="predictedScoreMin"
                                   name="predictedScoreMin"
                                   min="0"
                                   max="${maxScore!100}"
                                   required
                                   placeholder="0">
                        </div>
                        <div>
                            <label class="form-label" for="predictedScoreMax">До</label>
                            <input type="number"
                                   class="form-control"
                                   id="predictedScoreMax"
                                   name="predictedScoreMax"
                                   min="0"
                                   max="${maxScore!100}"
                                   required
                                   placeholder="${maxScore!100}">
                        </div>
                    </div>
                </div>

                <div class="odds-display" id="oddsDisplay" style="display: none;">
                    <div class="odds-display-label">Коэффициент</div>
                    <div class="odds-display-value" id="oddsValue">-</div>
                    <div class="payout-display" id="payoutDisplay">Выплата: -</div>
                </div>

                <div id="messageContainer"></div>

                <button type="submit" class="btn-primary" id="submitBtn">Разместить ставку</button>
            </form>
        </div>
    <#else>
        <div class="bet-card">
            <p>Событие не найдено или недоступно для ставок.</p>
        </div>
    </#if>
</main>

<script>
    function calculateOdds() {
        const eventId = document.querySelector('input[name="eventId"]').value;
        const betAmount = document.getElementById('betAmount').value;
        const predictedScoreMin = document.getElementById('predictedScoreMin').value;
        const predictedScoreMax = document.getElementById('predictedScoreMax').value;

        if (!eventId || !betAmount || !predictedScoreMin || !predictedScoreMax) {
            document.getElementById('oddsDisplay').style.display = 'none';
            return;
        }

        const params = new URLSearchParams();
        params.append('eventId', eventId);
        params.append('betAmount', betAmount);
        params.append('predictedScoreMin', predictedScoreMin);
        params.append('predictedScoreMax', predictedScoreMax);

        fetch('calculate-odds', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params.toString()
        })
            .then(r => r.json())
            .then(data => {
                if (data.success) {
                    document.getElementById('oddsDisplay').style.display = 'block';
                    document.getElementById('oddsValue').textContent = data.odds.toFixed(2);
                    document.getElementById('payoutDisplay').textContent =
                        'Выплата: ' + Math.round(data.payoutAmount) + ' StudCoins';
                }
            })
            .catch(e => console.error('Error:', e));
    }

    document.getElementById('betForm').addEventListener('submit', function(e) {
        e.preventDefault();

        const submitBtn = document.getElementById('submitBtn');
        submitBtn.disabled = true;
        submitBtn.textContent = 'Обработка...';

        const params = new URLSearchParams(new FormData(this));

        fetch('place-bet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params.toString()
        })
            .then(r => r.json())
            .then(data => {
                const msg = document.getElementById('messageContainer');
                msg.innerHTML = '';

                if (data.success) {
                    msg.innerHTML = '<div class="alert alert-success">' + data.message + '</div>';
                    if (data.newBalance !== undefined) {
                        document.getElementById('userBalance').textContent = data.newBalance + ' StudCoins';
                    }
                    document.getElementById('betForm').reset();
                    document.getElementById('oddsDisplay').style.display = 'none';
                    setTimeout(() => {
                        window.location.href = '.';
                    }, 2000);
                } else {
                    msg.innerHTML = '<div class="alert alert-error">' + data.message + '</div>';
                    submitBtn.disabled = false;
                    submitBtn.textContent = 'Разместить ставку';
                }
            })
            .catch(e => {
                console.error('Error:', e);
                document.getElementById('messageContainer').innerHTML =
                    '<div class="alert alert-error">Ошибка сервера</div>';
                submitBtn.disabled = false;
                submitBtn.textContent = 'Разместить ставку';
            });
    });

    document.getElementById('betAmount').addEventListener('input', calculateOdds);
    document.getElementById('predictedScoreMin').addEventListener('input', calculateOdds);
    document.getElementById('predictedScoreMax').addEventListener('input', calculateOdds);
</script>
</body>
</html>
