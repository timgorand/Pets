package exs;

import java.util.*;

public class Frequency {
    /*
    Постройте частотный словарь букв русского (или английского) алфавита. Опустим проблему выбора и анализа корпуса языка,\
    достаточно будет взять текст небольшой длины).
     */

    public static void main(String[] args) {
        String text = "Решения таких задач я описывать не буду, да и задач на строки тоже можно найти огромное количество";
        Map<Character, int[]> frequency = new TreeMap<>();
        text = text.toLowerCase();
        int sum = 0;
        for(int i = 0; i < text.length() - 1; i++) {
            char ch = text.charAt(i);
            if ((ch >= 'a' && ch <= 'я') || ch == 'ё') {
                // frequency.compute(ch, (character, integer) -> integer == null ? 1 : integer + 1);
                if (frequency.containsKey(ch)) {
                    int[] value = frequency.get(ch);
                    value[0] = value[0] + 1;
                } else {
                    frequency.put(ch, new int[]{1});
                }
                sum++;
            }
        }
        List<Map.Entry<Character, int[]>> list = new ArrayList<>(frequency.entrySet());
        // list.sort(Map.Entry.comparingByKey());
        Map<Character, int[]> final_frenquency = new LinkedHashMap<>();
        for(Map.Entry<Character, int[]> help : list) {
            final_frenquency.put(help.getKey(), help.getValue());
        }

        for(Map.Entry<Character, int[]> characterIntegerEntry : final_frenquency.entrySet()) {
            String x = characterIntegerEntry.toString();
            int value = characterIntegerEntry.getValue()[0];
            double freq = value * 100.0 / sum;
            System.out.println("'%s' | %s | %s%%".formatted(characterIntegerEntry.getKey(), value, freq));
        }

    }

}
