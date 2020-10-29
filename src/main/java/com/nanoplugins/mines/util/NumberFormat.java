package com.nanoplugins.mines.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NumberFormat {

    private final DecimalFormat numberFormat = new DecimalFormat("#,###.##", new DecimalFormatSymbols(new Locale("pt", "BR")));
    private final List<String> chars = Arrays.asList("", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D", "UN", "DD", "TR", "QT", "QN", "SD", "SPD", "OD", "ND", "VG", "UVG", "DVG", "TVG", "QTV");


    public String formatNumber(double number) {
        int index = 0;
        while (number / 1000.0D >= 1.0D) {
            number /= 1000.0D;
            index++;
        }

        String character = index < chars.size() ? chars.get(index) : "";
        return numberFormat.format(number) + character;
    }

}
