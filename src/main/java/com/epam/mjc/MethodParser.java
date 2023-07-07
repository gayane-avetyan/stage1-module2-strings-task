package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String[] splited = signatureString.split("[ (,)]");
        List<String> list = new ArrayList<>();

        if (!splited[0].equals("private") && !splited[0].equals("public") && !splited[0].equals("protected"))
            list.add(null);

        for (String el : splited) {
            if (!el.isEmpty())
                list.add(el);
        }

        String accessModifier = list.get(0);
        String returnType = list.get(1);
        String methodName = list.get(2);

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        for (int i = 3; i < list.size() - 1; i += 2) {
            MethodSignature.Argument argument =
                    new MethodSignature.Argument(list.get(i), list.get(i + 1));
            arguments.add(argument);
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
