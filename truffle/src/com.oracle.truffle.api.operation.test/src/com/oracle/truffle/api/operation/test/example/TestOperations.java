package com.oracle.truffle.api.operation.test.example;

import java.util.List;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.operation.GenerateOperations;
import com.oracle.truffle.api.operation.Operation;
import com.oracle.truffle.api.operation.Variadic;

@GenerateOperations
public class TestOperations {

    @Operation
    static class AddOperation {
        // @Cached(inline = true) MyOtherNode node;

        @Specialization
        public static long add(long lhs, long rhs) {
            return lhs + rhs;
        }

        @Specialization
        public static String addStrings(String lhs, String rhs, @Cached("1") int test) {
            return lhs + rhs;
        }
    }

    @Operation
    static class LessThanOperation {
        @Specialization
        public static boolean lessThan(long lhs, long rhs) {
            return lhs < rhs;
        }
    }

    @Operation
    static class VeryComplexOperation {
        @Specialization
        public static long bla(long a1, @Variadic Object[] a2) {
            return a1 + a2.length;
        }
    }

    @Operation
    static class AddToListOperation {
        @Specialization
        public static void bla(List a1, Object a2) {
            a1.add(a2);
        }
    }

    @Operation
    static class ThrowOperation {
        @Specialization
        public static void perform() {
            throw new AbstractTruffleException("haha") {
            };
        }
    }

    @Operation
    static class IsThruthyOperation {
        @Specialization
        public static boolean testString(String s) {
            return s != null && s.length() > 0;
        }

        @Specialization
        public static boolean testLong(long l) {
            return l > 0;
        }
    }

    @Operation
    static class IsFalseyOperation {
        @Specialization
        public static boolean testString(String s) {
            return s == null || s.length() == 0;
        }

        @Specialization
        public static boolean testLong(long l) {
            return l <= 0;
        }
    }
}