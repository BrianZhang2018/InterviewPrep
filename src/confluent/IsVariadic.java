package confluent;

import java.util.*;
/**
 * https://leetcode.com/discuss/interview-question/759611/confluent-senior-software-engineer-phone-interview
 *
 * If a function is marked as isVariadic=true, then the last argument can occur one or more number of times.
 */
public class IsVariadic {
    public static void main(String[] args) {
        FunctionLibrary lib = new FunctionLibrary();
        Set<Function> fns = new HashSet<>();
        fns.add(new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false));
        fns.add(new Function("FuncB", Arrays.asList("String", "Integer"), true));
        fns.add(new Function("FuncC", Arrays.asList("Integer"), true));
        fns.add(new Function("FuncD", Arrays.asList("Integer", "Integer"), true));
        fns.add(new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false));
        fns.add(new Function("FuncF", Arrays.asList("String"), false));
        fns.add(new Function("FuncG", Arrays.asList("Integer"), false));
        lib.register(fns);
/*        System.out.println(lib.findMatches(Arrays.asList("String")));
        System.out.println(lib.findMatches(Arrays.asList("Integer")));*/
        System.out.println(lib.findMatches(Arrays.asList("Integer", "Integer", "Integer", "Integer")));
   /*     System.out.println(lib.findMatches(Arrays.asList("Integer", "Integer", "Integer")));
        System.out.println(lib.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer")));
        System.out.println(lib.findMatches(Arrays.asList("String", "Integer", "Integer")));*/
    }
}

class Function {
    String name;
    List<String> argumentTypes;
    boolean isVariadic;
    Function(String name, List<String> argumentTypes, boolean isVariadic) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.isVariadic = isVariadic;
    }
    @Override
    public String toString() {
        return this.name;
    }
}

class FunctionLibrary {
    Map<String, List<Function>> nonVariadic = new HashMap<>();
    Map<String, List<Function>> variadic = new HashMap<>();
    public void register(Set<Function> functionSet) {
        for (Function f : functionSet) {
            String key = appendArgs(f.argumentTypes, f.argumentTypes.size());
            if (f.isVariadic) {
                variadic.putIfAbsent(key, new LinkedList<>());
                variadic.get(key).add(f);
            } else {
                nonVariadic.putIfAbsent(key, new LinkedList<>());
                nonVariadic.get(key).add(f);
            }
        }
    }

    public List<Function> findMatches(List<String> argumentTypes) {
        List<Function> matches = new ArrayList<>();
        String key = appendArgs(argumentTypes, argumentTypes.size());
        if (nonVariadic.containsKey(key)) {
            matches.addAll(new LinkedList<>(nonVariadic.get(key)));
        }
        if (variadic.containsKey(key)) {
            matches.addAll(new LinkedList<>(variadic.get(key)));
        }
        int count = argumentTypes.size();
        for (int i = argumentTypes.size() - 2; i >= 0; --i) {
            if (argumentTypes.get(i).equals(argumentTypes.get(i + 1))) {
                --count;
            } else {
                break;
            }
            key = appendArgs(argumentTypes, count);
            if (variadic.containsKey(key)) {
                matches.addAll(new LinkedList<>(variadic.get(key)));
            }
        }

        return matches;
    }

    public String appendArgs(List<String> argumentTypes, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            String arg = argumentTypes.get(i);
            sb.append(arg);
            sb.append("+");
        }
        return sb.toString();
    }
}