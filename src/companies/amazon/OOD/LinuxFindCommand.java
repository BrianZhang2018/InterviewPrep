package companies.amazon.OOD;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/369272/Amazon-or-Onsite-or-Linux-Find-Command
 *
 * Created by brianzhang on 5/31/20.
 */
public class LinuxFindCommand {

    class FindCommand {

        public List<File> findWithFilters(File directory, List<Filter> filters) {
            if (!directory.isDirectory) {
                return null;  // NotADirectoryException
            }
            List<File> output = new ArrayList<>();
            findWithFilters(directory, filters, output);
            return output;
        }

        private void findWithFilters(File directory, List<Filter> filters, List<File> output) {
            if (directory.children == null) {
                return;
            }
            for (File file : directory.children) {
                if (file.isDirectory) {
                    findWithFilters(file, filters, output);
                } else {
                    boolean selectFile = true;
                    for (Filter filter : filters) {
                        if (!filter.apply(file)) {
                            selectFile = false;
                        }
                    }
                    if (selectFile) {
                        output.add(file);
                    }
                }
            }
        }
    }

    class File {
        String name;
        int size;
        int type;
        boolean isDirectory;
        File[] children;
    }

    interface Filter {
        boolean apply(File file);
    }

    class MinSizeFilter implements Filter {

        int minSize;

        public MinSizeFilter(int minSize) {
            this.minSize = minSize;
        }

        public boolean apply(File file) {
            return file.size > minSize;
        }
    }

    class TypeFilter implements Filter {

        int type;

        public TypeFilter(int type) {
            this.type = type;
        }

        public boolean apply(File file) {
            return file.type == type;
        }
    }
}
