package practice;

public class TableCL {
	
	private String name;
	private int ko;
	private int math;
	private int eng;
	
	public TableCL() {
		super();
	}
	public TableCL(String name, int ko, int math, int eng) {
		super();
		this.name = name;
		this.ko = ko;
		this.math = math;
		this.eng = eng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKo() {
		return ko;
	}
	public void setKo(int ko) {
		this.ko = ko;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	@Override
	public String toString() {
		return "TableCL [name=" + name + ", ko=" + ko + ", math=" + math + ", eng=" + eng + "]";
	}
	
}
