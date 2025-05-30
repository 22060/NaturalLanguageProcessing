package nlp;

public class DfCompare implements java.util.Comparator<DfCount> {
    @Override
    public int compare(DfCount wc1, DfCount wc2) {
        if (wc1.getCount() < wc2.getCount())
            return -1;
        if (wc1.getCount() == wc2.getCount())
            return 0;
        if (wc1.getCount() > wc2.getCount())
            return 1;
        return 0;
    }

}
