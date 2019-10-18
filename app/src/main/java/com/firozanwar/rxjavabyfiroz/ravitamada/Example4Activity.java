package com.firozanwar.rxjavabyfiroz.ravitamada;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Example4Activity extends AppCompatActivity {

    private static final String TAG = Example4Activity.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);

        DisposableObserver<Note> noteDisposableObserver = getNotesObserver();

        Observable<Note> noteObservable = getNotesObservable();

        compositeDisposable.add(
                noteObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Note, Note>() {

                            @Override
                            public Note apply(Note note) throws Exception {
                                // Making the note to all uppercase
                                note.setNote(note.getNote().toUpperCase());
                                return note;
                            }
                        })
                        .subscribeWith(noteDisposableObserver));
    }

    private Observable<Note> getNotesObservable() {
        final List<Note> notes = prepareNotes();
        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) throws Exception {
                for (Note note : notes) {
                    if (!emitter.isDisposed())
                        emitter.onNext(note);
                }
            }
        });
    }

    private DisposableObserver<Note> getNotesObserver() {
        return new DisposableObserver<Note>() {
            @Override
            public void onNext(Note note) {
                Log.d(TAG, "Note: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All notes are emitted!");
            }
        };
    }

    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "buy tooth paste!"));
        notes.add(new Note(2, "call brother!"));
        notes.add(new Note(3, "watch narcos tonight!"));
        notes.add(new Note(4, "pay power bill!"));

        return notes;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // don't send events once the activity is destroyed
        compositeDisposable.clear();
    }
}
