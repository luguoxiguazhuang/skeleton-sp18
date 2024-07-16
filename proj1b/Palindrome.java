public class Palindrome {

    /** change word to deque containing character*/
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> myDeque = new ArrayDeque<Character>();
        for(int i = 0;i < word.length();i+=1){
            myDeque.addLast(word.charAt(i));
        }
        return myDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> myDeque = wordToDeque(word);
        return dequeIsPalindrome(myDeque);
    }

    private boolean dequeIsPalindrome(Deque<Character> myDeque) {
        if(myDeque.size() == 0 || myDeque.size() == 1) {
            return true;
        }
        if(myDeque.removeFirst() != myDeque.removeLast()) {
            return false;
        }
        return dequeIsPalindrome(myDeque);
    }

    public boolean isPalindrome(String word,CharacterComparator cc) {
        Deque<Character> myDeque = wordToDeque(word);
        return dequeIsPalindrome(myDeque,cc);
    }

    private boolean dequeIsPalindrome(Deque<Character> myDeque,CharacterComparator cc) {
        if(myDeque.size() == 0 || myDeque.size() == 1) {
            return true;
        }
        if(!cc.equalChars(myDeque.removeFirst() , myDeque.removeLast()))  {
            return false;
        }
        return dequeIsPalindrome(myDeque,cc);
    }
}