package nlp;

class Word {
	private String hyousoukei = null;// 表層形
	private String hinshi = null;// 品詞
	private String hinshi1 = null;// 品詞細分類 1
	private String hinshi2 = null;// 品詞細分類 2
	private String hinshi3 = null;// 品詞細分類 3
	private String katsuyoKata = null;// 活用型
	private String katsuyoKei = null;// 活用形
	private String genkei = null;// 原形
	private String yomi = null;// 読み
	private String hatsuon = null;// 発音

	public boolean equals(Object obj) {
		// オブジェクトが null の場合，不一致
		if (obj == null) {
			return false;
		}
		// オブジェクトの型が異なる場合，不一致
		if (!(obj instanceof Word)) {
			return false;
		}
		// オブジェクトの全てのフィールドが一致している場合，一致
		if (((((Word) obj).getHyousoukei() == null && this.getHyousoukei() == null)
				|| ((Word) obj).getHyousoukei().equals(this.getHyousoukei()))
				&& ((((Word) obj).getHinshi() == null && this.getHinshi() == null)
						|| ((Word) obj).getHinshi().equals(this.getHinshi()))
				&& ((((Word) obj).getHinshi1() == null && this.getHinshi1() == null)
						|| ((Word) obj).getHinshi1().equals(this.getHinshi1()))
				&& ((((Word) obj).getHinshi2() == null && this.getHinshi2() == null)
						|| ((Word) obj).getHinshi2().equals(this.getHinshi2()))
				&& ((((Word) obj).getHinshi3() == null && this.getHinshi3() == null)
						|| ((Word) obj).getHinshi3().equals(this.getHinshi3()))
				&& ((((Word) obj).getKatsuyoKata() == null && this.getKatsuyoKata() == null)
						|| ((Word) obj).getKatsuyoKata().equals(this.getKatsuyoKata()))
				&& ((((Word) obj).getKatsuyoKei() == null && this.getKatsuyoKei() == null)
						|| ((Word) obj).getKatsuyoKei().equals(this.getKatsuyoKei()))
				&& ((((Word) obj).getGenkei() == null && this.getGenkei() == null)
						|| ((Word) obj).getGenkei().equals(this.getGenkei()))
				&& ((((Word) obj).getYomi() == null && this.getYomi() == null)
						|| ((Word) obj).getYomi().equals(this.getYomi()))
				&& ((((Word) obj).getHatsuon() == null && this.getHatsuon() == null)
						|| ((Word) obj).getHatsuon().equals(this.getHatsuon()))) {
			return true;
		}
		// そのほかは不一致
		return false;
	}

	public String getHyousoukei() {
		return hyousoukei;
	}

	public void setHyousoukei(String hyousoukei) {
		this.hyousoukei = hyousoukei;
	}

	public String getHinshi() {
		return hinshi;
	}

	public void setHinshi(String hinshi) {
		this.hinshi = hinshi;
	}

	public String getHinshi1() {
		return hinshi1;
	}

	public void setHinshi1(String hinshi1) {
		this.hinshi1 = hinshi1;
	}

	public String getHinshi2() {
		return hinshi2;
	}

	public void setHinshi2(String hinshi2) {
		this.hinshi2 = hinshi2;
	}

	public String getHinshi3() {
		return hinshi3;
	}

	public void setHinshi3(String hinshi3) {
		this.hinshi3 = hinshi3;
	}

	public String getKatsuyoKata() {
		return katsuyoKata;
	}

	public void setKatsuyoKata(String katsuyoKata) {
		this.katsuyoKata = katsuyoKata;
	}

	public String getKatsuyoKei() {
		return katsuyoKei;
	}

	public void setKatsuyoKei(String katsuyoKei) {
		this.katsuyoKei = katsuyoKei;
	}

	public String getGenkei() {
		return genkei;
	}

	public void setGenkei(String genkei) {
		this.genkei = genkei;
	}

	public String getYomi() {
		return yomi;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public String getHatsuon() {
		return hatsuon;
	}

	public void setHatsuon(String hatsuon) {
		this.hatsuon = hatsuon;
	}

	public String toString() {
		return "Word [hyousoukei=" + hyousoukei + ", hinshi=" + hinshi + ", hinshi1=" + hinshi1 + ", hinshi2=" + hinshi2
				+ ", hinshi3=" + hinshi3 + ", katsuyoKata=" + katsuyoKata + ", katsuyoKei=" + katsuyoKei + ", genkei="
				+ genkei + ", yomi=" + yomi + ", hatsuon=" + hatsuon + "]";
	}
}
