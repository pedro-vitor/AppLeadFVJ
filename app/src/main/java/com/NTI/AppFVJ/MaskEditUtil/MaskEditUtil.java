package com.NTI.AppFVJ.MaskEditUtil;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

public abstract class MaskEditUtil {
    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(##) #####-####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";

    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }

    public static String[] returnOnlyName(String string) {
        String[] stringsArray = string.trim().split(" ");
        String[] stringResult = new String[2];

        if(stringsArray.length == 1){
            return stringsArray;
        }

        for (int i = 0; i < 2; i++) {
            stringResult[i] = stringsArray[i];
        }

        return stringResult;
    }

    public static String setmask(final String numberstring) {
        char format[] = FORMAT_FONE.toCharArray();
        char number[] = numberstring.toCharArray();
        char result[] = new char[format.length];

        for (int i = 0, j = 0, k = 0; i < format.length; i++, j++)
            if (format[i] == '#') {
                result[j] = number[k];
                k++;
            }
            else
                result[j] = format[i];

        return String.copyValueOf(result);
    }


    public static boolean validEmail(final String email){
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    public static String setDateFormat(String string) {
        String[] stringsArray = string.trim().split("T");
        String[] stringResult = new String[2];
        stringResult[0] = stringsArray[0];

        stringsArray = stringResult[0].split("-");
        return stringsArray[2] +"/"+ stringsArray[1] +"/"+ stringsArray[0];
    }

}