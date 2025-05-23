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
    ArrayList<TfCount> tfList = new ArrayList<TfCount>();

    // DF の元になる TF を受ける
    TermFrequency tf[];

    DocumentFrequency(TermFrequency[] tf) {
        this.tf = tf;
    }

    public void df(String outputFilename) {
        System.out.println("DF 導出!");
        String inputFileName;
        // TF100 ファイル分について繰り返す
        for (int i = 0; i < 1; i++) {
            // TF1 件に含まれる語の分だけ繰り返し
            inputFileName = "data\\" + String.format("%03d", i + 1) + "tf.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));) {
                String str;
                List<String> lines = new ArrayList<>();
                while ((str = reader.readLine()) != null) {
                    if (str.equals("単語\t出現回数\tTF")) {
                        continue;
                    }
                    lines.add(str.split("[,\t]")[0]);
                    // System.out.println(str);
                    // DF のリストの中にエントリがあるか調べる
                    for (int j = 0; j < dfList.size(); j++) {
                        // エントリがあった場合はカウントを増やす
                        if (dfList.get(j).getWord().equals(str.split("[,\t]")[0])) {
                            dfList.get(j).setCount(dfList.get(j).getCount() + 1);
                            tfList.get(i).setCount(tfList.get(i).getCount() + 1);
                            break;
                        }
                    }
                    // リストにエントリが無かったときは，新しい語としてリストに追加する
                    Word wo = new Word();
                    wo.setHyousoukei(str.split("[,\t]")[0]);
                    wo.setHinshi("名詞");
                    DfCount df = new DfCount(wo, 1);
                    dfList.add(df);
                    double value = Double.parseDouble(str.split("[,\t]")[2]);
                    Integer count = Integer.parseInt(str.split("[,\t]")[1]);
                    TfCount tf2 = new TfCount(wo, count, value); // Removed the third argument
                    tfList.add(tf2);
                    // 第三引数にStringをDoubleに変換して代入して
                    // 例: str.split("[,\t]")[1] を Double に変換
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ソートする
            dfList.sort(new DfCompare());
            // df,idf の結果をファイルに保存する
            outputFilename = "data\\" + String.format("%03d", i + 1) + "df.txt";
            try (FileWriter fw = new FileWriter(outputFilename)) {
                // System.out.println(outputFilename);
                fw.write("単語\tDF\tIDF\n");
                for (int j = 0; j < dfList.size(); j++) {
                    // System.out.println(dfList.get(j).getWord().getHyousoukei());
                    // + list.get(i).getCount());
                    // dfList.get(i).setDf((double) dfList.get(i).getCount() / (double) tf.length);
                    dfList.get(j)
                            .setIdf(Math.log10((double) tfList.get(j).getCount() / (double) dfList.get(j).getCount()));
                    fw.write(dfList.get(j).getWord().getHyousoukei() + "\t" + tfList.get(j).getTf() + "\t"
                            + String.format("%.10f", dfList.get(j).getIdf()) + "\n");
                }
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
