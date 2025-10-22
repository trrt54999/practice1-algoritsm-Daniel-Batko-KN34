public class Main {
  public static void main(String[] args) {
    BigIntDemo.runAll();

    BigInt a = new BigInt("36");
    BigInt b = new BigInt("48");
    BigInt c = new BigInt("60");

    BigInt gcd = a.gcd(b);
    BigInt lcm = a.lcm(b);
    BigInt gcdThree = BigInt.gcd(a, b, c);

    System.out.println("gcd(" + a + ", " + b + ") = " + gcd);
    System.out.println("lcm(" + a + ", " + b + ") = " + lcm);
    System.out.println("gcd(" + a + ", " + b + ", " + c + ") = " + gcdThree);

    System.out.println("Factors of " + a + ": " + BigIntFactorizer.factorize(a));
  }
}
