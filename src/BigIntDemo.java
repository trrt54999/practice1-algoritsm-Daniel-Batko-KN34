import java.util.List;

public class BigIntDemo {
  public static void runAll() {
    System.out.println("— BigInt Demo —\n");

    testConstructor();
    testAddition();
    testSubtraction();
    testComparison();
    testDivision();
    testMultiplication();
    testPower();
    testGCD();
    testLCM();
    testGCDThree();
    testFactorization();
  }

  private static void testConstructor() {
    System.out.println("— Constructor —");
    BigInt num = new BigInt("0000456700");
    System.out.println("Trimmed: 0000456700 -> " + num + "\n");
  }

  private static void testAddition() {
    System.out.println("— Addition —");
    BigInt a = new BigInt("987654129043210");
    BigInt b = new BigInt("123431251567890");
    BigInt sum = a.add(b);
    System.out.println(a + " + " + b + " = " + sum + "\n");
  }

  private static void testSubtraction() {
    System.out.println("— Subtraction —");
    BigInt a = new BigInt("765431232");
    BigInt b = new BigInt("1012000");
    try {
      BigInt diff = a.subtract(b);
      System.out.println(a + " - " + b + " = " + diff + "\n");
    } catch (IllegalArgumentException e) {
      System.out.println("Subtraction error: " + e.getMessage() + " (we keep absolute behavior)\n");
    }
  }

  private static void testComparison() {
    System.out.println("— Comparison —");
    BigInt a = new BigInt("999999");
    BigInt b = new BigInt("123456");
    int cmp = a.compare(b);
    System.out.println("Compare " + a + " and " + b + ": " + cmp + "\n");
  }

  private static void testDivision() {
    System.out.println("— Division —");
    BigInt dividend = new BigInt("5932109452195215192389");
    BigInt divisor = new BigInt("694219832131289412");
    BigInt quotient = dividend.divide(divisor);
    System.out.println(dividend + " / " + divisor + " = " + quotient + "\n");
  }

  private static void testMultiplication() {
    System.out.println("— Multiplication —");
    BigInt a = new BigInt("5116113811863491119");
    BigInt b = new BigInt("23111531221333211131");
    BigInt product = a.multiply(b);
    System.out.println(a + " * " + b + " = " + product + "\n");
  }

  private static void testPower() {
    System.out.println("— Power —");
    BigInt base = new BigInt("21593");
    BigInt result = base.pow(6);
    System.out.println(base + " ^ 6 = " + result + "\n");
  }

  private static void testGCD() {
    System.out.println("— GCD —");
    BigInt a = new BigInt("48");
    BigInt b = new BigInt("180");
    BigInt gcd = a.gcd(b);
    System.out.println("GCD(" + a + ", " + b + ") = " + gcd + "\n");
  }

  private static void testLCM() {
    System.out.println("— LCM —");
    BigInt a = new BigInt("21");
    BigInt b = new BigInt("14");
    BigInt lcm = a.lcm(b);
    System.out.println("LCM(" + a + ", " + b + ") = " + lcm + "\n");
  }

  private static void testGCDThree() {
    System.out.println("— GCD of Three Numbers —");
    BigInt a = new BigInt("48");
    BigInt b = new BigInt("180");
    BigInt c = new BigInt("300");
    BigInt gcd = BigInt.gcd(a, b, c);
    System.out.println("GCD(" + a + ", " + b + ", " + c + ") = " + gcd + "\n");
  }

  private static void testFactorization() {
    System.out.println("— Factorization —");
    BigInt number = new BigInt("360");
    List<BigInt> factors = BigIntFactorizer.factorize(number);
    System.out.println("Factors of " + number + ": " + factors + "\n");
  }
}