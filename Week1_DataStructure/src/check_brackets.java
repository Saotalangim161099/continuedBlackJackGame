import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            // If we encounter an OB, we push it into the stack
            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position + 1));
            }

            // If it is a CB, we need to pop the stack to compare OB and CB
            if (next == ')' || next == ']' || next == '}') {

                // If the stack is not empty, we pop the stack to compare OB and CB
                if (!opening_brackets_stack.isEmpty()) {
                    Bracket comparedOB = opening_brackets_stack.pop();

                    // Compare OB and CB
                    if (!comparedOB.Match(next)) {
                        // If matched, continue. Otherwise, return position +1
                        System.out.println(position + 1);
                        return;
                    }
                } else {
                    System.out.println(position + 1);
                    return;
                }
            }
        }


        // After traversing the text, but the stack is not empty, then print out position+1
        if (!opening_brackets_stack.isEmpty()) {
            System.out.println(opening_brackets_stack.get(0).getPosition());
        } else {
            System.out.println("Success");
        }

        // Printing answer, write your code here
    }
}
