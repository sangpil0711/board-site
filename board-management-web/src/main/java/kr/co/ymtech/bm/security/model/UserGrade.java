package kr.co.ymtech.bm.security.model;

import java.util.HashMap;

public class UserGrade {

	public static final int NOT_ENTERED_ID_OR_PASSWORD = -3;
	public static final int UNKNOWN_USER = -2;
	public static final int INVALID_UNKNOWN_AND_ERROR = -1;

	/** 관리자 */
	public static final int ADMIN = 0;
	/** 일반사용자 */
	public static final int USER = 1;

	private static final HashMap<Integer, String> USER_GRADES = new HashMap<>();

	static {
		USER_GRADES.put(NOT_ENTERED_ID_OR_PASSWORD, "NOT_ENTERED_ID_OR_PASSWORD");
		USER_GRADES.put(UNKNOWN_USER, "UNKNOWN_USER");
		USER_GRADES.put(INVALID_UNKNOWN_AND_ERROR, "ERROR");

		USER_GRADES.put(ADMIN, "ROLE_ADMIN");
		USER_GRADES.put(USER, "ROLE_USER");
	}

	private String descr;

	private Integer id = 0;

	private String name;

	public UserGrade() {
	}

	private UserGrade(int grade) {
		this.id = grade;
	}

	public String getDescr() {
		return this.descr;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static boolean available(int grade) {
		switch (grade) {
		case UserGrade.ADMIN:
		case UserGrade.USER:
			return true;
		default:
			return false;
		}
	}

	public static UserGrade getUserGrade(int id, String name, String desc) {

		int _id_ = INVALID_UNKNOWN_AND_ERROR;

		if (USER_GRADES.containsKey(id)) {
			_id_ = id;
			name = USER_GRADES.get(id);
		}

		UserGrade grade = new UserGrade(_id_);

		grade.setName(name);
		grade.setDescr(desc);

		return grade;
	}

}
