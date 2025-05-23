package nlp;

import java.util.Comparator;

class WordCompare implements Comparator<WordCount> {
    @Override
    public int compare(WordCount wc1, WordCount wc2) {
        if (wc1.getCount() < wc2.getCount())
            return 1;
        if (wc1.getCount() == wc2.getCount())
            return 0;
        if (wc1.getCount() > wc2.getCount())
            return -1;
        return 0;
    }
}