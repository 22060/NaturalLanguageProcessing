package nlp;

public class NaturalLanguageProcessing {
    static public void main(String args[]) {
        System.out.println("TF 導出");
        TermFrequency[] tf = new TermFrequency[100];
        for (int i = 1; i <= 100; i++) {
            tf[i - 1] = new TermFrequency();
            String inputFileName = "data\\" + String.format("%03d", i) + ".txt";
            String outputFileName = "data\\" + String.format("%03d", i) + "tf.txt";
            tf[i - 1].tf(inputFileName, outputFileName);
        }
        System.out.println("TF 導出完了");
        System.out.println("DF 導出");
        DocumentFrequency df = new DocumentFrequency(tf);
        df.df("data\\df.txt");
        System.out.println("DF 導出完了");
    }
}
