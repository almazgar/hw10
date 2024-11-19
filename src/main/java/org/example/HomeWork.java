package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/contest/356/problem/A
     */
    @SneakyThrows
    public void championship(InputStream in, OutputStream out) {

        String input = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        List<String> inList = Arrays.stream(input.split("\n"))
                .collect(Collectors.toList());
        String firstLine = inList.get(0);
        List<Integer> firstLineListInt = Arrays.stream(firstLine.split(" "))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        int n = firstLineListInt.get(0);
        if (n < 2 || n > 300000) {
            new IllegalArgumentException("Не корректное количество рыцарей");
        }
        int m = firstLineListInt.get(1);
        if (m < 1 || m > 300000) {
            new IllegalArgumentException("Не корректное число сражений");
        }
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i ++){
            result[i] = i;
        }
        // обрабатываем сражения
        for (int i = 1; i <= m; i++) {
            List<Integer> fight = Arrays.stream(inList.get(i).split(" "))
                    .map(e -> Integer.parseInt(e))
                    .collect(Collectors.toList());
            fillResult(fight, result);
        }
        // последний выигравший - пишем 0
        List<Integer> fight = Arrays.stream(inList.get(m).split(" "))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        result[fight.get(2)] = 0;
        // вывод результата, скипаем 0 индекс
        String resultString = Arrays.stream(result).boxed().skip(1).map(String::valueOf).collect(Collectors.joining(" "));
        out.write(resultString.getBytes(StandardCharsets.UTF_8));
    }

    private void fillResult(List<Integer> fight, int[] knigt) {
        for (int j = fight.get(0); j <= fight.get(1); j++){
            // пишем победителя если ранее не записан
            if (j == knigt[j]){
                knigt[j] = fight.get(2);
            }
        }
    }


}
