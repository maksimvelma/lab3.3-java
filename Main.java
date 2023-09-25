import java.util.Arrays;

// Клас для представлення раціонального дробу
class RationalFraction {
    private int numerator; // Лічильник
    private int denominator; // Знаменник

    public RationalFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Метод для обчислення суми раціональних дробів
    public RationalFraction add(RationalFraction other) {
        int newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
        int newDenominator = this.denominator * other.denominator;

        return simplify(newNumerator, newDenominator);
    }

    // Метод для отримання значення чисельника
    public int getNumerator() {
        return numerator;
    }

    // Метод для отримання значення знаменника
    public int getDenominator() {
        return denominator;
    }

    // Метод для спрощення раціонального дробу
    private RationalFraction simplify(int numerator, int denominator) {
        int gcd = greatestCommonDivisor(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        return new RationalFraction(numerator, denominator);
    }

    // Метод для знаходження найбільшого спільного дільника
    private int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return greatestCommonDivisor(b, a % b);
        }
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

// Клас для представлення полінома
class Polynomial {
    private RationalFraction[] coefficients; // Коефіцієнти

    public Polynomial(RationalFraction[] coefficients) {
        this.coefficients = coefficients;
    }

    // Метод для обчислення суми поліномів
    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.length, other.coefficients.length);
        RationalFraction[] sumOfCoefficients = new RationalFraction[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            RationalFraction a = (i < this.coefficients.length) ? this.coefficients[i] : new RationalFraction(0, 1);
            RationalFraction b = (i < other.coefficients.length) ? other.coefficients[i] : new RationalFraction(0, 1);
            sumOfCoefficients[i] = a.add(b);
        }

        return new Polynomial(sumOfCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i].getNumerator() != 0) {
                result.append(coefficients[i]);
                if (i < coefficients.length - 1) {
                    result.append("x^").append(i).append(" + ");
                }
            }
        }
        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        // Приклад створення раціональних дробів
        RationalFraction fraction1 = new RationalFraction(1, 2);
        RationalFraction fraction2 = new RationalFraction(1, 3);

        // Приклад створення поліномів
        RationalFraction[] coefficients1 = {fraction1, fraction2};
        RationalFraction[] coefficients2 = {fraction2, fraction1};

        Polynomial polynomial1 = new Polynomial(coefficients1);
        Polynomial polynomial2 = new Polynomial(coefficients2);

        // Обчислюємо суму поліномів і виводимо результат
        Polynomial sum = polynomial1.add(polynomial2);
        System.out.println("Сума поліномів: " + sum);
    }
}
