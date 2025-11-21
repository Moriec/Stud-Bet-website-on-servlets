package com.studbet.util.calculator.impl;

import com.studbet.util.calculator.NormalCalculator;

/**
 * Простая версия без зависимостей - использует встроенные возможности Java
 * Приблизительный расчет функции распределения
 */
public class NormalCalculatorImpl implements NormalCalculator {

    private double mean;
    private double stdDev;

    public NormalCalculatorImpl(double mean, double stdDev) {
        if (stdDev <= 0) {
            throw new IllegalArgumentException("Стандартное отклонение должно быть > 0");
        }
        this.mean = mean;
        this.stdDev = stdDev;
    }

    // метод Абрамовица и Стегуна
    private double normalCDF(double x) {
        double z = (x - mean) / stdDev;

        double a1 =  0.254829592;
        double a2 = -0.284496736;
        double a3 =  1.421413741;
        double a4 = -1.453152027;
        double a5 =  1.061405429;
        double p  =  0.3275911;

        int sign = z < 0 ? -1 : 1;
        z = Math.abs(z) / Math.sqrt(2.0);

        double t = 1.0 / (1.0 + p * z);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) *
                t * Math.exp(-z * z);

        return 0.5 * (1.0 + sign * y);
    }

    public double calculateProbability(double min, double max) {
        validateRange(min, max);
        return normalCDF(max) - normalCDF(min);
    }

    private void validateRange(double min, double max) {
        if (min < 0 || max > 100) {
            throw new IllegalArgumentException("Оценка должна быть от 0 до 100");
        }
        if (min > max) {
            throw new IllegalArgumentException("min > max");
        }
    }
}