package nlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class DocumentFrequency {
    // DF をカウントするためのデータ格納領域の定義
    ArrayList<DfCount> dfList = new ArrayList<DfCount>();

    // DF の元になる TF を受ける
    TermFrequency tf[];

    DocumentFrequency(TermFrequency[] tf) {
        this.tf = tf;
    }

    public void df(String outputFilename) {
        // TF100 ファイル分について繰り返す
        for (int i = 0; i < tf.length; i++) {

            // TF1 件に含まれる語の分だけ繰り返し
            for (TfCount tfCount : tf[i].list) {
                // DF のリストの中にエントリがあるか調べる
                for (int j = 0; j < dfList.size(); j++) {
                    // エントリがあった場合はカウントを増やす
                    if (dfList.get(j).getWord().equals(tfCount.getWord())) {
                        dfList.get(j).setCount(dfList.get(j).getCount() + 1);
                        break;
                    }
                }
                // リストにエントリが無かったときは，新しい語としてリストに追加する
                DfCount df = new DfCount(tfCount.getWord(), 1);
                dfList.add(df);

            }

        }
        // ソートする
        dfList.sort(new DfCompare());
        // df,idf の結果をファイルに保存する

        try (FileWriter fw = new FileWriter(outputFilename)) {
            fw.write("単語\tDF\tIDF\n");
            for (int j = 0; j < dfList.size(); j++) {
                dfList.get(j)
                        .setIdf(Math.log10((double) tf.length / (double) dfList.get(j).getCount()) + 1.0);
                fw.write(dfList.get(j).getWord().getHyousoukei() + "\t" + dfList.get(j).getCount() + "\t"
                        + String.format("%.10f", dfList.get(j).getIdf()) + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
