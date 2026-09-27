class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before '('
                stack.push(current);

                // Start a new string
                current = new StringBuilder();
            }

            else if (ch == ')') {
                // Reverse the current string
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Add reversed string to it
                previous.append(current);

                current = previous;
            }

            else {
                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}