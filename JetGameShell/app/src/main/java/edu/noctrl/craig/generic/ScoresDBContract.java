package edu.noctrl.craig.generic;

import android.provider.BaseColumns;

/**
 * Created by bacraig on 5/25/2016.
 */
public final class ScoresDBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ScoresDBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class QuizDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "HiScores";
        public static final String COLUMN_NAME_GAME = "game";
        public static final String COLUMN_NAME_NAME = "student";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_DATE = "date";
    }
}
