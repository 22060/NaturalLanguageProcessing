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
        System.out.println("DF 導出!");
        String inputFileName;
        // TF100 ファイル分について繰り返す
        for (int i = 0; i < 100; i++) {
            // TF1 件に含まれる語の分だけ繰り返し
            inputFileName = "data\\" + String.format("%03d", i + 1) + "tf.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));) {
                String str;
                while ((str = reader.readLine()) != null) {
                    if (str.equals("単語\t出現回数\tTF")) {
                        continue;
                    } else {
                        // DF のリストの中にエントリがあるか調べる
                        for (int j = 0; j < dfList.size(); j++) {
                            // エントリがあった場合はカウントを増やす
                            if (dfList.get(j).getWord().getHyousoukei()
                                    .equals(str.split("[,\t]")[0])) {
                                dfList.get(j).setCount(dfList.get(j).getCount() + 1);
                                // System.out.println("DF: " + dfList.get(j).getWord().getHyousoukei()
                                // + " のカウントを増やす: " + dfList.get(j).getCount());
                                break;
                            }
                        }
                        // リストにエントリが無かったときは，新しい語としてリストに追加する
                        Word wo = new Word();
                        wo.setHyousoukei(str.split("[,\t]")[0]);
                        wo.setHinshi("名詞");
                        DfCount df = new DfCount(wo, 1);
                        dfList.add(df);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // ソートする
        // df,idf の結果をファイルに保存する
        // 総数を調べる

        dfList.sort(new DfCompare());
        try (FileWriter fw = new FileWriter(outputFilename)) {
            fw.write("単語\tDF\tIDF\n");
            for (int j = 0; j < dfList.size(); j++) {
                // System.out.println(dfList.get(j).getCount());
                // System.out.println(dfList.get(j).getWord().getHyousoukei());
                // + list.get(i).getCount());
                // dfList.get(i).setDf((double) dfList.get(i).getCount() / (double) tf.length);
                dfList.get(j)
                        .setIdf(Math.log10((double) 100 / (double) dfList.get(j).getCount()) + 1.0);
                fw.write(dfList.get(j).getWord().getHyousoukei() + "\t" + dfList.get(j).getCount() + "\t"
                        + String.format("%.10f", dfList.get(j).getIdf()) + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
