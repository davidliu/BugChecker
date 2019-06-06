package com.evie.bugchecker;

import com.evie.bugchecker.annotations.PropKey;
import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.code.Type;

import java.util.List;

import static com.google.errorprone.BugPattern.SeverityLevel.*;

/**
 * Bug checker to detect usage of {@code return null;}.
 */
@AutoService(BugChecker.class)
@BugPattern(
        name = "CheckHasAnnotation",
        summary = "Requires EventKey annotation.",
        severity = ERROR)
public class CheckHasAnnotation extends BugChecker implements BugChecker.MethodInvocationTreeMatcher {

    private static final String ANNOTATION_EVENT_KEY_NAME = PropKey.class.getName();

    @Override
    public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree, VisitorState state) {
        // Check if there are even any arguments to check.
        List<? extends ExpressionTree> arguments = methodInvocationTree.getArguments();
        System.out.println("arguments: " + arguments);
        if (arguments.isEmpty()) {
            return Description.NO_MATCH;
        }

        // The unbound MethodSymbol for bar(), with type parameters <A> and <B>
        MethodSymbol declaredMethod = ASTHelpers.getSymbol(methodInvocationTree);
        System.out.println("  declaredMethod: " + declaredMethod);
        if (declaredMethod == null) {
            return Description.NO_MATCH;
        }

        // A Type substitution capturing <Integer> on Foo and <String> on bar(Object,Object);
        Type calledMethodType = ASTHelpers.getType(methodInvocationTree.getMethodSelect());
        Type calledClazzType = ASTHelpers.getReceiverType(methodInvocationTree.getMethodSelect());
        System.out.println("    calledMethodType: " + calledMethodType);
        System.out.println("    calledClazzType: " + calledClazzType);
        if (calledMethodType == null) {
            return Description.NO_MATCH;
        }

        List<Type> params = calledMethodType.getParameterTypes();
        System.out.println("      params: " + params);

        List<? extends ExpressionTree> args = methodInvocationTree.getArguments();
        for (int i = 0; i < params.size(); i++) {
            Type param = params.get(i);
            List<Attribute.TypeCompound> annotations = param.getAnnotationMirrors();

            for (Attribute.TypeCompound annotation : annotations) {
                if (annotation.type.toString().equals(ANNOTATION_EVENT_KEY_NAME)) {
                    if (!ASTHelpers.hasAnnotation(ASTHelpers.getSymbol(args.get(i)), PropKey.class, state)) {
                        return describeMatch(methodInvocationTree);
                    }
                }
            }
        }
        return Description.NO_MATCH;
    }
}