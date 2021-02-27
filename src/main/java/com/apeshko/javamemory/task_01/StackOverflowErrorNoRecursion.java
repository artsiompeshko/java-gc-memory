package com.apeshko.javamemory.task_01;

import javassist.*;

public class StackOverflowErrorNoRecursion implements ErrorGenerator {
    // constructs method body with a lot of field declarations
    private String getMethodBody(final int numberOfLocalVarsToGenerate) {
        StringBuilder methodBody = new StringBuilder("public static long ")
                .append("generatedMethodName").append("() {")
                .append(System.lineSeparator());
        StringBuilder antiDeadCodeEliminationString = new StringBuilder("long result = i0");
        long i = 0;
        while (i < numberOfLocalVarsToGenerate) {
            methodBody.append("  long i").append(i)
                    .append(" = ").append(i).append(";")
                    .append(System.lineSeparator());
            antiDeadCodeEliminationString.append("+").append("i").append(i);
            i++;
        }
        antiDeadCodeEliminationString.append(";");
        methodBody.append("  ").append(antiDeadCodeEliminationString)
                .append(System.lineSeparator())
                .append("  return result;")
                .append(System.lineSeparator())
                .append("}");

        return methodBody.toString();
    }

    Class<?> createClassWithLotsOfLocalVars(final int numberOfLocalVarsToGenerate) throws CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass generatedClass = pool.makeClass("test");
        CtMethod generatedMethod = CtNewMethod.make(getMethodBody(numberOfLocalVarsToGenerate), generatedClass);
        generatedClass.addMethod(generatedMethod);

        return generatedClass.toClass();
    }

    @Override
    public void generate() {
        try {
            createClassWithLotsOfLocalVars(6_000);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StackOverflowErrorNoRecursion().generate();
    }
}
