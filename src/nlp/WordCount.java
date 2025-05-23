package nlp;

class WordCount {
    private Word word;// 形態素解析した語の結果を格納する
    private Integer count;// 出現回数を計数するためのカウンタ

    WordCount(Word word, Integer count) {
        this.word = word;
        this.count = count;
    }

    Word getWord() {
        return word;
    }

    void setWord(Word word) {
        this.word = word;
    }

    Integer getCount() {
        return count;
    }

    void setCount(Integer count) {
        this.count = count;
    }
}