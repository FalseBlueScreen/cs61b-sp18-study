public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> newDeque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            newDeque.addLast(word.charAt(i));
        }
        return newDeque;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque =  wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    private boolean isPalindrome(Deque<Character> wordDeque) {
        /* wordDeque.isEmpty() is wrong. When we compare wordDeque.removeFirst and wordDeque.removeLast,
        removeLast will return null because there is nothing left after removeFirst when size is 1.
         */
        if (wordDeque.size() <= 1) {
            return true;
        } else if (wordDeque.removeFirst() == wordDeque.removeLast()) {
            return isPalindrome(wordDeque);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }

    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc){
        if (wordDeque.size() <= 1){
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();

        if (cc.equalChars(first, last)){
            return isPalindrome(wordDeque, cc);
        }
        return false;
    }
}
