package com.hache.javatutorial.conceptos.strings;

import lombok.extern.slf4j.Slf4j;

import java.text.Collator;

@Slf4j
public class CompareStringTest {

    public static void main(String[] arg) {
        String str1 = "Hola mundo";
        String str2 = "Hola mundo";
        String str3 = new String("Hola mundo");

        /** == **/
        log.info("Operador ==");
        log.info(">>> Str1 == str2 ? {}", str1 == str2);
        log.info(">>> Str1 == str3 ? {}", str1 == str3);

        /** Equals **/
        log.info("Operador Equals");
        log.info(">>> Str1.equals(str3) ? {}", str1.equals(str3));
        log.info(">>> Str1.equals(\"Hóla mundo\") ? {}", str1.equals("Hóla mundo"));

        /** compareTo **/
        log.info("Operador compareTo");
        log.info(">>> Str1.compareTo(str3) == 0 ? {}", str1.compareTo(str3) == 0);
        log.info(">>> Str1.compareTo(\"Hóla mundo\") ? {}", str1.compareTo("Hóla mundo"));
        log.info(">>> Str1.compareTo(\"Hola\") ? {}", str1.compareTo("Hola"));

        /** collator **/
        log.info("Operador collator");
        Collator comparator = Collator.getInstance();

        log.info(">>> comparator.equals(str1,str2) ? {}", comparator.equals(str1,str2));
        log.info(">>> comparator.equals(str1,str3) ? {}", comparator.equals(str1,str3));
        log.info(">>> comparator.equals(str1,\"hola mundo\") ? {}", comparator.equals(str1,"hola mundo"));
        log.info(">>> comparator.compare(str1,\"hola mundo\") ? {}", comparator.compare(str1,"hola mundo"));

        log.info("Operador collator - Collator.PRIMARY");
        // Para no distinguir entre mayusculas, minusculas y letras con acentos.
        comparator.setStrength(Collator.PRIMARY);
        log.info(">>> comparator.compare(str1,str3) ? {}", comparator.compare(str1,str3));
        log.info(">>> comparator.compare(str1,\"Hóla mundo\") ? {}", comparator.compare(str1,"Hóla mundo"));
        log.info(">>> comparator.compare(str1,\"hola mundo\") ? {}", comparator.compare(str1,"hola mundo"));
        log.info(">>> comparator.compare(str1,\"Hóla\") ? {}", comparator.compare(str1,"Hóla"));

        // Distinguir entre mayusculas, minusculas y pero NO letras con acentos.
        log.info("Operador collator - Collator.SECONDARY");
        comparator.setStrength(Collator.SECONDARY);
        log.info(">>> comparator.compare(str1,\"Hola mundo\") ? {}", comparator.compare(str1,"Hola mundo"));
        log.info(">>> comparator.compare(str1,\"hola mundo\") ? {}", comparator.compare(str1,"hola mundo"));
        log.info(">>> comparator.compare(str1,\"Hóla mundo\") ? {}", comparator.compare(str1,"Hóla mundo"));

        log.info("Operador collator - Collator.TERTIARY");
        // Distinguir entre mayusculas, minusculas y tambien letras con acentos.
        comparator.setStrength(Collator.TERTIARY);
        log.info(">>> comparator.compare(str1,\"Hóla mundo\") ? {}", comparator.compare(str1,"Hóla mundo"));
        log.info(">>> comparator.compare(str1,\"Hóla\") ? {}", comparator.compare(str1,"Hóla"));
    }
}
