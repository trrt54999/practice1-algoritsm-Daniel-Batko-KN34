import java.util.ArrayList;
import java.util.List;

public final class BigIntFactorizer {

  private BigIntFactorizer() {}

  public static List<BigInt> factorize(BigInt number) {
    if (number == null) {
      throw new IllegalArgumentException("Number is null");
    }
    if (number.isZero()) {
      throw new IllegalArgumentException("Factorization for zero is undefined");
    }

    BigInt n = new BigInt(number.toString());
    List<BigInt> factors = new ArrayList<>();

    BigInt two = new BigInt("2");
    while (!n.isOne() && n.mod(two).isZero()) {
      factors.add(two);
      n = n.divide(two);
    }

    BigInt candidate = new BigInt("3");
    BigInt step = new BigInt("2");

    while (candidate.multiply(candidate).compare(n) <= 0) {
      while (n.mod(candidate).isZero()) {
        factors.add(candidate);
        n = n.divide(candidate);
      }
      candidate = candidate.add(step);
    }

    if (!n.isOne()) {
      factors.add(n);
    }

    return factors;
  }
}
