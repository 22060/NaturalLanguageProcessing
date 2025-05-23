package nlp;

class DfCount extends WordCount {
    private Double idf;

    DfCount(Word word, Integer count) {
        super(word, count);
        this.idf = 0.0;
    }

    DfCount(Word word, Integer count, Double idf) {
        super(word, count);
        this.idf = idf;
    }

    void setIdf(Double idf) {
        this.idf = idf;
    }

    Double getIdf() {
        return idf;
    }
}
