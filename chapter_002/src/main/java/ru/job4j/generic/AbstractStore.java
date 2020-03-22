package ru.job4j.generic;

import java.util.Iterator;

public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> array;

    public AbstractStore(int size) {
        this.array = new SimpleArray<>(size);
    }

    public void add(T model) {
        this.array.add(model);
    }

    public boolean replace(String id, T model) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            this.array.set(index, model);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            this.array.remove(index);
            result = true;
        }
        return result;
    }

    public T findById(String id) {
        int index = getIndex(id);
        if (index >= 0) {
            return (T) array.get(index);
        }
        return null;
    }

    public int getIndex(String id) {
        Iterator<T> iterator = this.array.iterator();
        int result = -1;
        int index = 0;
        while (iterator.hasNext()) {
            T model = iterator.next();
            if (model.getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

}
