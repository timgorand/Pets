package exs;

import java.util.Iterator;
/*
Дан итератор. Метод next() возвращает либо String, либо итератор такой же структуры
(то есть который опять возвращает или String, или такой же итератор). Напишите поверх этого итератора другой, уже «плоский».
 */
public class Double_Iterator implements Iterator<String> {
    private Iterator subIter;
    private Double_Iterator newIter;
    public Double_Iterator(Iterator iter){
        this.subIter = iter;
    }
    @Override
    public boolean hasNext() {
        if (subIter.hasNext())return true;
        if (newIter!=null)return true;
        return false;
    }

    @Override
    public String next() {
        return null;
    }
}
