package com.cxy.demo.demojpa.base.streamables;

import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Description: 自定义  </br>
 * Date: 2021/3/31 15:50
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@RequiredArgsConstructor(staticName = "of")
public class Products implements Streamable<Product> {

    private Streamable<Product> streamable;

    public Money getTotal() {

        return streamable.stream() //

                .map(Product::getPrice)

                .reduce(Money.zero(CurrencyUnit.USD), Money::plus);

    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Product> action) {

    }

    @Override
    public Spliterator<Product> spliterator() {
        return null;
    }

    @Override
    public Stream<Product> stream() {
        return null;
    }

    @Override
    public <R> Streamable<R> map(Function<? super Product, ? extends R> mapper) {
        return null;
    }

    @Override
    public <R> Streamable<R> flatMap(Function<? super Product, ? extends Stream<? extends R>> mapper) {
        return null;
    }

    @Override
    public Streamable<Product> filter(Predicate<? super Product> predicate) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Streamable<Product> and(Supplier<? extends Stream<? extends Product>> stream) {
        return null;
    }

    @Override
    public Stream<Product> get() {
        return null;
    }
}

