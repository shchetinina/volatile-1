package ru.netology;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class PalindromChecker {
    public static boolean checkPalindrom(String text){
        boolean isPalindrom = true;
        List<Character> characters = convertStringToCharacterList(text);
        ListIterator<Character> charactersIterator = characters.listIterator();
        ListIterator<Character> reversedCharacterIterator = characters.listIterator(characters.size());
        while (charactersIterator.hasNext() && reversedCharacterIterator.hasPrevious()){
            if (charactersIterator.next() != reversedCharacterIterator.previous()){
                isPalindrom = false;
                break;
            }
        }
        return isPalindrom;
    }

    private static List<Character> convertStringToCharacterList(String text){
        List<Character> chars = new LinkedList<>();
        for (char character: text.toCharArray()){
            chars.add(character);
        }
        return chars;
    }
}
