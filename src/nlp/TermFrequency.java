package nlp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TermFrequency {
    ArrayList<TfCount> list = new ArrayList<TfCount>();

    // public static void main(String[] args) {
    public void tf(String inputFilename, String outputFilename) {
        // 解析対象のファイル名
        // String inputFilename = "data\\001.txt";
        // mecab コマンドで対象ファイルを解析するコマンド文
        String[] command = { "cmd.exe", "/C", "mecab", inputFilename };
        int sum = 0;
        try {
            // 解析するコマンドを実行する
            Process ps = Runtime.getRuntime().exec(command);
            // 解析した結果を表示するためのオブジェクトに変換する
            BufferedReader bReader_i = new BufferedReader(new InputStreamReader(ps.getInputStream(), "SJIS"));
            // 標準出力を 1 行ずつ受け取る一時オブジェクト
            String targetLine;
            while (true) {
                // 形態素解析結果を 1 行ずつ受け取る
                targetLine = bReader_i.readLine();
                if (targetLine == null) {
                    // 最終行になったら終わる
                    break;
                } else if (targetLine.equals("EOS")) {
                    continue;
                } else {
                    // 行ごとに結果を表示する
                    String targetArray[] = targetLine.split("[,\t]");
                    Word wo = new Word();
                    if (targetArray.length > 0) {
                        wo.setHyousoukei(targetArray[0]);
                    }
                    if (targetArray.length > 1) {
                        wo.setHinshi(targetArray[1]);
                    }
                    if (targetArray.length > 2) {
                        wo.setHinshi1(targetArray[2]);
                    }
                    if (targetArray.length > 3) {
                        wo.setHinshi2(targetArray[3]);
                    }
                    if (targetArray.length > 4) {
                        wo.setHinshi3(targetArray[4]);
                    }
                    if (targetArray.length > 5) {
                        wo.setKatsuyoKata(targetArray[5]);
                    }
                    if (targetArray.length > 6) {
                        wo.setKatsuyoKei(targetArray[6]);
                    }
                    if (targetArray.length > 7) {
                        wo.setGenkei(targetArray[7]);
                    }
                    if (targetArray.length > 8) {
                        wo.setYomi(targetArray[8]);
                    }

                    if ("名詞".equals(wo.getHinshi().toString()) && "一般".equals(wo.getHinshi1().toString())
                            && wo.getYomi() != null) {
                        // System.out.println(wo.toString());

                        int i;
                        for (i = 0; i < list.size(); i++) {
                            if (list.get(i).getWord().equals(wo)) {
                                list.get(i).setCount(list.get(i).getCount() + 1);
                                break;
                            }
                        }
                        // リストにエントリが無かったときは，新しい語としてリストに追加する
                        if (i == list.size()) {
                            list.add(new TfCount(wo, Integer.valueOf(1)));
                        }

                    }

                    // 形態素解析結果を表示する
                    // System.out.println(wo.toString());

                }

            }
            for (int i = 0; i < list.size(); i++) {
                // System.out.println(list.get(i).getWord().getHyousoukei()
                // + ":" + list.get(i).getCount());
                sum += list.get(i).getCount();
            }
            // System.out.println("合計語数:" + sum);

            list.sort(new WordCompare());

            // String outputFilename = "data\\001tf.txt";
            try (FileWriter fw = new FileWriter(outputFilename)) {
                // System.out.println(outputFilename);
                fw.write("単語\t出現回数\tTF\n");
                for (int i = 0; i < list.size(); i++) {
                    // System.out.println(list.get(i).getWord().getHyousoukei() + ":"
                    // + list.get(i).getCount());
                    list.get(i).setTf((double) list.get(i).getCount() / (double) sum);
                    fw.write(list.get(i).getWord().getHyousoukei() + "\t"
                            + list.get(i).getCount() + "\t"
                            + String.format("%.10f", list.get(i).getTf()) + "\n");
                }
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}