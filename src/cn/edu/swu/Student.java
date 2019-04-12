package cn.edu.swu;

public class Student {
		
	private int Id;
	
	private int type;
	
	private String name;
	
	private int grade;

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", type=" + type + ", name=" + name + ", grade=" + grade + "]";
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Student() {
		
	}

	public Student(Integer id, int type, String name, int grade) {
		super();
		Id = id;
		this.type = type;
		this.name = name;
		this.grade = grade;
	}


	
}

