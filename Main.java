import java.util.ArrayDeque;

class Main {
  public static void main(String[] args) {
    //Tokenizer.main(args);
    Tokenizer createTokens = new Tokenizer();
    ArrayDeque<Object> list = createTokens.readTokens(args[0]);
    Postfix postfixCalculator = new Postfix(list);
    System.out.println(postfixCalculator.postFixArithmetic());
  }
}
