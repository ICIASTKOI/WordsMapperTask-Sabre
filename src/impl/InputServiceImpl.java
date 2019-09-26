package impl;

import service.InputService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputServiceImpl implements InputService<String> {
    @Override

        public Set<String> getOnlyValidInput(String... input) {
            List<String> list = new ArrayList<>();
            Arrays.asList(input).stream().filter(x -> notNull(x)).forEach(x -> list.addAll(Arrays.asList(x.split("[$&+,:;=?@#|'<>.^*()%! -]"))));
            return Stream.of(list.toArray()).map(x -> x.toString().trim().toLowerCase()).collect(Collectors.toSet());
    }

    private Boolean notNull(Object o) {
        return !(o == null);
    }

}
