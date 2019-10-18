package com.firozanwar.rxjavabyfiroz.ravitamada;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;


public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators_new);

        /**
         * Observable is created using fromArray() operator which emits the numbers from 1 to 20
         */
        //new FromArrayOperator().run();

        /**
         * Observable is created using Range() operator which emits the numbers from 1 to 20
         */
        //new RangeOperator().run();

        /**
         * Chaining of Operators
         *
         * range(): Range operator generates the numbers from 1 to 20
         * filter(): Filters the numbers by applying a condition onto each number
         * map(): Map transform the data from Integer to String by appending the string at the end
         */
        //new FilterAndMapOperator().run();

        /**
         * Just() operator takes a list of arguments and converts the items into Observable items.
         * It takes arguments between one to ten
         */
        //new JustOperator().run();

        /**
         *  From() creates an Observable from set of items using an Iterable, which means each item is emitted one at a time.
         *  There is also fromCallable(), fromFuture(), fromIterable() and fromPublisher() operators available.
         */
        //new FromOperator().run();

        /**
         * Repeat() creates an Observable that emits an item or series of items repeatedly.
         * You can also pass an argument to limit the number of repetitions.
         */
        //new RepeatOperator().run();

        /**
         * Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time.
         * Use case: Let’s say you want to track number of taps performed in a specified time period,
         * using buffer it can be done very easily.
         */
        //new BufferOperator().run();

        /**
         * Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time.
         *
         * When buffer(3) is used, it emits 3 integers at a time.
         * .buffer(3, TimeUnit.SECONDS)
         */

        /**
         *Debounce operators emits items only when a specified timespan is passed.
         * This operator is very useful when the Observable is rapidly emitting items
         * but you are only interested in receiving them in timely manner.
         *
         * .debounce(300, TimeUnit.MILLISECONDS)
         */

        /**
         * Skip(n) operator skips the emission of first N items emitted by an Observable.
         * Let’s say you have an Observable that emits integers from 1-10 and if skip(4)
         * is operator is used, it skips 1-4 and emits the numbers 5, 6, 7, 8, 9, 10.
         *
         * skipLast(n) skips the last N emissions from an Observable.
         */
        //new TakeOperator().run();

        /**
         * Distinct operator filters out items emitted by an Observable by avoiding duplicate items in the list.
         * The distinct operator works very well with primitive datatypes. But if you want to use it with a custom datatype,
         * you need to override the equals() and hashCode() methods.
         */
        //new DistinctOperator().run();

        /**
         * Max() operator finds the maximum valued item in the Observable sequence and emits that value.
         * .max(observable)
         * MathObservable.max(floatObservable)
         *
         * Min() operator emits the minimum valued item in the Observable data set.
         * .min(observable)
         *
         * Calculates the sum of all the items emitted by an Observable and emits only the Sum value.
         * .sumInteger(observable)
         *
         * Calculates the average of all the items emitted by an Observable and emits only the Average value.
         * .averageInteger(observable), averageFloat(), averageDouble() and averageLong()
         *
         * Counts number of items emitted by an Observable and emits only the count value.
         * .count()
         */

        /**
         * Reduce applies a function on each item and emits the final result. First, it applies a function to first item,
         * takes the result and feeds back to same function on second item. This process continuous until the last emission.
         * Once all the items are over, it emits the final result.
         *
         * Below we have an Observable that emits numbers from 1 to 10. The reduce() operator calculates the
         * sum of all the numbers and emits the final result.
         */

        /**
         * Concat operator combines output of two or more Observables into a single Observable.
         * Concat operator always maintains the sequential execution without interleaving the emissions.
         * So the first Observables completes its emission before the second starts and so forth if there are more observables.
         *
         * Merge also merges multiple Observables into a single Observable but it won’t maintain the sequential execution.
         */
        // new ConcatMergeOperator().run();

        /**
         * In short, Map, FlatMap, ConcatMap and SwitchMap applies a function or modifies the data emitted by an Observable.
         *
         * Map modifies each item emitted by a source Observable and emits the modified item.
         * FlatMap, SwitchMap and ConcatMap also applies a function on each emitted item but instead of returning the modified item,
         * it returns the Observable itself which can emit data again.
         * FlatMap and ConcatMap work is pretty much same. They merges items emitted by multiple Observables and returns a single Observable.
         * The difference between FlatMap and ConcatMap is, the order in which the items are emitted.
         * FlatMap can interleave items while emitting i.e the emitted items order is not maintained.
         * ConcatMap preserves the order of items. But the main disadvantage of ConcatMap is, it has to wait for each Observable to complete its work thus asynchronous is not maintained.
         * SwitchMap is a bit different from FlatMap and ConcatMap. SwitchMap unsubscribe from previous source Observable whenever
         * new item started emitting, thus always emitting the items from current Observable.
         */
        new MapOperators().run();
    }
}
