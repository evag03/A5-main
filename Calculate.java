import java.util.ArrayDeque;

public class Calculate {
    public ArrayDeque<Object> list;

    public Calculate(ArrayDeque<Object> list) {
        this.list = list;
    }

    public int getPrec(Object operator) {
        int precedent = 0;
        if (operator.equals('+') || operator.equals('-')) {
            precedent = 1;
        } 
        else if (operator.equals('*') || operator.equals('/')) {
            precedent = 2;
        }
        else if (operator.equals('^')) {
            precedent = 3;
        }
        return precedent;
    }

    public double calculateResult() {
        ArrayDeque<Object> stack = new ArrayDeque<>();
        ArrayDeque<Object> outputQueue = new ArrayDeque<>();
        
        while (list.isEmpty() == false) {
            Object selectedToken = list.removeFirst();

            if (selectedToken instanceof Double) {
                outputQueue.addLast(selectedToken);
                System.out.println("Currently on queue " + outputQueue.toString());
            }
            else if (selectedToken instanceof Character) {
                if (stack.isEmpty() == false && stack.peek() instanceof Character) {
                    int firstPrecedent = getPrec(stack.peek());
                    int objPrecendence = getPrec(selectedToken);
                    if (firstPrecedent >= objPrecendence) {
                        outputQueue.addLast(stack.pop());
                    }
                    stack.push(selectedToken);
                        outputQueue.addLast(stack.pop());
                }
                else {
                    stack.push(selectedToken);
                }
            }
            //uhh it may register the () as characters, just trying to semi psuedo-code this in.
            //Hey bc this is like psuedo-code, the paranthesis, again, may register as characters so maybe you gotta put this in the conditional that checks if the ( ) are characters/symbols.
            else if (selectedToken.equals('(')) {
                stack.push('(');
            }
            else if (selectedToken.equals(')')) {
                //Until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue.
                while (stack.peekLast().equals('(') && stack.isEmpty() == false) {
                    outputQueue.addLast(stack.pop());

                }

                if (stack.peek().equals('(')) { 
                    stack.pop();
                    //why is this here? we all don't really know
                    //technically it may not be a String might be a Character
                    if (stack.isEmpty() == false && stack.peek() instanceof String) {
                        outputQueue.addLast(stack.pop());
                    }

                }
                
                if (stack.isEmpty() == true) {
                    //no left parenthesis exists then...the person inputting the expression messed up and forgot a matching paranthesis
                    throw new RuntimeException("Missing matching parenthesis.");
                }
                //Pop the left parenthesis from the stack, but not onto the output queue.
                //If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
            }
        }
        
        
        double result = (Double)2.0;
        return result;
    }
}
