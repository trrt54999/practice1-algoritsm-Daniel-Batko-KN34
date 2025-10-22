public class BigInt {
  private final String value;

  public BigInt(String val) {
    this.value = normalize(val);
  }

  @Override
  public String toString() {
    return this.value;
  }
  // Нормалізує вхідний рядок: null/"" -> "0"; видаляє првоідні нулі,
  private static String normalize(String val) {
    if (val == null || val.isEmpty()) return "0";
    int i = 0;
    while (i < val.length() - 1 && val.charAt(i) == '0') i++;
    return val.substring(i);
  }

  public boolean isZero() {
    return "0".equals(this.value);
  }

  public boolean isOne() {
    return "1".equals(this.value);
  }
  // додавання чисел
  public BigInt add(BigInt other) {
    String val1 = this.value;
    String val2 = other.value;
    StringBuilder result = new StringBuilder();
    int carry = 0;
    int i = val1.length() - 1;
    int j = val2.length() - 1;

    while (i >= 0 || j >= 0 || carry > 0) {
      int digit1 = i >= 0 ? val1.charAt(i) - '0' : 0;
      int digit2 = j >= 0 ? val2.charAt(j) - '0' : 0;
      int sum = digit1 + digit2 + carry;
      carry = sum / 10;
      result.insert(0, sum % 10);
      i--; j--;
    }
    return new BigInt(result.toString());
  }
  // віднімання, негативного не може бути
  public BigInt subtract(BigInt other) {
    if (this.compare(other) < 0) {
      throw new IllegalArgumentException("Negative result not supported");
    }

    StringBuilder result = new StringBuilder();
    int borrow = 0;
    int i = this.value.length() - 1;
    int j = other.value.length() - 1;

    while (i >= 0) {
      int digit1 = this.value.charAt(i) - '0' - borrow;
      int digit2 = j >= 0 ? other.value.charAt(j) - '0' : 0;
      if (digit1 < digit2) {
        digit1 += 10;
        borrow = 1;
      } else borrow = 0;
      result.insert(0, digit1 - digit2);
      i--; j--;
    }

    return new BigInt(result.toString());
  }
  // порівняння
  public int compare(BigInt other) {
    if (this.value.length() != other.value.length()) {
      return Integer.compare(this.value.length(), other.value.length());
    }
    return this.value.compareTo(other.value);
  }
// множення
  public BigInt multiply(BigInt other) {
    if (this.isZero() || other.isZero()) return new BigInt("0");

    int len1 = this.value.length();
    int len2 = other.value.length();
    int[] product = new int[len1 + len2];

    for (int i = len1 - 1; i >= 0; i--) {
      int digit1 = this.value.charAt(i) - '0';
      for (int j = len2 - 1; j >= 0; j--) {
        int digit2 = other.value.charAt(j) - '0';
        int sum = digit1 * digit2 + product[i + j + 1];
        product[i + j + 1] = sum % 10;
        product[i + j] += sum / 10;
      }
    }

    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index < product.length && product[index] == 0) index++;
    for (; index < product.length; index++) result.append(product[index]);

    return new BigInt(result.length() == 0 ? "0" : result.toString());
  }
  // ділення
  public BigInt divide(BigInt divisor) {
    if (divisor.isZero()) throw new ArithmeticException("Division by zero");

    BigInt dividend = new BigInt(this.value);
    if (dividend.compare(divisor) < 0) return new BigInt("0");
    if (divisor.isOne()) return dividend;

    StringBuilder quotient = new StringBuilder();
    StringBuilder current = new StringBuilder();

    for (int idx = 0; idx < dividend.value.length(); idx++) {
      current.append(dividend.value.charAt(idx));
      BigInt currentValue = new BigInt(current.toString());
      int q = 0;
      while (currentValue.compare(divisor) >= 0) {
        currentValue = currentValue.subtract(divisor);
        q++;
      }
      quotient.append(q);
      current = new StringBuilder(currentValue.toString());
    }

    String result = quotient.toString().replaceFirst("^0+", "");
    return new BigInt(result.isEmpty() ? "0" : result);
  }
// залишок від ділення
  public BigInt mod(BigInt divisor) {
    if (divisor.isZero()) throw new ArithmeticException("Division by zero");
    if (this.compare(divisor) < 0) return new BigInt(this.value);

    StringBuilder current = new StringBuilder();
    for (int idx = 0; idx < this.value.length(); idx++) {
      current.append(this.value.charAt(idx));
      BigInt currentValue = new BigInt(current.toString());
      while (currentValue.compare(divisor) >= 0) {
        currentValue = currentValue.subtract(divisor);
      }
      current = new StringBuilder(currentValue.toString());
    }

    return current.length() == 0 ? new BigInt("0") : new BigInt(current.toString());
  }
// степінь
  public BigInt pow(int exponent) {
    if (exponent < 0) throw new IllegalArgumentException("Negative exponent not supported");
    if (exponent == 0) return new BigInt("1");

    BigInt result = new BigInt("1");
    BigInt base = new BigInt(this.value);
    int exp = exponent;

    while (exp > 0) {
      if ((exp & 1) == 1) result = result.multiply(base);
      exp >>= 1;
      if (exp > 0) base = base.multiply(base);
    }

    return result;
  }
  // Евклідов алгоритм: використовує операцію mod до тих пір, поки b != 0
  // саме gcd на укр НСД(Найбільший спільний дільник)
  public BigInt gcd(BigInt other) {
    BigInt a = new BigInt(this.value);
    BigInt b = new BigInt(other.value);

    while (!b.isZero()) {
      BigInt temp = b;
      b = a.mod(b);
      a = temp;
    }

    return a;
  }
  // LCM = (this / gcd) * other. Якщо один із аргументів нуль — повертаємо "0"
  // саме lcm це на укр НСК, найменше спільне кратне
  public BigInt lcm(BigInt other) {
    if (this.isZero() || other.isZero()) return new BigInt("0");
    BigInt gcdValue = this.gcd(other);
    return this.divide(gcdValue).multiply(other);
  }
// НСД 3х ччисел
  public static BigInt gcd(BigInt a, BigInt b, BigInt c) {
    return a.gcd(b).gcd(c);
  }
}
