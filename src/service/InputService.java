package service;

import java.util.Collection;


public interface InputService<T> {
   Collection<T> getOnlyValidInput(String... input);

}
