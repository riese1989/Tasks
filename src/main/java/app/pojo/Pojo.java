package app.pojo;

public interface Pojo<T> {
  T setField(String field, Object value);
}
